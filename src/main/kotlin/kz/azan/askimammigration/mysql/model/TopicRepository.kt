package kz.azan.askimammigration.mysql.model

import org.springframework.data.repository.CrudRepository

interface TopicRepository : CrudRepository<Topic, Int>
