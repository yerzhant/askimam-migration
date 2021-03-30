package kz.azan.askimammigration.mysql

import com.google.cloud.firestore.QueryDocumentSnapshot
import kz.azan.askimammigration.mysql.model.Topic
import kz.azan.askimammigration.mysql.model.TopicRepository
import org.springframework.stereotype.Service

@Service
class Importer(private val topicRepository: TopicRepository) {

    fun saveTopic(doc: QueryDocumentSnapshot) {
        val topic = Topic.from(doc)
        topicRepository.save(topic)
    }

    fun cleanup() {
        topicRepository.deleteAll()
    }
}
