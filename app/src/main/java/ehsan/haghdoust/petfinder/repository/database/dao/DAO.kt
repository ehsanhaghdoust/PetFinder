package ehsan.haghdoust.petfinder.repository.database.dao

import androidx.room.Query
import ehsan.haghdoust.petfinder.repository.database.entity.UserCredentials

interface DAO {

    @Query("Select * from UserCredentials")
    fun getCredential(): UserCredentials
}