package com.example.trickortreat.lambda

import org.junit.Assert.assertEquals
import org.junit.Test

class TrickOrTreatTest {

    @Test
    fun officialDemo_printsExpectedOutput() {
        val output = captureDeviceLog { runCodelabDemo() }

        assertEquals(
            listOf(
                "5 quarters",
                "Have a treat!",
                "Have a treat!",
                "Have a treat!",
                "Have a treat!",
                "No treats!",
            ),
            output,
        )
    }

    @Test
    fun treat_usesTrailingLambdaAndReturnsTreatFunction() {
        val output = captureDeviceLog {
            val treatFunction = trickOrTreat(false) { "$it quarters" }
            treatFunction()
        }

        assertEquals(listOf("5 quarters", "Have a treat!"), output)
    }

    @Test
    fun trick_skipsNullableExtraTreat() {
        val output = captureDeviceLog {
            val trickFunction = trickOrTreat(true, null)
            trickFunction()
        }

        assertEquals(listOf("No treats!"), output)
    }

    @Test
    fun cupcakeLambda_isPassedAsFunctionArgument() {
        val cupcake: (Int) -> String = { "Have a cupcake!" }
        val output = captureDeviceLog {
            val treatFunction = trickOrTreat(false, cupcake)
            treatFunction()
        }

        assertEquals(listOf("Have a cupcake!", "Have a treat!"), output)
    }
}
