package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

private val CardBackground = Color(0xFFD2E8D4)
private val LogoBackground = Color(0xFF073042)
private val AccentGreen = Color(0xFF006D3B)
private val NameColor = Color(0xFF1B1C18)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme(darkTheme = false, dynamicColor = false) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    BusinessCardApp()
                }
            }
        }
    }
}

@Composable
fun BusinessCardApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CardBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileSection()
        }
        ContactSection(
            modifier = Modifier.padding(bottom = 32.dp)
        )
    }
}

@Composable
private fun ProfileSection() {
    Box(
        modifier = Modifier
            .background(LogoBackground)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.android_logo),
            contentDescription = stringResource(R.string.android_logo_description),
            modifier = Modifier.size(96.dp)
        )
    }
    Text(
        text = stringResource(R.string.full_name),
        fontSize = 40.sp,
        fontWeight = FontWeight.Light,
        color = NameColor,
        modifier = Modifier.padding(top = 8.dp)
    )
    Text(
        text = stringResource(R.string.title),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = AccentGreen
    )
}

@Composable
private fun ContactSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        ContactRow(
            icon = Icons.Default.Call,
            text = stringResource(R.string.phone_number),
            contentDescription = stringResource(R.string.phone_icon_description)
        )
        Spacer(modifier = Modifier.height(16.dp))
        ContactRow(
            icon = Icons.Default.Share,
            text = stringResource(R.string.social_handle),
            contentDescription = stringResource(R.string.share_icon_description)
        )
        Spacer(modifier = Modifier.height(16.dp))
        ContactRow(
            icon = Icons.Default.Email,
            text = stringResource(R.string.email),
            contentDescription = stringResource(R.string.email_icon_description)
        )
    }
}

@Composable
private fun ContactRow(
    icon: ImageVector,
    text: String,
    contentDescription: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = AccentGreen,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            color = NameColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme(darkTheme = false, dynamicColor = false) {
        BusinessCardApp()
    }
}
