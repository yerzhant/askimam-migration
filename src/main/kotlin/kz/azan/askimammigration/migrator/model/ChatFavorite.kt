package kz.azan.askimammigration.migrator.model

import kz.azan.askimammigration.importer.model.Favorite
import kz.azan.askimammigration.importer.model.ProfileRepository
import kz.azan.askimammigration.importer.model.TopicRepository
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "favorites")
data class ChatFavorite(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null,
    val userId: Int,
    val chatId: Long,
    val addedAt: LocalDateTime,
) {
    companion object {
        fun from(
            favorite: Favorite,
            profileRepository: ProfileRepository,
            topicRepository: TopicRepository,
        ) = profileRepository.findByIdAndUserIdIsNotNull(favorite.uid)?.run {
            topicRepository.findByIdOrNull(favorite.topicId)?.run {
                ChatFavorite(
                    userId = userId!!,
                    chatId = chatId!!,
                    addedAt = favorite.createdOn,
                )
            }
        }
    }
}