package com.example.funfacts.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fun_facts")
data class FunFactEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val text: String,
    val source: String?,
    val fetchedAtMillis: Long
)
