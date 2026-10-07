package com.example.trickortreat.lambda

val trick = {
    DeviceConsole.log("No treats!")
}

val treat: () -> Unit = {
    DeviceConsole.log("Have a treat!")
}

fun trickOrTreat(isTrick: Boolean, extraTreat: ((Int) -> String)?): () -> Unit {
    if (isTrick) {
        return trick
    } else {
        if (extraTreat != null) {
            DeviceConsole.log(extraTreat(5))
        }
        return treat
    }
}

fun runCodelabDemo() {
    val treatFunction = trickOrTreat(false) { "$it quarters" }
    val trickFunction = trickOrTreat(true, null)
    repeat(4) {
        treatFunction()
    }
    trickFunction()
}
