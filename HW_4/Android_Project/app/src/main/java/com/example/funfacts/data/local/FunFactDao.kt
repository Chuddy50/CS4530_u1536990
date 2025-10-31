package com.example.funfacts.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.funfacts.data.FunFactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FunFactDao {
    @Query("SELECT * FROM fun_facts ORDER BY fetchedAtMillis DESC")
    fun observeFacts(): Flow<List<FunFactEntity>>

    @Insert
    suspend fun insert(fact: FunFactEntity)
}