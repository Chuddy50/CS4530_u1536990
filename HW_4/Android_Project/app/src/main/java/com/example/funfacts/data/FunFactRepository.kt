package com.example.funfacts.data

import com.example.funfacts.data.local.FunFactDao
import com.example.funfacts.data.remote.FunFactDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

class FunFactsRepository private constructor(
    private val dao: FunFactDao,
    private val client: HttpClient
) {
    fun facts(): Flow<List<FunFactEntity>> = dao.observeFacts()

    suspend fun fetchAndStoreRandomFact() {
        // Fetch from API
        val dto: FunFactDto = client.get("https://uselessfacts.jsph.pl/random.json?language=en").body()

        // Map to Room entity and save
        val entity = FunFactEntity(
            text = dto.text,
            source = dto.source,
            fetchedAtMillis = System.currentTimeMillis()
        )
        dao.insert(entity)
    }

    companion object {
        @Volatile private var INSTANCE: FunFactsRepository? = null
        fun getInstance(dao: FunFactDao, client: HttpClient): FunFactsRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: FunFactsRepository(dao, client).also { INSTANCE = it }
            }
    }
}
