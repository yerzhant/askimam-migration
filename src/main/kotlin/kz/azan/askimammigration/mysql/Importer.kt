package kz.azan.askimammigration.mysql

import com.google.cloud.firestore.QueryDocumentSnapshot
import kz.azan.askimammigration.mysql.model.Topic
import kz.azan.askimammigration.mysql.model.TopicRepository

class Importer(private val topicRepository: TopicRepository) {
    fun cleanup() {
        topicRepository.deleteAll()
    }

    fun saveTopic(doc: QueryDocumentSnapshot) {
        Topic.from(doc)
    }
}
