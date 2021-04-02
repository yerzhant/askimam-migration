package kz.azan.askimammigration.migrator.model

import kz.azan.askimammigration.anonymousUserId
import kz.azan.askimammigration.importer.model.Message
import kz.azan.askimammigration.importer.model.ProfileRepository
import kz.azan.askimammigration.importer.model.TopicRepository
import kz.azan.askimammigration.migrator.model.ChatMessage.Type.Audio
import kz.azan.askimammigration.migrator.model.ChatMessage.Type.Text
import kz.azan.askimammigration.migrator.model.ChatMessage.UserType.Imam
import kz.azan.askimammigration.migrator.model.ChatMessage.UserType.Inquirer
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.EnumType.STRING
import javax.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "messages")
data class ChatMessage(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null,

    @Enumerated(STRING)
    val type: Type,
    val text: String,

    val audio: String?,
    val duration: String?,

    val authorId: Int,
    @Enumerated(STRING)
    val authorType: UserType,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?,

    val chatId: Long,
) {
    companion object {
        fun from(
            message: Message,
            imamRepository: ImamRepository,
            profileRepository: ProfileRepository,
            topicRepository: TopicRepository,
        ) = topicRepository.findByIdOrNull(message.topicId)?.run {
            ChatMessage(
                type = if (message.audioUrl != null) Audio else Text,
                text = message.text,

                audio = message.audioUrl,
                duration = message.duration,

                authorType = if (message.sender == "i") Imam else Inquirer,
                authorId = getAuthor(message, imamRepository, profileRepository),

                createdAt = message.createdOn,
                updatedAt = message.editedOn,

                chatId = chatId!!,
            )
        }

        private fun getAuthor(
            message: Message,
            imamRepository: ImamRepository,
            profileRepository: ProfileRepository,
        ) = if (message.sender == "i") {
            if (message.senderName == null) anonymousUserId
            else
                imamRepository.findFirstByName(message.senderName.trim()).userId
        } else {
            profileRepository.findByIdOrNull(message.uid)?.userId ?: anonymousUserId
        }
    }

    enum class Type { Text, Audio }
    enum class UserType { Imam, Inquirer }
}