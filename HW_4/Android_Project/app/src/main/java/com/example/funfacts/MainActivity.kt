package com.example.funfacts

import android.os.Bundle
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.funfacts.data.FunFactEntity
import com.example.funfacts.ui.FactsViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: FactsViewModel by viewModels {
        val app = application as FunFactsApp
        FactsViewModel.Factory(app.repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(Modifier.fillMaxSize()) {
                    val state by viewModel.uiState.collectAsState()
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(onClick = { viewModel.fetchNewFact() }) {
                            Text("Fetch new fun fact")
                        }

                        Text("Previously fetched facts:", style = MaterialTheme.typography.titleMedium)

                        FactsList(facts = state.facts)
                    }
                }
            }
        }
    }
}

@androidx.compose.runtime.Composable
fun FactsList(facts: List<FunFactEntity>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(facts) { fact ->
            Text("• " + fact.text)
        }
    }
}
