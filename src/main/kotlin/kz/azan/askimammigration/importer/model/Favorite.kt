package kz.azan.askimammigration.importer.model

import com.google.cloud.firestore.QueryDocumentSnapshot
import kz.azan.askimammigration.common.converter.toLocalDateTime
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "fb_favorites")
data class Favorite(
    @Id
    val id: String,
    val createdOn: LocalDateTime?,

    val uid: String?,

    val topicId: String?,
    val topicName: String?,
) {
    companion object {
        fun from(doc: QueryDocumentSnapshot) = Favorite(
            id = doc.id,
            createdOn = toLocalDateTime(doc.getLong("createdOn")),

            uid = doc.getString("uid"),

            topicId = doc.getString("topicId"),
            topicName = doc.getString("topicName"),
        )
    }
}
