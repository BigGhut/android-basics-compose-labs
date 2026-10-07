package com.example.kotlinpractice.practice

class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
        val builder = StringBuilder()
        builder.appendLine("Name: $name")
        builder.appendLine("Age: $age")
        if (hobby != null) {
            builder.append("Likes to $hobby. ")
        }
        if (referrer != null) {
            builder.append("Has a referrer named ${referrer.name}")
            if (referrer.hobby != null) {
                builder.append(", who likes to ${referrer.hobby}.")
            } else {
                builder.append(".")
            }
        } else {
            builder.append("Doesn't have a referrer.")
        }
        DeviceConsole.log(builder.toString().trimEnd())
    }
}

fun runInternetProfile() {
    val vitaliy = Person("Vitaliy", 23, "build ML models and Android apps", null)
    val atiqah = Person("Atiqah", 28, "climb", vitaliy)

    vitaliy.showProfile()
    atiqah.showProfile()
}
