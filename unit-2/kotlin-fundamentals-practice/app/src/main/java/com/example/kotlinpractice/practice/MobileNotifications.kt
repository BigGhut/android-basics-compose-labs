package com.example.kotlinpractice.practice

fun runMobileNotifications() {
    val morningNotification = 51
    val eveningNotification = 135

    printNotificationSummary(morningNotification)
    printNotificationSummary(eveningNotification)
}

fun printNotificationSummary(numberOfMessages: Int) {
    if (numberOfMessages < 100) {
        DeviceConsole.log("You have $numberOfMessages notifications.")
    } else {
        DeviceConsole.log("Your phone is blowing up! You have 99+ notifications.")
    }
}
