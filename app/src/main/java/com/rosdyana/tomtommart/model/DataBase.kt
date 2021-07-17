package com.rosdyana.tomtommart.model

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rosdyana.tomtommart.AppController

@Database(entities = [ProductEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        private var INSTANCE: DataBase? = null

        fun getInstance(): DataBase? {
            if (INSTANCE == null) {
                synchronized(DataBase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        AppController.getInstance().applicationContext,
                        DataBase::class.java, "tomtom_mart.db"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }
}