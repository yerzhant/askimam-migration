package kz.azan.askimammigration.migrator.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "chats")
data class Chat(
    @Id
    val id: Long? = null,
    val type: Type,
    val subject: String?,

    val askedBy: Long,
    val answeredBy: Long?,

    val inquirerFcmToken: String,
    val imamFcmToken: String?,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,

    val isVisibleToPublic: Boolean,
    val isViewedByImam: Boolean,
    val isViewedByInquirer: Boolean,
) {
//    companion object {
//        fun from(topic: Topic) = Chat(
//            type = if (topic.isPublic && topic.isAnswered != null && topic.isAnswered) Public else Private,
//            subject = topic.name,
//            askedBy =
//        )
//    }

    enum class Type { Public, Private }
}