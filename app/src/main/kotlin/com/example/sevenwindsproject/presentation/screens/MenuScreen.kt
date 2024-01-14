package com.example.sevenwindsproject.presentation.screens

import PrimaryButton
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sevenwindsproject.navigation.main.MainScreen
import com.example.sevenwindsproject.network.model.MenuModel
import com.example.sevenwindsproject.presentation.viewmodels.main.MenuScreenViewModel
import com.example.sevenwindsproject.ui.components.cards.ItemCard
import com.example.sevenwindsproject.ui.theme.LocalDimensions
import org.koin.androidx.compose.koinViewModel

@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    viewModel: MenuScreenViewModel,
    navHostController: NavHostController
) {
    val state = rememberLazyGridState()
    val dimensions = LocalDimensions.current
    val list = List(12) {
        MenuModel(
            id = 0,
            price = 200,
            name = "Капучино",
            imageURL = "")
    }

    Box(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = dimensions.standardSpacer)
    ) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            state = state,
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.Center,
            content = {
                items(list.size) {
                    ItemCard(
                        modifier = Modifier.padding(4.dp),
                        name = list[it].name,
                        price = list[it].price,
                        imageURL = list[it].imageURL
                    )
                }
            }
        )

        AnimatedVisibility(
            visible = state.isScrollingUp(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .padding(vertical = dimensions.standardSpacer)
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Перейти к оплате",
                    onButtonClick = {
                        navHostController.navigate(MainScreen.OrderScreen.route) {
                            popUpTo(0) {
                                inclusive = true
                                saveState = false
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun LazyGridState.isScrollingUp(): Boolean {
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

@Preview
@Composable
fun MenuScreenPreview() {
    MenuScreen(
        viewModel = koinViewModel<MenuScreenViewModel>(),
        navHostController = rememberNavController()
    )
}
