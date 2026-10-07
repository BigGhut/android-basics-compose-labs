package com.example.trickortreat

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
import com.example.trickortreat.ui.theme.TrickOrTreatTheme

@Composable
fun TrickOrTreatRoute(
    viewModel: TrickOrTreatViewModel = viewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    TrickOrTreatScreen(
        state = state,
        onTreatQuarters = viewModel::knockForTreatWithQuarters,
        onTreatCupcake = viewModel::knockForTreatWithCupcake,
        onTrick = viewModel::knockForTrick,
        onRunDemo = viewModel::runOfficialDemo,
        onClearLog = viewModel::clearLog,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrickOrTreatScreen(
    state: TrickOrTreatUiState,
    onTreatQuarters: () -> Unit,
    onTreatCupcake: () -> Unit,
    onTrick: () -> Unit,
    onRunDemo: () -> Unit,
    onClearLog: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Trick or Treat") })
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
                text = "Function types and lambda expressions",
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = "trickOrTreat() takes a nullable extraTreat: ((Int) -> String)? and returns () -> Unit. Treat uses a trailing lambda. Trick passes null. Demo uses repeat(4).",
                style = MaterialTheme.typography.bodyMedium,
            )

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text("Knock on a door", style = MaterialTheme.typography.titleLarge)
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Button(onClick = onTreatQuarters) { Text("Treat + quarters") }
                        OutlinedButton(onClick = onTreatCupcake) { Text("Treat + cupcake") }
                        OutlinedButton(onClick = onTrick) { Text("Trick") }
                    }
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Button(onClick = onRunDemo) { Text("Run official demo") }
                        TextButton(onClick = onClearLog) { Text("Clear log") }
                    }
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text("Console", style = MaterialTheme.typography.titleMedium)
                    if (state.log.isEmpty()) {
                        Text(
                            "Tap a door. Official demo prints 5 quarters, then Have a treat! four times, then No treats!",
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
private fun TrickOrTreatScreenPreview() {
    TrickOrTreatTheme {
        TrickOrTreatScreen(
            state = TrickOrTreatUiState(
                log = listOf(
                    "5 quarters",
                    "Have a treat!",
                    "Have a treat!",
                    "Have a treat!",
                    "Have a treat!",
                    "No treats!",
                ),
            ),
            onTreatQuarters = {},
            onTreatCupcake = {},
            onTrick = {},
            onRunDemo = {},
            onClearLog = {},
        )
    }
}
