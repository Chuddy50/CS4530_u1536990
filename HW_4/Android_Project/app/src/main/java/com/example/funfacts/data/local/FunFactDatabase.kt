package com.example.funfacts.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.funfacts.data.local.FunFactDao

@Database(entities = [FunFactEntity::class], version = 1, exportSchema = false)
abstract class FunFactsDatabase : RoomDatabase() {
    abstract fun funFactDao(): FunFactDao
}
