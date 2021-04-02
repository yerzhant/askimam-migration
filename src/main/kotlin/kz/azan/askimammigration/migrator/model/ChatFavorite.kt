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
        ) = profileRepository.findByIdOrNull(favorite.uid)?.run {

            ChatFavorite(
                userId = userId!!,
                chatId = topicRepository.findByIdOrNull(favorite.topicId)?.chatId!!,
                addedAt = favorite.createdOn,
            )
        }
    }
}