package kz.azan.askimammigration.migrator.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "fb_imams")
data class Imam(
    @Id
    val id: String,
    val userId: Int,
    val name: String,
)
