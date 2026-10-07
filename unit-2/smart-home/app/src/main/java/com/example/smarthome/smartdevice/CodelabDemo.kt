package com.example.smarthome.smartdevice

fun runCodelabSolutionDemo() {
    var smartDevice: SmartDevice = SmartTvDevice("Android TV", "Entertainment")
    smartDevice.turnOn()

    smartDevice = SmartLightDevice("Google Light", "Utility")
    smartDevice.turnOn()
}

fun runCodelabChallengeDemo() {
    val smartHome = SmartHome(
        smartTvDevice = SmartTvDevice("Android TV", "Entertainment"),
        smartLightDevice = SmartLightDevice("Google Light", "Utility"),
    )

    smartHome.turnOnTv()
    smartHome.turnOnLight()
    println()
    smartHome.printSmartTvInfo()
    smartHome.decreaseTvVolume()
    smartHome.changeTvChannelToPrevious()
    println()
    smartHome.printSmartLightInfo()
    smartHome.decreaseLightBrightness()
}
