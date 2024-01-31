package ehsan.haghdoust.petfinder.repository.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserCredential")
data class UserCredential(@ColumnInfo val tokenType: String,
                          @ColumnInfo var retrievedTime: Long,
                          @ColumnInfo val expiresIn: Int,
                          @PrimaryKey @ColumnInfo val accessToken: String)