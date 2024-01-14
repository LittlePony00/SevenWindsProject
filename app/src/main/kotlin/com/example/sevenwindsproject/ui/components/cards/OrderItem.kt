package com.example.sevenwindsproject.ui.components.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sevenwindsproject.R
import com.example.sevenwindsproject.ui.theme.CardColor
import com.example.sevenwindsproject.ui.theme.FieldIndicatorColor
import com.example.sevenwindsproject.ui.theme.LocalDimensions
import com.example.sevenwindsproject.ui.theme.PrimaryMain
import com.example.sevenwindsproject.ui.theme.SecondaryText
import com.example.sevenwindsproject.ui.theme.SfUiDisplayNormal15
import com.example.sevenwindsproject.ui.theme.SfUiDisplayNormal18

@Composable
fun OrderItem(
    name: String,
    price: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val dimensions = LocalDimensions.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = CardColor,
            contentColor = FieldIndicatorColor
        ),
        elevation = CardDefaults.cardElevation(dimensions.standardCardElevation),
        shape = RoundedCornerShape(dimensions.nearestRestaurantShape)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                modifier = Modifier.padding(top = 9.dp),
                text = name,
                style = SfUiDisplayNormal18.copy(FieldIndicatorColor)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(9.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.minus),
                    contentDescription = null
                )

                Text(
                    text = "2",
                    style = SfUiDisplayNormal15.copy(fontSize = 15.sp, color = PrimaryMain)
                )

                Icon(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier.padding(bottom = 9.dp),
                text = price,
                style = SfUiDisplayNormal18.copy(SecondaryText)
            )
        }
    }
}

@Preview
@Composable
fun OrderItemPreview() {
    OrderItem(
        modifier = Modifier
            .padding(top = 100.dp)
            .padding(horizontal = 16.dp),
        name = "Капучино",
        price = "200 руб"
    ) {}
}
//Column(modifier = Modifier.padding(12.dp)) {
//    Text(
//        modifier = Modifier.padding(top = 9.dp),
//        text = name,
//        style = SfUiDisplayNormal18.copy(FieldIndicatorColor)
//    )
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.spacedBy(9.dp)
//    ) {
//        Spacer(modifier = Modifier.weight(1f))
//        Icon(
//            painter = painterResource(id = R.drawable.minus),
//            contentDescription = null
//        )
//
//        Text(
//            text = "2",
//            style = SfUiDisplayNormal15.copy(fontSize = 15.sp, color = PrimaryMain)
//        )
//
//        Icon(
//            painter = painterResource(id = R.drawable.plus),
//            contentDescription = null
//        )
//    }
//    Text(
//        modifier = Modifier.padding(bottom = 9.dp),
//        text = price,
//        style = SfUiDisplayNormal18.copy(SecondaryText)
//    )
//}
