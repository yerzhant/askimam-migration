package kz.azan.askimammigration.migrator

import kz.azan.askimammigration.importer.model.FavoriteRepository
import kz.azan.askimammigration.importer.model.MessageRepository
import kz.azan.askimammigration.importer.model.ProfileRepository
import kz.azan.askimammigration.importer.model.TopicRepository
import kz.azan.askimammigration.migrator.model.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class Migrator(
    private val topicRepository: TopicRepository,
    private val messageRepository: MessageRepository,
    private val favoriteRepository: FavoriteRepository,
    private val imamRepository: ImamRepository,
    private val profileRepository: ProfileRepository,
    private val chatRepository: ChatRepository,
    private val chatMessageRepository: ChatMessageRepository,
    private val chatFavoriteRepository: ChatFavoriteRepository,
    private val userRepository: UserRepository,
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun fillUserIdsInProfiles() {
        logger.info("Filling up user ids in profiles...")

        profileRepository.findAll().forEach {
            it.userId = userRepository.findByUsername(it.login).id
            profileRepository.save(it)
        }
    }

    @Transactional
    fun migrateChats() {
        logger.info("Migrating chats...")

        topicRepository.findAll().forEach {
            val chat = Chat.from(it, profileRepository, imamRepository)
            chatRepository.save(chat).run {
                it.chatId = id
                topicRepository.save(it)
            }
        }
    }

    fun cleanup() {
        logger.info("[Migration] Cleaning up...")
        chatRepository.deleteAll()
    }
}