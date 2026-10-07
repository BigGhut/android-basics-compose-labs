package com.example.smarthome.smartdevice

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SmartHomeTest {

    @Test
    fun challengeDemo_printsExpectedOutput() {
        val output = captureDeviceLog { runCodelabChallengeDemo() }

        assertEquals(
            listOf(
                "Android TV is turned on. Speaker volume is set to 2 and channel number is set to 1.",
                "Google Light turned on. The brightness level is 2.",
                "Device name: Android TV, category: Entertainment, type: Smart TV",
                "Speaker volume decreased to 1.",
                "Channel number decreased to 0.",
                "Device name: Google Light, category: Utility, type: Smart Light",
                "Brightness decreased to 1.",
            ),
            output,
        )
    }

    @Test
    fun smartHome_ignoresActionsWhenDeviceIsOff() {
        val home = SmartHome(
            SmartTvDevice("Android TV", "Entertainment"),
            SmartLightDevice("Google Light", "Utility"),
        )

        val output = captureDeviceLog {
            home.increaseTvVolume()
            home.decreaseTvVolume()
            home.changeTvChannelToNext()
            home.increaseLightBrightness()
            home.turnOffAllDevices()
        }

        assertTrue(output.isEmpty())
        assertEquals(0, home.deviceTurnOnCount)
        assertEquals("online", home.smartTvDevice.deviceStatus)
        assertEquals("online", home.smartLightDevice.deviceStatus)
    }

    @Test
    fun deviceTurnOnCount_tracksOnOffWithoutDoubleCount() {
        val home = SmartHome(
            SmartTvDevice("Android TV", "Entertainment"),
            SmartLightDevice("Google Light", "Utility"),
        )

        home.turnOnTv()
        home.turnOnTv()
        home.turnOnLight()
        assertEquals(2, home.deviceTurnOnCount)

        home.turnOffTv()
        home.turnOffTv()
        assertEquals(1, home.deviceTurnOnCount)

        home.turnOffAllDevices()
        assertEquals(0, home.deviceTurnOnCount)
    }

    @Test
    fun rangeRegulator_ignoresValuesOutsideRange() {
        val tv = SmartTvDevice("Android TV", "Entertainment")
        repeat(200) { tv.decreaseVolume() }
        assertEquals(0, tv.volume)
        repeat(200) { tv.increaseSpeakerVolume() }
        assertEquals(100, tv.volume)
    }
}
