package ehsan.haghdoust.petfinder.repository.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "UserCredentials")
data class UserCredential(@ColumnInfo val tokenType: String,
                          @ColumnInfo var retrievedTime: Long,
                          @ColumnInfo val expiresIn: Int,
                          @ColumnInfo val accessToken: String)