package kz.azan.askimammigration.migrator.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Id
    val id: Int,
    val username: String,
)