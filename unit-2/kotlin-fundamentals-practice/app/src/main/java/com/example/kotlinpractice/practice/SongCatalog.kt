package com.example.kotlinpractice.practice

class Song(
    val title: String,
    val artist: String,
    val yearPublished: Int,
    val playCount: Int,
) {
    val isPopular: Boolean
        get() = playCount >= 1000

    fun printDescription() {
        DeviceConsole.log("$title, performed by $artist, was released in $yearPublished.")
    }
}

fun runSongCatalog() {
    val brunoSong = Song("We Don't Talk About Bruno", "Encanto Cast", 2022, 1_000_000)
    brunoSong.printDescription()
    DeviceConsole.log(brunoSong.isPopular.toString())
}
