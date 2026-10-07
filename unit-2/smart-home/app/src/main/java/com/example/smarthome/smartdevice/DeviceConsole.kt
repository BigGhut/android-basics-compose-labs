package com.example.smarthome.smartdevice

object DeviceConsole {
    fun interface Listener {
        fun onMessage(message: String)
    }

    @Volatile
    var listener: Listener? = null

    fun log(message: String) {
        println(message)
        listener?.onMessage(message)
    }
}

internal fun captureDeviceLog(action: () -> Unit): List<String> {
    val captured = mutableListOf<String>()
    val previous = DeviceConsole.listener
    DeviceConsole.listener = DeviceConsole.Listener { line ->
        if (line.isNotBlank()) {
            captured += line
        }
    }
    try {
        action()
    } finally {
        DeviceConsole.listener = previous
    }
    return captured
}
