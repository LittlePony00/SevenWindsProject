package com.example.sevenwindsproject.presentation.screens

import PrimaryButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sevenwindsproject.navigation.main.MainScreen
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
    val dimensions = LocalDimensions.current
    val list = viewModel.getRestaurantsList()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = dimensions.horizontalPadding),
        verticalArrangement = Arrangement.spacedBy(dimensions.standardSpacer)
    ) {
        items(viewModel.getRestaurantsList().size) {
            Card(
                modifier = Modifier.fillMaxWidth(),
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
    }
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

@Preview
@Composable
fun NearestRestaurantsScreenPreview() {
    NearestRestaurantsScreen(
        modifier = Modifier.padding(vertical = 12.dp),
        navHostController = rememberNavController(),
        viewModel = koinViewModel<NearestRestaurantsScreenViewModel>()
    )
}
