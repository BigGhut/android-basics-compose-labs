/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

data class Artwork(
    @param:DrawableRes val imageRes: Int,
    @param:StringRes val titleRes: Int,
    @param:StringRes val artistRes: Int,
    @param:StringRes val yearRes: Int
)

val artworks = listOf(
    Artwork(
        imageRes = R.drawable.starry_night,
        titleRes = R.string.starry_night_title,
        artistRes = R.string.starry_night_artist,
        yearRes = R.string.starry_night_year
    ),
    Artwork(
        imageRes = R.drawable.mona_lisa,
        titleRes = R.string.mona_lisa_title,
        artistRes = R.string.mona_lisa_artist,
        yearRes = R.string.mona_lisa_year
    ),
    Artwork(
        imageRes = R.drawable.great_wave,
        titleRes = R.string.great_wave_title,
        artistRes = R.string.great_wave_artist,
        yearRes = R.string.great_wave_year
    ),
    Artwork(
        imageRes = R.drawable.girl_with_pearl,
        titleRes = R.string.girl_with_pearl_title,
        artistRes = R.string.girl_with_pearl_artist,
        yearRes = R.string.girl_with_pearl_year
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
    var currentArtworkIndex by remember { mutableIntStateOf(0) }
    val currentArtwork = artworks[currentArtworkIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .safeDrawingPadding()
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Section 1: Artwork Wall
        ArtworkWall(
            artwork = currentArtwork,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Section 2: Artwork Descriptor
        ArtworkDescriptor(
            artwork = currentArtwork,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Section 3: Display Controller
        DisplayController(
            onPrevious = {
                currentArtworkIndex = if (currentArtworkIndex > 0) {
                    currentArtworkIndex - 1
                } else {
                    artworks.lastIndex
                }
            },
            onNext = {
                currentArtworkIndex = if (currentArtworkIndex < artworks.lastIndex) {
                    currentArtworkIndex + 1
                } else {
                    0
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

/**
 * 1. Artwork Wall: Shows the artwork inside a framed, elevated surface.
 */
@Composable
fun ArtworkWall(
    artwork: Artwork,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shadowElevation = 8.dp,
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
    ) {
        Image(
            painter = painterResource(id = artwork.imageRes),
            contentDescription = stringResource(
                R.string.artwork_image_content_description,
                stringResource(artwork.titleRes),
                stringResource(artwork.artistRes)
            ),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .heightIn(max = 420.dp)
        )
    }
}

/**
 * 2. Artwork Descriptor: Displays the artwork title, artist, and year in a card.
 */
@Composable
fun ArtworkDescriptor(
    artwork: Artwork,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = artwork.titleRes),
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                lineHeight = 30.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            val detailsText = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(stringResource(id = artwork.artistRes))
                }
                append(" (")
                append(stringResource(id = artwork.yearRes))
                append(")")
            }
            Text(
                text = detailsText,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * 3. Display Controller: Previous and Next navigation buttons.
 */
@Composable
fun DisplayController(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onPrevious,
            modifier = Modifier
                .weight(1f)
                .height(48.dp)
        ) {
            Text(
                text = stringResource(id = R.string.previous_button),
                fontSize = 15.sp
            )
        }
        Spacer(modifier = Modifier.width(24.dp))
        Button(
            onClick = onNext,
            modifier = Modifier
                .weight(1f)
                .height(48.dp)
        ) {
            Text(
                text = stringResource(id = R.string.next_button),
                fontSize = 15.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceScreenPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}
