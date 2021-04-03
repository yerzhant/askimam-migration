package kz.azan.askimammigration.migrator.model

import org.springframework.data.repository.CrudRepository

interface ChatMessageRepository : CrudRepository<ChatMessage, Long> {
    fun findByAudioStartingWith(prefix: String): Collection<ChatMessage>
}
