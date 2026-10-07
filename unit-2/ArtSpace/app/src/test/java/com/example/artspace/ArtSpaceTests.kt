package com.example.artspace

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ArtSpaceTests {

    @Test
    fun artworks_hasExpectedArtworks() {
        assertEquals(4, artworks.size)
        assertTrue(artworks.all { it.imageRes > 0 && it.titleRes > 0 && it.artistRes > 0 && it.yearRes > 0 })
    }

    @Test
    fun nextNavigation_cyclesThroughAllAndWraps() {
        var index = 0
        val lastIndex = artworks.lastIndex

        // Advance next
        index = if (index < lastIndex) index + 1 else 0
        assertEquals(1, index)

        index = if (index < lastIndex) index + 1 else 0
        assertEquals(2, index)

        index = if (index < lastIndex) index + 1 else 0
        assertEquals(3, index)

        // Wraps to 0
        index = if (index < lastIndex) index + 1 else 0
        assertEquals(0, index)
    }

    @Test
    fun previousNavigation_wrapsToLastIndexWhenAtZero() {
        var index = 0
        val lastIndex = artworks.lastIndex

        // Previous from 0 wraps to 3
        index = if (index > 0) index - 1 else lastIndex
        assertEquals(3, index)

        index = if (index > 0) index - 1 else lastIndex
        assertEquals(2, index)
    }
}
