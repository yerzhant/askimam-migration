package kz.azan.askimammigration.migrator.model

import kz.azan.askimammigration.anonymousUserId
import kz.azan.askimammigration.importer.model.ProfileRepository
import kz.azan.askimammigration.importer.model.Topic
import kz.azan.askimammigration.migrator.Migrator
import kz.azan.askimammigration.migrator.model.Chat.Type.Private
import kz.azan.askimammigration.migrator.model.Chat.Type.Public
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

    val askedBy: Int,
    val answeredBy: Long?,

    val inquirerFcmToken: String,
    val imamFcmToken: String?,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,

    val isVisibleToPublic: Boolean,
    val isViewedByImam: Boolean,
    val isViewedByInquirer: Boolean,
) {
    companion object {
//        fun from(
//            topic: Topic,
//            profileRepository: ProfileRepository,
//            imamRepository: ImamRepository,
//        ) = Chat(
//            type = if (topic.isPublic && topic.isAnswered != null && topic.isAnswered) Public else Private,
//            subject = topic.name,
//            askedBy = profileRepository.findByUid(topic.uid)?.userId ?: anonymousUserId,
//            answeredBy = imamRepository.
//        )
    }

    enum class Type { Public, Private }
}