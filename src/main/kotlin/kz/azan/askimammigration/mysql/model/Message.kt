package kz.azan.askimammigration.mysql.model

import com.google.cloud.firestore.QueryDocumentSnapshot
import kz.azan.askimammigration.common.converter.toLocalDateTime
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "fb_messages")
data class Message(
    @Id
    val id: String,
    val topicId: String?,

    val uid: String?,
    val sender: String?,
    val senderName: String?,

    val text: String?,
    val audioUrl: String?,
    val duration: String?,

    val createdOn: LocalDateTime?,
    val editedOn: LocalDateTime?,
) {
    companion object {
        fun from(doc: QueryDocumentSnapshot) = Message(
            id = doc.id,
            topicId = doc.getString("topicId"),

            uid = doc.getString("uid"),
            sender = doc.getString("sender"),
            senderName = doc.getString("senderName"),

            text = doc.getString("text"),
            audioUrl = doc.getString("audioUrl"),
            duration = doc.getString("duration"),

            createdOn = toLocalDateTime(doc.getLong("createdOn")),
            editedOn = toLocalDateTime(doc.getLong("editedOn")),
        )
    }
}
