package kz.azan.askimammigration.migrator.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "messages")
data class ChatMessage(
    @Id
    val id: Long?,
    val type: Type,
    val text: String,
    val audio: String?,

    val authorId: Long,
    val authorType: UserType,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?,
) {
    enum class Type { Text, Audio }
    enum class UserType { Imam, Inquirer }
}