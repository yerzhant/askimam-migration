package kz.azan.askimammigration.importer.model

import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, String>
