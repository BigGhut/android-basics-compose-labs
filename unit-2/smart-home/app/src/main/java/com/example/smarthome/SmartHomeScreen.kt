package com.example.smarthome

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
import com.example.smarthome.ui.theme.SmartHomeTheme

@Composable
fun SmartHomeRoute(
    viewModel: SmartHomeViewModel = viewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    SmartHomeScreen(
        state = state,
        onTurnOnTv = viewModel::turnOnTv,
        onTurnOffTv = viewModel::turnOffTv,
        onVolumeUp = viewModel::increaseTvVolume,
        onVolumeDown = viewModel::decreaseTvVolume,
        onChannelNext = viewModel::nextChannel,
        onChannelPrevious = viewModel::previousChannel,
        onPrintTv = viewModel::printTvInfo,
        onTurnOnLight = viewModel::turnOnLight,
        onTurnOffLight = viewModel::turnOffLight,
        onBrightnessUp = viewModel::increaseBrightness,
        onBrightnessDown = viewModel::decreaseBrightness,
        onPrintLight = viewModel::printLightInfo,
        onTurnOffAll = viewModel::turnOffAll,
        onRunDemo = viewModel::runChallengeDemo,
        onClearLog = viewModel::clearLog,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartHomeScreen(
    state: SmartHomeUiState,
    onTurnOnTv: () -> Unit,
    onTurnOffTv: () -> Unit,
    onVolumeUp: () -> Unit,
    onVolumeDown: () -> Unit,
    onChannelNext: () -> Unit,
    onChannelPrevious: () -> Unit,
    onPrintTv: () -> Unit,
    onTurnOnLight: () -> Unit,
    onTurnOffLight: () -> Unit,
    onBrightnessUp: () -> Unit,
    onBrightnessDown: () -> Unit,
    onPrintLight: () -> Unit,
    onTurnOffAll: () -> Unit,
    onRunDemo: () -> Unit,
    onClearLog: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Smart Home") },
            )
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
                text = "Devices currently on: ${state.devicesOn}",
                style = MaterialTheme.typography.titleMedium,
            )

            DeviceCard(
                title = state.tvName,
                subtitle = if (state.tvOn) {
                    "Status: ON · volume ${state.volume} · channel ${state.channel}"
                } else {
                    "Status: OFF — press Turn on first"
                },
            ) {
                ActionRow {
                    if (state.tvOn) {
                        Button(onClick = onTurnOffTv) { Text("Turn off") }
                    } else {
                        Button(onClick = onTurnOnTv) { Text("Turn on") }
                    }
                    OutlinedButton(onClick = onVolumeDown, enabled = state.tvOn) { Text("Vol -") }
                    OutlinedButton(onClick = onVolumeUp, enabled = state.tvOn) { Text("Vol +") }
                    OutlinedButton(onClick = onChannelPrevious, enabled = state.tvOn) { Text("Ch -") }
                    OutlinedButton(onClick = onChannelNext, enabled = state.tvOn) { Text("Ch +") }
                    TextButton(onClick = onPrintTv) { Text("Print info") }
                }
            }

            DeviceCard(
                title = state.lightName,
                subtitle = if (state.lightOn) {
                    "Status: ON · brightness ${state.brightness}"
                } else {
                    "Status: OFF — press Turn on first"
                },
            ) {
                ActionRow {
                    if (state.lightOn) {
                        Button(onClick = onTurnOffLight) { Text("Turn off") }
                    } else {
                        Button(onClick = onTurnOnLight) { Text("Turn on") }
                    }
                    OutlinedButton(onClick = onBrightnessDown, enabled = state.lightOn) { Text("Dim") }
                    OutlinedButton(onClick = onBrightnessUp, enabled = state.lightOn) { Text("Brighten") }
                    TextButton(onClick = onPrintLight) { Text("Print info") }
                }
            }

            ActionRow {
                Button(onClick = onRunDemo) { Text("Run challenge demo") }
                OutlinedButton(onClick = onTurnOffAll) { Text("Turn off all") }
                TextButton(onClick = onClearLog) { Text("Clear log") }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text("Console", style = MaterialTheme.typography.titleMedium)
                    if (state.log.isEmpty()) {
                        Text(
                            "Press Turn on, then volume/channel/brightness will work. The power button switches to Turn off.",
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

@Composable
private fun DeviceCard(
    title: String,
    subtitle: String,
    content: @Composable () -> Unit,
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(title, style = MaterialTheme.typography.titleLarge)
            Text(subtitle, style = MaterialTheme.typography.bodyMedium)
            content()
        }
    }
}

@Composable
private fun ActionRow(content: @Composable () -> Unit) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun SmartHomeScreenPreview() {
    SmartHomeTheme {
        SmartHomeScreen(
            state = SmartHomeUiState(
                tvName = "Android TV",
                tvOn = true,
                volume = 2,
                channel = 1,
                lightName = "Google Light",
                lightOn = true,
                brightness = 2,
                devicesOn = 2,
                log = listOf("Android TV is turned on. Speaker volume is set to 2 and channel number is set to 1."),
            ),
            onTurnOnTv = {},
            onTurnOffTv = {},
            onVolumeUp = {},
            onVolumeDown = {},
            onChannelNext = {},
            onChannelPrevious = {},
            onPrintTv = {},
            onTurnOnLight = {},
            onTurnOffLight = {},
            onBrightnessUp = {},
            onBrightnessDown = {},
            onPrintLight = {},
            onTurnOffAll = {},
            onRunDemo = {},
            onClearLog = {},
        )
    }
}
