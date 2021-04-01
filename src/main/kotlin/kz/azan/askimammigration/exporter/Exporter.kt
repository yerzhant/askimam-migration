package kz.azan.askimammigration.exporter

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.QueryDocumentSnapshot
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import kz.azan.askimammigration.importer.Importer
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.util.function.Consumer

@Service
class Exporter(private val importer: Importer) {

    private val topics = "topics"
    private val messages = "messages"
    private val favorites = "favorites"
    private val profiles = "profiles"

    private lateinit var db: Firestore

    private val logger  = LoggerFactory.getLogger(javaClass)

    init {
        FileInputStream("google/azan-kz-ask-imam-firebase-adminsdk.json").use {
            val credentials = GoogleCredentials.fromStream(it)
            val options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build()
            FirebaseApp.initializeApp(options)
        }

        db = FirestoreClient.getFirestore()
    }

    fun copyAll() {
//        logger.info("Copying Topics...")
//        extractCollectionTo(topics, importer::saveTopic)

//        logger.info("Copying Messages...")
//        extractCollectionTo(messages, importer::saveMessage)

//        logger.info("Copying Favorites...")
//        extractCollectionTo(favorites, importer::saveFavorite)

//        logger.info("Copying Profiles...")
//        extractCollectionTo(profiles, importer::saveProfile)
    }

    private fun extractCollectionTo(collection: String, saver: Consumer<QueryDocumentSnapshot>) {
        db.collection(collection).get().get().run {
            documents.forEach {
                saver.accept(it)
            }
        }
    }
}