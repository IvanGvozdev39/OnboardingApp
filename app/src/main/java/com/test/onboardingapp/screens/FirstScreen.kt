package com.test.onboardingapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.test.onboardingapp.R

@Composable
fun FirstScreen(
    navController: NavController,
    backgroundColor: Color,
    title1: String,
    title2: String,
    image: Painter
) {
    val abelFont = FontFamily(Font(R.font.abel_regular, FontWeight.Light))

    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .background(color = backgroundColor)
            ) {
                Text(
                    text = title1,
                    color = colorResource(id = R.color.white),
                    fontFamily = abelFont,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(top = 34.dp, bottom = 12.dp)
                )

                Text(
                    text = title2,
                    color = colorResource(id = R.color.white),
                    fontFamily = abelFont,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start
                )
            }

            Image(
                painter = image,
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                contentScale = ContentScale.Crop
            )

        }
    }
}