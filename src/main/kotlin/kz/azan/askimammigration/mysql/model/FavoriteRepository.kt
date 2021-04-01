package kz.azan.askimammigration.mysql.model

import org.springframework.data.repository.CrudRepository

interface FavoriteRepository : CrudRepository<Favorite, String>
