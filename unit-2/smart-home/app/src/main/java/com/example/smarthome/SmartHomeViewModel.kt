package com.example.smarthome

import androidx.lifecycle.ViewModel
import com.example.smarthome.smartdevice.SmartHome
import com.example.smarthome.smartdevice.SmartLightDevice
import com.example.smarthome.smartdevice.SmartTvDevice
import com.example.smarthome.smartdevice.captureDeviceLog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SmartHomeUiState(
    val tvName: String,
    val tvOn: Boolean,
    val volume: Int,
    val channel: Int,
    val lightName: String,
    val lightOn: Boolean,
    val brightness: Int,
    val devicesOn: Int,
    val log: List<String> = emptyList(),
)

class SmartHomeViewModel : ViewModel() {

    private val smartTv = SmartTvDevice("Android TV", "Entertainment")
    private val smartLight = SmartLightDevice("Google Light", "Utility")
    private val smartHome = SmartHome(smartTv, smartLight)

    private val _uiState = MutableStateFlow(snapshot(log = emptyList()))
    val uiState: StateFlow<SmartHomeUiState> = _uiState.asStateFlow()

    fun turnOnTv() = runLogged { smartHome.turnOnTv() }
    fun turnOffTv() = runLogged { smartHome.turnOffTv() }
    fun increaseTvVolume() = runLogged { smartHome.increaseTvVolume() }
    fun decreaseTvVolume() = runLogged { smartHome.decreaseTvVolume() }
    fun nextChannel() = runLogged { smartHome.changeTvChannelToNext() }
    fun previousChannel() = runLogged { smartHome.changeTvChannelToPrevious() }
    fun printTvInfo() = runLogged { smartHome.printSmartTvInfo() }

    fun turnOnLight() = runLogged { smartHome.turnOnLight() }
    fun turnOffLight() = runLogged { smartHome.turnOffLight() }
    fun increaseBrightness() = runLogged { smartHome.increaseLightBrightness() }
    fun decreaseBrightness() = runLogged { smartHome.decreaseLightBrightness() }
    fun printLightInfo() = runLogged { smartHome.printSmartLightInfo() }

    fun turnOffAll() = runLogged { smartHome.turnOffAllDevices() }

    fun runChallengeDemo() = runLogged {
        smartHome.turnOnTv()
        smartHome.turnOnLight()
        smartHome.printSmartTvInfo()
        smartHome.decreaseTvVolume()
        smartHome.changeTvChannelToPrevious()
        smartHome.printSmartLightInfo()
        smartHome.decreaseLightBrightness()
    }

    fun clearLog() {
        _uiState.update { snapshot(log = emptyList()) }
    }

    private fun runLogged(action: () -> Unit) {
        val captured = captureDeviceLog(action)
        _uiState.update { current -> snapshot(log = current.log + captured) }
    }

    private fun snapshot(log: List<String>): SmartHomeUiState {
        return SmartHomeUiState(
            tvName = smartTv.name,
            tvOn = smartTv.deviceStatus == "on",
            volume = smartTv.volume,
            channel = smartTv.channel,
            lightName = smartLight.name,
            lightOn = smartLight.deviceStatus == "on",
            brightness = smartLight.brightness,
            devicesOn = smartHome.deviceTurnOnCount,
            log = log,
        )
    }
}
