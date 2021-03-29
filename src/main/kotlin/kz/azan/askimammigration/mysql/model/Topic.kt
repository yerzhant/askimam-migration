package kz.azan.askimammigration.mysql.model

import com.google.cloud.Timestamp
import com.google.cloud.firestore.QueryDocumentSnapshot
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.persistence.GeneratedValue
import javax.persistence.Id

data class Topic(
    @Id
    @GeneratedValue
    val id: Int? = null,
    val name: String?,
    val uid: String?,
    val imamUid: String?,
    val fcmToken: String?,
    val imamFcmToken: String?,
    val viewedOn: LocalDateTime?,
    val createdOn: LocalDateTime?,
    val modifiedOn: LocalDateTime?,
    val imamViewedOn: LocalDateTime?,
) {
    companion object {
        fun from(doc: QueryDocumentSnapshot) = Topic(
            name = doc.getString("name"),
            uid = doc.getString("uid"),
            imamUid = doc.getString("imamUid"),
            fcmToken = doc.getString("fcmToken"),
            imamFcmToken = doc.getString("imamFcmToken"),
            viewedOn = toLocalDateTime(doc.getTimestamp("viewedOn")),
            createdOn = toLocalDateTime(doc.getTimestamp("createdOn")),
            modifiedOn = toLocalDateTime(doc.getTimestamp("modifiedOn")),
            imamViewedOn = toLocalDateTime(doc.getTimestamp("imamViewedOn")),
        )

        private fun toLocalDateTime(timestamp: Timestamp?) =
            timestamp?.run {
                LocalDateTime.ofEpochSecond(timestamp.seconds, timestamp.nanos, ZoneOffset.UTC)
            }
    }
}
