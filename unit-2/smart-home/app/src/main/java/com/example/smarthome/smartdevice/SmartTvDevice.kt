package com.example.smarthome.smartdevice

class SmartTvDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart TV"

    private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)
    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)

    val volume: Int
        get() = speakerVolume

    val channel: Int
        get() = channelNumber

    fun increaseSpeakerVolume() {
        speakerVolume++
        DeviceConsole.log("Speaker volume increased to $speakerVolume.")
    }

    fun decreaseVolume() {
        speakerVolume--
        DeviceConsole.log("Speaker volume decreased to $speakerVolume.")
    }

    fun nextChannel() {
        channelNumber++
        DeviceConsole.log("Channel number increased to $channelNumber.")
    }

    fun previousChannel() {
        channelNumber--
        DeviceConsole.log("Channel number decreased to $channelNumber.")
    }

    override fun turnOn() {
        super.turnOn()
        DeviceConsole.log(
            "$name is turned on. Speaker volume is set to $speakerVolume and channel number is " +
                "set to $channelNumber.",
        )
    }

    override fun turnOff() {
        super.turnOff()
        DeviceConsole.log("$name turned off")
    }
}
