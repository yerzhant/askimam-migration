package kz.azan.askimammigration.importer.model

import com.google.cloud.firestore.QueryDocumentSnapshot
import kz.azan.askimammigration.common.converter.toLocalDateTime
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "fb_topics")
data class Topic(
    @Id
    val id: String,
    val name: String,

    val uid: String,
    val imamUid: String?,

    val isPublic: Boolean,
    val isAnswered: Boolean?,

    val fcmToken: String,
    val imamFcmToken: String?,

    val viewedOn: LocalDateTime,
    val createdOn: LocalDateTime,
    val modifiedOn: LocalDateTime,
    val imamViewedOn: LocalDateTime?,
) {
    companion object {
        fun from(doc: QueryDocumentSnapshot) = Topic(
            id = doc.id,
            name = doc.getString("name") ?: "XXX",

            uid = doc.getString("uid") ?: "XXX",
            imamUid = doc.getString("imamUid"),

            isPublic = doc.getBoolean("isPublic") ?: false,
            isAnswered = doc.getBoolean("isAnswered"),

            fcmToken = doc.getString("fcmToken") ?: "XXX",
            imamFcmToken = doc.getString("imamFcmToken"),

            viewedOn = toLocalDateTime(doc.getLong("viewedOn")) ?: LocalDateTime.of(2019, 1, 1, 0, 0),
            createdOn = toLocalDateTime(doc.getLong("createdOn")) ?: LocalDateTime.of(2019, 1, 1, 0, 0),
            modifiedOn = toLocalDateTime(doc.getLong("modifiedOn")) ?: LocalDateTime.of(2019, 1, 1, 0, 0),
            imamViewedOn = toLocalDateTime(doc.getLong("imamViewedOn")),
        )
    }
}
