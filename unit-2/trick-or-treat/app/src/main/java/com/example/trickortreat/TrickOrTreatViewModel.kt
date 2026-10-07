package com.example.trickortreat

import androidx.lifecycle.ViewModel
import com.example.trickortreat.lambda.captureDeviceLog
import com.example.trickortreat.lambda.runCodelabDemo
import com.example.trickortreat.lambda.trickOrTreat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class TrickOrTreatUiState(
    val log: List<String> = emptyList(),
)

class TrickOrTreatViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TrickOrTreatUiState())
    val uiState: StateFlow<TrickOrTreatUiState> = _uiState.asStateFlow()

    fun knockForTreatWithQuarters() = runLogged {
        val treatFunction = trickOrTreat(false) { "$it quarters" }
        treatFunction()
    }

    fun knockForTreatWithCupcake() = runLogged {
        val cupcake: (Int) -> String = { "Have a cupcake!" }
        val treatFunction = trickOrTreat(false, cupcake)
        treatFunction()
    }

    fun knockForTrick() = runLogged {
        val trickFunction = trickOrTreat(true, null)
        trickFunction()
    }

    fun runOfficialDemo() = runLogged { runCodelabDemo() }

    fun clearLog() {
        _uiState.update { it.copy(log = emptyList()) }
    }

    private fun runLogged(action: () -> Unit) {
        val captured = captureDeviceLog(action)
        _uiState.update { current -> current.copy(log = current.log + captured) }
    }
}
