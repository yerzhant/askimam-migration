package kz.azan.askimammigration.mysql.model

import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, String>
