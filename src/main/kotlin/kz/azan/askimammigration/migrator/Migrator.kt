package kz.azan.askimammigration.migrator

import kz.azan.askimammigration.importer.model.FavoriteRepository
import kz.azan.askimammigration.importer.model.MessageRepository
import kz.azan.askimammigration.importer.model.ProfileRepository
import kz.azan.askimammigration.importer.model.TopicRepository
import kz.azan.askimammigration.migrator.model.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.util.*

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
    private val mp3Directory = "/tmp/audio"

    private val logger = LoggerFactory.getLogger(javaClass)

    fun fillUserIdsInProfiles() {
        logger.info("Filling up user ids in profiles...")

        profileRepository.findAll().forEach {
            it.userId = userRepository.findByUsername(it.login).id
            profileRepository.save(it)
        }
    }

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

    fun migrateMessages() {
        logger.info("Migrating messages...")

        messageRepository.findAll().forEach {
            ChatMessage.from(it, imamRepository, profileRepository, topicRepository)?.let { chatMessage ->
                chatMessageRepository.save(chatMessage).run {
                    it.messageId = id
                    messageRepository.save(it)
                }
            }
        }
    }

    fun migrateFavorites() {
        logger.info("Migrating favorites...")

        favoriteRepository.findAll().forEach {
            ChatFavorite.from(it, profileRepository, topicRepository)?.run {
                chatFavoriteRepository.save(this).run {
                    it.favoriteId = id
                    favoriteRepository.save(it)
                }
            }
        }
    }

    fun downloadAudios() {
        logger.info("Downloading audios...")
        File(mp3Directory).mkdir()

        chatMessageRepository.findByAudioStartingWith("https").forEach { message ->
            URL(message.audio).openStream().use { input ->
                val uuid = UUID.randomUUID()
                val fileName = "audio-$uuid.mp3"

                FileOutputStream("$mp3Directory/$fileName").use { input.copyTo(it) }

                message.audio = fileName
                chatMessageRepository.save(message)
            }
        }
    }

    fun cleanup() {
        logger.info("[Migration] Cleaning up...")
        File(mp3Directory).deleteRecursively()
        chatMessageRepository.deleteAll()
        chatRepository.deleteAll()
    }
}