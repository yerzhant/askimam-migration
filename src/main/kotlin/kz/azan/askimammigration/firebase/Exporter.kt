package kz.azan.askimammigration.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.cloud.firestore.Query
import com.google.cloud.firestore.QueryDocumentSnapshot
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import kz.azan.askimammigration.mysql.Importer
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.util.function.Consumer

@Service
class Exporter(private val importer: Importer) {

    private val topics = "testTopics"
    private val messages = "testMessages"
    private val favorites = "testFavorites"
    private val profiles = "testProfiles"

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

        logger.info("Copying Profiles...")
        extractCollectionTo(profiles, importer::saveProfile)
    }

    private fun extractCollectionTo(collection: String, saver: Consumer<QueryDocumentSnapshot>) {
        db.collection(collection).limit(100).get().get().run {
            documents.forEach {
                saver.accept(it)
            }
        }
    }
}