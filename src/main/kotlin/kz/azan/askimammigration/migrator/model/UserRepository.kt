package kz.azan.askimammigration.migrator.model

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int> {
    fun findByUsername(login: String?): User?
}
