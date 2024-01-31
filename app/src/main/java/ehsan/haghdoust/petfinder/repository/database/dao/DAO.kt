package ehsan.haghdoust.petfinder.repository.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ehsan.haghdoust.petfinder.repository.database.entity.UserCredential

@Dao
interface DAO {

    @Query("Select * from UserCredential")
    fun getToken(): List<UserCredential>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveToken(userCredential: UserCredential)

    @Query("delete from UserCredential")
    fun deleteTokens()
}