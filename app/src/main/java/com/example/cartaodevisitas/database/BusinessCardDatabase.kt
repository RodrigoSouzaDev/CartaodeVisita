package com.example.cartaodevisitas.database

import android.content.Context
import androidx.room.Database

import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.cartaodevisitas.database.dao.BusinessCardDao
import com.example.cartaodevisitas.model.BusinessCard

@Database (entities = [BusinessCard::class], version = 1,exportSchema = false)
abstract class BusinessCardDatabase : RoomDatabase() {

    abstract fun businessCardDao() : BusinessCardDao

    companion object {

        private const val DATABASE_NAME: String = "Banco_BusinessCard"

        @Volatile
        private var INSTANCE:BusinessCardDatabase? = null

        fun getDatabase (context: Context): BusinessCardDatabase
        {
            val tempInstance = INSTANCE
            if(tempInstance != null)
            {
                return tempInstance
            }
            synchronized(this)
            {
                val instance = databaseBuilder(
                    context.applicationContext,
                    BusinessCardDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}