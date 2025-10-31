package com.example.funfacts.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FunFactDao {
    @Query("SELECT * FROM fun_facts ORDER BY fetchedAtMillis DESC")
    fun observeFacts(): Flow<List<FunFactEntity>>

    @Insert
    suspend fun insert(fact: FunFactEntity)
}
