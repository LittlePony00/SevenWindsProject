package com.example.sevenwindsproject.presentation.screens

import PrimaryButton
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.sevenwindsproject.network.model.ShopLocationModel
import com.example.sevenwindsproject.ui.components.cards.OrderItem
import com.example.sevenwindsproject.ui.theme.LocalDimensions

@Composable
fun OrderScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    val dimensions = LocalDimensions.current
    val list = List(12) {
        ShopLocationModel(
            id = it,
            name = "Капучино",
            point = Pair(0f, 0f)
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimensions.horizontalPadding),
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(dimensions.standardSpacer)
    ) {
        item {
            Spacer(modifier = Modifier.height(dimensions.standardSpacer))
        }
        items(list.size) {
            OrderItem(
                name = list[it].name,
                price = "500 руб",
                onClick = {
                    //Do nothing
                }
            )
        }
        item {
            Spacer(modifier = Modifier.height(dimensions.standardSpacer))
        }
    }
    AnimatedVisibility(
        visible = lazyListState.isScrollingUp(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensions.horizontalPadding),
            contentAlignment = Alignment.BottomCenter
        ) {
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Оплатить",
                onButtonClick = {
                    //Do nothing
                }
            )
        }
    }
}

@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableIntStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableIntStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}
