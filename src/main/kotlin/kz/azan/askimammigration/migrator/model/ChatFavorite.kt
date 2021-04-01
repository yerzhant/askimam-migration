package kz.azan.askimammigration.migrator.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "favorites")
data class ChatFavorite(
    @Id
    val id: Long?,
    val userId: Long,
    val chatId: Long,
    val addedAt: LocalDateTime,
)