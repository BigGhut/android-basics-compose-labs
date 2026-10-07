package com.example.smarthome.smartdevice

class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice,
) {

    var deviceTurnOnCount = 0
        private set

    private val isTvOn: Boolean
        get() = smartTvDevice.deviceStatus == "on"

    private val isLightOn: Boolean
        get() = smartLightDevice.deviceStatus == "on"

    fun turnOnTv() {
        if (!isTvOn) {
            smartTvDevice.turnOn()
            deviceTurnOnCount++
        }
    }

    fun turnOffTv() {
        if (isTvOn) {
            smartTvDevice.turnOff()
            deviceTurnOnCount--
        }
    }

    fun increaseTvVolume() {
        if (isTvOn) {
            smartTvDevice.increaseSpeakerVolume()
        }
    }

    fun decreaseTvVolume() {
        if (isTvOn) {
            smartTvDevice.decreaseVolume()
        }
    }

    fun changeTvChannelToNext() {
        if (isTvOn) {
            smartTvDevice.nextChannel()
        }
    }

    fun changeTvChannelToPrevious() {
        if (isTvOn) {
            smartTvDevice.previousChannel()
        }
    }

    fun printSmartTvInfo() {
        smartTvDevice.printDeviceInfo()
    }

    fun turnOnLight() {
        if (!isLightOn) {
            smartLightDevice.turnOn()
            deviceTurnOnCount++
        }
    }

    fun turnOffLight() {
        if (isLightOn) {
            smartLightDevice.turnOff()
            deviceTurnOnCount--
        }
    }

    fun increaseLightBrightness() {
        if (isLightOn) {
            smartLightDevice.increaseBrightness()
        }
    }

    fun decreaseLightBrightness() {
        if (isLightOn) {
            smartLightDevice.decreaseBrightness()
        }
    }

    fun printSmartLightInfo() {
        smartLightDevice.printDeviceInfo()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}
