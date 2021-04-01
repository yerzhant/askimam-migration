package kz.azan.askimammigration.migrator.model

import org.springframework.data.repository.CrudRepository

interface ChatMessageRepository : CrudRepository<ChatMessage, Long>
