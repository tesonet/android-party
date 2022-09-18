package com.ac.androidparty.core.components.loading

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ac.androidparty.R
import com.ac.androidparty.core.components.CircularProgressBarComponent

@OptIn(ExperimentalAnimationApi::class, ExperimentalLifecycleComposeApi::class)
@Composable
internal fun LoadingComponent(
    isLoading: Boolean,
    viewModel: LoadingComponentViewModel = hiltViewModel()
) {
    val animationState by viewModel.state.collectAsStateWithLifecycle()

    val animatedVisibilityState = remember {
        MutableTransitionState(false)
    }.apply { targetState = true }

    if (isLoading) viewModel.load()

    AnimatedVisibility(
        visibleState = animatedVisibilityState,
        enter = fadeIn(initialAlpha = 0f),
        exit = fadeOut()
    ) {
        animatedVisibilityState.targetState = animationState
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_loading_bg),
                contentDescription = stringResource(R.string.loading_background_content_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            LoadingBarComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
            )

            LoadingTextComponent(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun LoadingBarComponent(modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircularProgressBarComponent(
            isDisplayed = true
        )
    }
}

@Composable
private fun LoadingTextComponent(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.loading_screen_loading_text),
            modifier = modifier,
            color = Color.White
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.15f))
    }
}