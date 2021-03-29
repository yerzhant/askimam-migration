package kz.azan.askimammigration.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.QueryDocumentSnapshot
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import kz.azan.askimammigration.mysql.Importer
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.util.function.Consumer
import javax.annotation.PostConstruct

@Service
class Exporter(private val importer: Importer) {

    private val topics = "testTopics"
    private val messages = "testMessages"
    private val favorites = "testFavorites"

    private lateinit var db: Firestore

    @PostConstruct
    fun init() {
        FileInputStream("google/azan-kz-ask-imam-firebase-adminsdk.json").use {
            val credentials = GoogleCredentials.fromStream(it)
            val options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build()
            FirebaseApp.initializeApp(options)
        }

        db = FirestoreClient.getFirestore()
    }

    fun transfer() {
        importer.cleanup()

        extractCollectionTo(topics, importer::saveTopic)
//        extractCollectionTo(topics, importer::saveMessage)
    }

    private fun extractCollectionTo(collection: String, saver: Consumer<QueryDocumentSnapshot>) {
        db.collection(collection).get().get().run {
            documents.forEach {
                saver.accept(it)
            }
        }
    }
}