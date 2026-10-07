package com.example.kotlinpractice.practice

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
    DeviceConsole.listener = DeviceConsole.Listener { message ->
        message.split('\n')
            .map { it.trimEnd() }
            .filter { it.isNotBlank() }
            .forEach { captured += it }
    }
    try {
        action()
    } finally {
        DeviceConsole.listener = previous
    }
    return captured
}
