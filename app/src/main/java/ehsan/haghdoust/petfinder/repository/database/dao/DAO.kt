package ehsan.haghdoust.petfinder.repository.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ehsan.haghdoust.petfinder.repository.database.entity.UserCredential

interface DAO {

    @Query("Select * from UserCredentials")
    fun getCredential(): UserCredential

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveToken(userCredential: UserCredential)
}