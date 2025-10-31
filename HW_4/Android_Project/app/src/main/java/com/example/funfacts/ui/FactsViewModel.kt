package com.example.funfacts.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.funfacts.data.FunFactEntity
import com.example.funfacts.data.FunFactsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class UiState(
    val facts: List<FunFactEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class FactsViewModel(private val repo: FunFactsRepository) : ViewModel() {

    val uiState: StateFlow<UiState> =
        repo.facts()
            .map { list -> UiState(facts = list) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UiState())

    fun fetchNewFact() {
        viewModelScope.launch {
            try {
                repo.fetchAndStoreRandomFact()
            } catch (e: Exception) {
                // Keep it simple: we don't crash; optional: log
            }
        }
    }

    class Factory(private val repo: FunFactsRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass.isAssignableFrom(FactsViewModel::class.java))
            return FactsViewModel(repo) as T
        }
    }
}
