package com.example.kotlinpractice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinpractice.ui.theme.PracticeTheme

@Composable
fun PracticeRoute(
    viewModel: PracticeViewModel = viewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    PracticeScreen(
        state = state,
        onNotifications = viewModel::runNotifications,
        onTickets = viewModel::runTickets,
        onTemperature = viewModel::runTemperature,
        onSong = viewModel::runSong,
        onProfile = viewModel::runProfile,
        onPhones = viewModel::runPhones,
        onAuction = viewModel::runAuction,
        onRunAll = viewModel::runAll,
        onClear = viewModel::clearLog,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeScreen(
    state: PracticeUiState,
    onNotifications: () -> Unit,
    onTickets: () -> Unit,
    onTemperature: () -> Unit,
    onSong: () -> Unit,
    onProfile: () -> Unit,
    onPhones: () -> Unit,
    onAuction: () -> Unit,
    onRunAll: () -> Unit,
    onClear: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Kotlin Fundamentals") })
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                "Practice problems — official solutions",
                style = MaterialTheme.typography.titleMedium,
            )

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text("Run a problem", style = MaterialTheme.typography.titleLarge)
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Button(onClick = onNotifications) { Text("Notifications") }
                        OutlinedButton(onClick = onTickets) { Text("Movie tickets") }
                        OutlinedButton(onClick = onTemperature) { Text("Temperature") }
                        OutlinedButton(onClick = onSong) { Text("Song catalog") }
                        OutlinedButton(onClick = onProfile) { Text("Internet profile") }
                        OutlinedButton(onClick = onPhones) { Text("Foldable phones") }
                        OutlinedButton(onClick = onAuction) { Text("Special auction") }
                    }
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Button(onClick = onRunAll) { Text("Run all") }
                        TextButton(onClick = onClear) { Text("Clear") }
                    }
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        text = state.selectedProblem ?: "Console",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    if (state.log.isEmpty()) {
                        Text(
                            "Tap a problem to print the official sample output.",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    } else {
                        state.log.forEach { line ->
                            Text(line, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PracticeScreenPreview() {
    PracticeTheme {
        PracticeScreen(
            state = PracticeUiState(
                selectedProblem = "Mobile notifications",
                log = listOf(
                    "You have 51 notifications.",
                    "Your phone is blowing up! You have 99+ notifications.",
                ),
            ),
            onNotifications = {},
            onTickets = {},
            onTemperature = {},
            onSong = {},
            onProfile = {},
            onPhones = {},
            onAuction = {},
            onRunAll = {},
            onClear = {},
        )
    }
}
