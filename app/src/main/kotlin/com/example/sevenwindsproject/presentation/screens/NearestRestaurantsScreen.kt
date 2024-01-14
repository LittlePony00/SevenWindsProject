package com.example.sevenwindsproject.presentation.screens

import PrimaryButton
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sevenwindsproject.navigation.main.MainScreen
import com.example.sevenwindsproject.network.model.ShopLocationModel
import com.example.sevenwindsproject.presentation.viewmodels.main.NearestRestaurantsScreenViewModel
import com.example.sevenwindsproject.ui.theme.CardColor
import com.example.sevenwindsproject.ui.theme.FieldIndicatorColor
import com.example.sevenwindsproject.ui.theme.LocalDimensions
import com.example.sevenwindsproject.ui.theme.SecondaryText
import com.example.sevenwindsproject.ui.theme.SfUiDisplayNormal15
import com.example.sevenwindsproject.ui.theme.SfUiDisplayNormal18
import org.koin.androidx.compose.koinViewModel

@Composable
fun NearestRestaurantsScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: NearestRestaurantsScreenViewModel
) {
    val lazyListState = rememberLazyListState()
    val dimensions = LocalDimensions.current
    val list = List(12) {
        ShopLocationModel(
            id = it,
            name = "Coffix",
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
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navHostController.navigate(MainScreen.MenuScreen.route) {
                            popUpTo(0) {
                                inclusive = true
                                saveState = false
                            }
                        }
                    },
                colors = CardDefaults.cardColors(
                    containerColor = CardColor,
                    contentColor = FieldIndicatorColor
                ),
                elevation = CardDefaults.cardElevation(dimensions.standardCardElevation),
                shape = RoundedCornerShape(dimensions.nearestRestaurantShape)
            ) {
                Text(
                    modifier = Modifier.padding(
                        start = dimensions.standardSpacer,
                        bottom = dimensions.nearestRestaurantTextSpacer,
                        top = dimensions.standardSpacer
                    ),
                    text = list[it].name,
                    style = SfUiDisplayNormal18
                )
                Text(
                    modifier = Modifier.padding(
                        start = dimensions.standardSpacer,
                        bottom = dimensions.standardSpacer
                    ),
                    text = "500 м",
                    style = SfUiDisplayNormal15.copy(SecondaryText)
                )
            }
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
                text = "На карте",
                onButtonClick = {
                    navHostController.navigate(MainScreen.GoogleMapScreen.route)
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

@Preview
@Composable
fun NearestRestaurantsScreenPreview() {
    NearestRestaurantsScreen(
        modifier = Modifier.padding(vertical = 12.dp),
        navHostController = rememberNavController(),
        viewModel = koinViewModel<NearestRestaurantsScreenViewModel>()
    )
}
