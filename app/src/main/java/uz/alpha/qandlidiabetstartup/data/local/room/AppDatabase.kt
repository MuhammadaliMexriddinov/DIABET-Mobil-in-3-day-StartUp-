package uz.alpha.qandlidiabetstartup.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
Mobile Developer
Creator:Mekhriddinov Muhammadali
 */
@Database(entities = [DiseaseEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): DiseaseDao

    companion object {
        private lateinit var instance: AppDatabase
        fun init(ctx: Context) {
            if (!Companion::instance.isInitialized) {
                instance = Room.databaseBuilder(ctx, AppDatabase::class.java, "disease.db")
                    .allowMainThreadQueries()
                    .build()
            }
        }

        fun getInstance() = instance
    }

}