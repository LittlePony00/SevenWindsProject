
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sevenwindsproject.ui.theme.ButtonColor
import com.example.sevenwindsproject.ui.theme.LocalDimensions
import com.example.sevenwindsproject.ui.theme.OnButtonColor
import com.example.sevenwindsproject.ui.theme.PrimaryMain
import com.example.sevenwindsproject.ui.theme.SfUiDisplayNormal18

@Composable
fun PrimaryButton(
    text: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes leftIcon: Int? = null,
    @DrawableRes rightIcon: Int? = null,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
) {
    val dimensions = LocalDimensions.current
    val spacerModifier = Modifier.width(dimensions.paddingSmall)

    Button(
        modifier = modifier,
        shape = RoundedCornerShape(size = dimensions.standardShape),
        onClick = {
            onButtonClick()
        },
        contentPadding = PaddingValues(
            start = dimensions.standardShape,
            top = dimensions.standardSpacer,
            bottom = dimensions.standardSpacer,
            end = dimensions.standardShape
        ),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = ButtonColor,
            contentColor = OnButtonColor,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Gray
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(dimensions.normalSize)
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterVertically),
                color = PrimaryMain,
                strokeCap = StrokeCap.Round
            )
        } else {
            leftIcon?.let {
                Icon(
                    painterResource(id = it),
                    contentDescription = null
                )
            }
            Spacer(modifier = spacerModifier)
            Text(
                text = text,
                style = SfUiDisplayNormal18.copy(
                    color = OnButtonColor
                )
            )
            Spacer(modifier = spacerModifier)
            rightIcon?.let {
                Icon(
                    painterResource(id = it),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPrimaryButton() {
    PrimaryButton(
        text = "Регистрация",
        isEnabled = true,
        isLoading = false,
        onButtonClick = {}
    )
}
