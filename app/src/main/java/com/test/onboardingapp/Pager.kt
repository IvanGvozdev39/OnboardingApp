package com.test.onboardingapp

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.test.onboardingapp.screens.FinalOnboardingScreen
import com.test.onboardingapp.screens.FirstScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Pager(navController: NavController, context: Context) {
    val horizontalPagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val abelFont = FontFamily(Font(R.font.abel_regular, FontWeight.Light))

    val title1s = listOf(
        stringResource(id = R.string.title1_1),
        stringResource(id = R.string.title1_2),
        stringResource(id = R.string.title1_3),
        stringResource(id = R.string.title1_4)
    )
    val title2s = listOf(
        stringResource(id = R.string.title2_1),
        stringResource(id = R.string.title2_2),
        stringResource(id = R.string.title2_3),
        stringResource(id = R.string.title2_4)
    )
    val backgroundColors = listOf(
        colorResource(id = R.color.yellow),
        colorResource(id = R.color.purple),
        colorResource(id = R.color.peach),
        colorResource(id = R.color.blue)
    )
    val images = listOf(
        painterResource(id = R.drawable.img1),
        painterResource(id = R.drawable.img2),
        painterResource(id = R.drawable.img3),
        painterResource(id = R.drawable.img4)
    )

    LaunchedEffect(key1 = horizontalPagerState.currentPage) {
        if (horizontalPagerState.currentPage <= 3) {
            while (true) {
                setStatusBarColor(
                    context as Activity,
                    backgroundColors[horizontalPagerState.currentPage],
                    darkIcons = false
                )
                delay(200)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.yellow))
    ) {
        HorizontalPager(pageCount = title1s.size + 1, state = horizontalPagerState) { currentPage ->
            if (currentPage <= 3)
                FirstScreen(
                    navController = navController,
                    backgroundColor = backgroundColors[currentPage],
                    title1 = title1s[currentPage],
                    title2 = title2s[currentPage],
                    image = images[currentPage]
                )
            else
                FinalOnboardingScreen()
        }

        if (horizontalPagerState.currentPage <= 3) {
            Column(
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .align(Alignment.BottomStart)
            ) {
                DotsIndicator(
                    totalDots = title1s.size,
                    selectedIndex = horizontalPagerState.currentPage,
                    whiteTransparentColor = colorResource(id = R.color.white_transparent),
                    modifier = Modifier.padding(start = 24.dp)
                )
                TextButton(
                    onClick = {
                        navController.navigate(Screen.FinalOnboardingScreen.route)
                    },
                    modifier = Modifier.padding(start = 10.dp, top = 8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.skip),
                        color = colorResource(id = R.color.white),
                        fontFamily = abelFont,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start
                    )
                }
            }

            Box(
                modifier = Modifier
                    .padding(24.dp)
                    .padding(bottom = 12.dp)
                    .size(72.dp)
                    .align(Alignment.BottomEnd),
                contentAlignment = Alignment.Center
            ) {
                val progress = animateFloatAsState(
                    targetValue = when (horizontalPagerState.currentPage) {
                        0 -> 0f
                        1 -> 0.33f
                        2 -> 0.66f
                        3 -> 1f
                        else -> 0f
                    },
                    animationSpec = androidx.compose.animation.core.tween(durationMillis = 300)
                )

                CircularProgressIndicator(
                    progress = progress.value,
                    modifier = Modifier.size(68.dp),
                    color = Color.White,
                    backgroundColor = colorResource(id = R.color.white_transparent),
                    strokeWidth = 4.dp
                )

                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            if (horizontalPagerState.currentPage < title1s.size - 1) {
                                horizontalPagerState.animateScrollToPage(
                                    horizontalPagerState.currentPage + 1
                                )
                            } else {
                                //The last page action
                                navController.navigate(Screen.FinalOnboardingScreen.route)
                            }
                        }
                    },
                    modifier = Modifier.size(48.dp),
                    backgroundColor = colorResource(id = R.color.white),
                    contentColor = colorResource(id = R.color.black)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_forward),
                        contentDescription = "arrowRight"
                    )
                }
            }
        }
    }
}

fun setStatusBarColor(activity: Activity, color: Color, darkIcons: Boolean) {
    val window: Window = activity.window
    val view: View = window.decorView

    window.statusBarColor = color.toArgb()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val controller: WindowInsetsController? = window.insetsController
        controller?.setSystemBarsAppearance(
            if (darkIcons) WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS else 0,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
    } else {
        @Suppress("DEPRECATION")
        view.systemUiVisibility = if (darkIcons) {
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            0
        }
    }
}
