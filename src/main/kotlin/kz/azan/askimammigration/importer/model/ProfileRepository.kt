package kz.azan.askimammigration.importer.model

import org.springframework.data.repository.CrudRepository

interface ProfileRepository : CrudRepository<Profile, String> {
    fun findByUid(uid: String): Profile?
}
