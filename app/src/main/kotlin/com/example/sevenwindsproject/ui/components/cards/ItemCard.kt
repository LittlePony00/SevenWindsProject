package com.example.sevenwindsproject.ui.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.sevenwindsproject.R
import com.example.sevenwindsproject.ui.theme.ButtonColor
import com.example.sevenwindsproject.ui.theme.CardColor
import com.example.sevenwindsproject.ui.theme.LocalDimensions
import com.example.sevenwindsproject.ui.theme.PrimaryMain
import com.example.sevenwindsproject.ui.theme.SfUiDisplayNormal15

@Composable
fun ItemCard(
    name: String,
    price: Int,
    imageURL: String,
    modifier: Modifier = Modifier,
) {
    val dimensions = LocalDimensions.current
    val horizontalPadding = Modifier.padding(horizontal = dimensions.standardSpacer)

    Card(
        modifier = modifier.fillMaxSize(),
        elevation = CardDefaults.cardElevation(dimensions.standardCardElevation),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(dimensions.standardSpacer)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.coffee3),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )

            Text(
                modifier = horizontalPadding,
                text = name,
                style = SfUiDisplayNormal15.copy(PrimaryMain)
            )

            Row(
                modifier = horizontalPadding.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(dimensions.standardSpacer)
            ) {
                Text(
                    text = "$price руб",
                    style = SfUiDisplayNormal15.copy(ButtonColor)
                )

                Icon(
                    modifier = Modifier.size(dimensions.normalSize),
                    painter = painterResource(id = R.drawable.minus),
                    tint = CardColor,
                    contentDescription = null
                )
                Text(
                    text = "2",
                    style = SfUiDisplayNormal15.copy(ButtonColor)
                )
                Icon(
                    modifier = Modifier.size(dimensions.normalSize),
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = null,
                    tint = CardColor
                )
            }
            Spacer(modifier = Modifier.height(dimensions.standardSpacer))
        }
    }
}

@Preview
@Composable
fun ItemCardPreview() {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.Center,
        content = {
            items(10) {
                ItemCard(
                    modifier = Modifier.padding(4.dp),
                    name = "Капучино",
                    price = 200,
                    imageURL = "https://example.com/image.jpg"
                )
            }
        }
    )
}
