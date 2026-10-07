package com.example.kotlinpractice

import androidx.lifecycle.ViewModel
import com.example.kotlinpractice.practice.captureDeviceLog
import com.example.kotlinpractice.practice.runAllPracticeProblems
import com.example.kotlinpractice.practice.runFoldablePhones
import com.example.kotlinpractice.practice.runInternetProfile
import com.example.kotlinpractice.practice.runMobileNotifications
import com.example.kotlinpractice.practice.runMovieTicketPrice
import com.example.kotlinpractice.practice.runSongCatalog
import com.example.kotlinpractice.practice.runSpecialAuction
import com.example.kotlinpractice.practice.runTemperatureConverter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PracticeUiState(
    val selectedProblem: String? = null,
    val log: List<String> = emptyList(),
)

class PracticeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PracticeUiState())
    val uiState: StateFlow<PracticeUiState> = _uiState.asStateFlow()

    fun runNotifications() = runProblem("Mobile notifications", ::runMobileNotifications)
    fun runTickets() = runProblem("Movie-ticket price", ::runMovieTicketPrice)
    fun runTemperature() = runProblem("Temperature converter", ::runTemperatureConverter)
    fun runSong() = runProblem("Song catalog", ::runSongCatalog)
    fun runProfile() = runProblem("Internet profile", ::runInternetProfile)
    fun runPhones() = runProblem("Foldable phones", ::runFoldablePhones)
    fun runAuction() = runProblem("Special auction", ::runSpecialAuction)
    fun runAll() = runProblem("All problems", ::runAllPracticeProblems)

    fun clearLog() {
        _uiState.value = PracticeUiState()
    }

    private fun runProblem(title: String, action: () -> Unit) {
        val captured = captureDeviceLog(action)
        _uiState.update {
            PracticeUiState(
                selectedProblem = title,
                log = captured,
            )
        }
    }
}
