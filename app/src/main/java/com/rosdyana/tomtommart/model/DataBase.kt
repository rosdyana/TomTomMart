package com.rosdyana.tomtommart.model

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rosdyana.tomtommart.AppController

@Database(entities = [ProductEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        private var dbInstance: DataBase? = null

        fun getInstance(): DataBase? {
            if (dbInstance == null) {
                synchronized(DataBase::class.java) {
                    dbInstance = Room.databaseBuilder(
                        AppController.getInstance().applicationContext,
                        DataBase::class.java, "tomtom_mart.db"
                    ).allowMainThreadQueries().build()
                }
            }
            return dbInstance
        }

        fun destroyInstance() {
            dbInstance = null
        }
    }
}