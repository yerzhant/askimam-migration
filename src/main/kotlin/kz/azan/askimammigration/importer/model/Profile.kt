package kz.azan.askimammigration.importer.model

import com.google.cloud.firestore.QueryDocumentSnapshot
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "fb_profiles")
data class Profile(
    @Id
    val id: String,
    val login: String?,
    val timezone: Int?,
    var userId: Int? = null,
) {
    companion object {
        fun from(doc: QueryDocumentSnapshot) = Profile(
            id = doc.id,
            login = doc.getString("login"),
            timezone = doc.getLong("timezone")?.toInt(),
        )
    }
}
