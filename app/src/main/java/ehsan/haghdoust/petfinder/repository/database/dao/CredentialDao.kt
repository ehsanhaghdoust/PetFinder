package ehsan.haghdoust.petfinder.repository.database.dao

import androidx.room.Insert
import ehsan.haghdoust.petfinder.repository.database.entity.UserCredential
import retrofit2.http.GET

interface CredentialDao {

    @Insert
    suspend fun saveUserCredential(userCredential: UserCredential)

    @GET
    suspend fun getUserCredential(): UserCredential
}