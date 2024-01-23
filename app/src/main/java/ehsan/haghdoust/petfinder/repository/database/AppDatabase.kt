package ehsan.haghdoust.petfinder.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ehsan.haghdoust.petfinder.repository.database.dao.DAO
import ehsan.haghdoust.petfinder.repository.database.entity.UserCredential


@Database(entities = [UserCredential::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun daoObject(): DAO

    companion object {
        //  Volatile means that writings to this field are immediately made visible to other thread
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "PetFinderDatabase").build()
                    INSTANCE = instance
                }
                instance
            }
        }
    }
}