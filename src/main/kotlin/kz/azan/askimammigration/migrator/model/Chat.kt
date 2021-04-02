package kz.azan.askimammigration.migrator.model

import kz.azan.askimammigration.anonymousUserId
import kz.azan.askimammigration.importer.model.ProfileRepository
import kz.azan.askimammigration.importer.model.Topic
import kz.azan.askimammigration.migrator.model.Chat.Type.Private
import kz.azan.askimammigration.migrator.model.Chat.Type.Public
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.EnumType.STRING
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "chats")
data class Chat(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null,

    @Enumerated(STRING)
    val type: Type,
    val subject: String?,

    val askedBy: Int,
    val answeredBy: Long?,

    val inquirerFcmToken: String,
    val imamFcmToken: String?,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,

    val isVisibleToPublic: Boolean,
    val isViewedByInquirer: Boolean,
    val isViewedByImam: Boolean,
) {
    companion object {
        fun from(
            topic: Topic,
            profileRepository: ProfileRepository,
            imamRepository: ImamRepository,
        ) = Chat(
            type = if (topic.isPublic && topic.isAnswered != null && topic.isAnswered) Public else Private,
            subject = topic.name,

            askedBy = profileRepository.findByIdOrNull(topic.uid)?.userId ?: anonymousUserId,
            answeredBy = if (topic.imamUid != null) imamRepository.findById(topic.imamUid)
                .get().userId.toLong() else null,

            inquirerFcmToken = topic.fcmToken,
            imamFcmToken = topic.imamFcmToken,

            createdAt = topic.createdOn,
            updatedAt = topic.modifiedOn,

            isVisibleToPublic = topic.isPublic,
            isViewedByInquirer = topic.viewedOn >= topic.modifiedOn,
            isViewedByImam = if (topic.imamViewedOn == null) false else topic.imamViewedOn >= topic.modifiedOn,
        )
    }

    enum class Type { Public, Private }
}