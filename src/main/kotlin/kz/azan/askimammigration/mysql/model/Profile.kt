package kz.azan.askimammigration.mysql.model

import com.google.cloud.firestore.QueryDocumentSnapshot
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "fb_profiles")
data class Profile(
    @Id
    val id: String,
    val uid: String?,
    val login: String?,
    val timezone: Int?,
) {
    companion object {
        fun from(doc: QueryDocumentSnapshot) = Profile(
            id = doc.id,
            login = doc.getString("login"),
            uid = doc.getString("uid"),
            timezone = doc.getLong("timezone")?.toInt(),
        )
    }
}
