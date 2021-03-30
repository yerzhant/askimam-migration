package kz.azan.askimammigration.mysql.model

import com.google.cloud.firestore.QueryDocumentSnapshot
import kz.azan.askimammigration.common.type.ext.toNano
import kz.azan.askimammigration.common.type.ext.toSeconds
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "fb_topics")
data class Topic(
    @Id
    val id: String?,
    val name: String?,

    val uid: String?,
    val imamUid: String?,

    val isPublic: Boolean?,
    val isAnswered: Boolean?,

    val fcmToken: String?,
    val imamFcmToken: String?,

    val viewedOn: LocalDateTime?,
    val createdOn: LocalDateTime?,
    val modifiedOn: LocalDateTime?,
    val imamViewedOn: LocalDateTime?,
) {
    companion object {
        fun from(doc: QueryDocumentSnapshot) = Topic(
            id = doc.id,
            name = doc.getString("name"),

            uid = doc.getString("uid"),
            imamUid = doc.getString("imamUid"),

            isPublic = doc.getBoolean("isPublic"),
            isAnswered = doc.getBoolean("isAnswered"),

            fcmToken = doc.getString("fcmToken"),
            imamFcmToken = doc.getString("imamFcmToken"),

            viewedOn = toLocalDateTime(doc.getLong("viewedOn")),
            createdOn = toLocalDateTime(doc.getLong("createdOn")),
            modifiedOn = toLocalDateTime(doc.getLong("modifiedOn")),
            imamViewedOn = toLocalDateTime(doc.getLong("imamViewedOn")),
        )

        private fun toLocalDateTime(timestamp: Long?) =
            timestamp?.run {
                LocalDateTime.ofEpochSecond(timestamp.toSeconds(), timestamp.toNano(), ZoneOffset.ofHours(6))
            }
    }
}
