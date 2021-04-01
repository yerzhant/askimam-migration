package kz.azan.askimammigration.mysql

import com.google.cloud.firestore.QueryDocumentSnapshot
import kz.azan.askimammigration.mysql.model.Message
import kz.azan.askimammigration.mysql.model.MessageRepository
import kz.azan.askimammigration.mysql.model.Topic
import kz.azan.askimammigration.mysql.model.TopicRepository
import org.springframework.stereotype.Service

@Service
class Importer(
    private val topicRepository: TopicRepository,
    private val messageRepository: MessageRepository,
) {

    fun saveTopic(doc: QueryDocumentSnapshot) {
        val topic = Topic.from(doc)
        topicRepository.save(topic)
    }

    fun saveMessage(doc: QueryDocumentSnapshot) {
        val message = Message.from(doc)
        messageRepository.save(message)
    }

    fun cleanup() {
//        println("Cleaning up...")
//        topicRepository.deleteAll()
//        messageRepository.deleteAll()
    }
}
