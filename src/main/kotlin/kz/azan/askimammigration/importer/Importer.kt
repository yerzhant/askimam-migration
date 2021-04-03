package kz.azan.askimammigration.importer

import com.google.cloud.firestore.QueryDocumentSnapshot
import kz.azan.askimammigration.importer.model.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class Importer(
    private val topicRepository: TopicRepository,
    private val messageRepository: MessageRepository,
    private val favoriteRepository: FavoriteRepository,
    private val profileRepository: ProfileRepository,
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun saveTopic(doc: QueryDocumentSnapshot) {
        val topic = Topic.from(doc)
        topicRepository.save(topic)
    }

    fun saveMessage(doc: QueryDocumentSnapshot) {
        val message = Message.from(doc)
        messageRepository.save(message)
    }

    fun saveFavorite(doc: QueryDocumentSnapshot) {
        val favorite = Favorite.from(doc)
        favoriteRepository.save(favorite)
    }

    fun saveProfile(doc: QueryDocumentSnapshot) {
        val profile = Profile.from(doc)
        profileRepository.save(profile)
    }

    fun cleanup() {
        logger.info("[Importer] Cleaning up...")
        topicRepository.deleteAll()
        messageRepository.deleteAll()
        favoriteRepository.deleteAll()
        profileRepository.deleteAll()
    }
}
