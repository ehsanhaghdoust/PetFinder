package ehsan.haghdoust.petfinder.repository.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserCredentials")
data class UserCredentials(@ColumnInfo val tokenType: String,
                           @ColumnInfo val expiresIn: Int,
                           @ColumnInfo val accessToken: String)