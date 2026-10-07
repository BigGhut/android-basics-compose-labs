package com.example.smarthome.smartdevice

open class SmartDevice(val name: String, val category: String) {

    var deviceStatus = "online"
        protected set

    open val deviceType = "unknown"

    open fun turnOn() {
        deviceStatus = "on"
    }

    open fun turnOff() {
        deviceStatus = "off"
    }

    fun printDeviceInfo() {
        DeviceConsole.log("Device name: $name, category: $category, type: $deviceType")
    }
}
