package com.example.kotlinpractice.practice

import org.junit.Assert.assertEquals
import org.junit.Test

class PracticeProblemsTest {

    @Test
    fun mobileNotifications_printsExactAndCappedCounts() {
        assertEquals(
            listOf(
                "You have 51 notifications.",
                "Your phone is blowing up! You have 99+ notifications.",
            ),
            captureDeviceLog { runMobileNotifications() },
        )
    }

    @Test
    fun movieTicketPrice_usesAgeAndMondayDiscount() {
        assertEquals(
            listOf(
                "The movie ticket price for a person aged 5 is \$15.",
                "The movie ticket price for a person aged 28 is \$25.",
                "The movie ticket price for a person aged 87 is \$20.",
            ),
            captureDeviceLog { runMovieTicketPrice() },
        )
        assertEquals(30, ticketPrice(28, isMonday = false))
        assertEquals(-1, ticketPrice(120, isMonday = false))
    }

    @Test
    fun temperatureConverter_usesTrailingLambdas() {
        assertEquals(
            listOf(
                "27.0 degrees Celsius is 80.60 degrees Fahrenheit.",
                "350.0 degrees Kelvin is 76.85 degrees Celsius.",
                "10.0 degrees Fahrenheit is 260.93 degrees Kelvin.",
            ),
            captureDeviceLog { runTemperatureConverter() },
        )
    }

    @Test
    fun songCatalog_printsDescriptionAndPopularity() {
        assertEquals(
            listOf(
                "We Don't Talk About Bruno, performed by Encanto Cast, was released in 2022.",
                "true",
            ),
            captureDeviceLog { runSongCatalog() },
        )
        assertEquals(false, Song("Demo", "Artist", 2020, 999).isPopular)
    }

    @Test
    fun internetProfile_handlesNullableHobbyAndReferrer() {
        assertEquals(
            listOf(
                "Name: Vitaliy",
                "Age: 33",
                "Likes to build ML models and Android apps. Doesn't have a referrer.",
                "Name: Atiqah",
                "Age: 28",
                "Likes to climb. Has a referrer named Vitaliy, who likes to build ML models and Android apps.",
            ),
            captureDeviceLog { runInternetProfile() },
        )
    }

    @Test
    fun foldablePhone_staysOffWhenFolded() {
        assertEquals(
            listOf(
                "The phone screen's light is off.",
                "The phone screen's light is on.",
            ),
            captureDeviceLog { runFoldablePhones() },
        )
    }

    @Test
    fun specialAuction_usesSafeCallAndElvis() {
        assertEquals(
            listOf(
                "Item A is sold at 5000.",
                "Item B is sold at 3000.",
            ),
            captureDeviceLog { runSpecialAuction() },
        )
    }
}
