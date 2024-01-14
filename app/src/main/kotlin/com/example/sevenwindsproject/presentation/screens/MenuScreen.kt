package com.example.sevenwindsproject.presentation.screens

import PrimaryButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sevenwindsproject.presentation.viewmodels.main.MenuScreenViewModel
import com.example.sevenwindsproject.ui.components.cards.ItemCard
import com.example.sevenwindsproject.ui.theme.LocalDimensions
import org.koin.androidx.compose.koinViewModel

@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    viewModel: MenuScreenViewModel
) {
    val dimensions = LocalDimensions.current
    val list = viewModel.getMenuList()

    Box(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = dimensions.standardSpacer)
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.Center,
            content = {
                items(list.size) {
                    ItemCard(
                        modifier = Modifier.padding(4.dp),
                        name = list[it].name,
                        price = list[it].price
                    )
                }
            }
        )

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
                onButtonClick = {}
            )
        }
    }
}

@Preview
@Composable
fun MenuScreenPreview() {
    MenuScreen(viewModel = koinViewModel<MenuScreenViewModel>())
}
