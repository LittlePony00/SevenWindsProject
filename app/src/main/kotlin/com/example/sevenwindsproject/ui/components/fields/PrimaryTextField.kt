package com.example.sevenwindsproject.ui.components.fields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sevenwindsproject.R
import com.example.sevenwindsproject.ui.theme.LocalDimensions
import com.example.sevenwindsproject.ui.theme.PrimaryMain
import com.example.sevenwindsproject.ui.theme.SfUiDisplayNormal18

@Composable
fun PrimaryTextField(
    modifier: Modifier = Modifier,
    title: String? = null,
    value: String = "",
    description: String? = null,
    supportText: String? = null,
    errorText: String? = null,
    maxQuantityOfChar: Int? = null,
    maxLines: Int = 1,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    isOnlyNumbers: Boolean = false,
    onTextChange: (String) -> Unit
) {
    val spacerModifier = Modifier.height(4.dp)
    val dimensions = LocalDimensions.current
    val fillMaxWidthModifier = Modifier.fillMaxWidth()

    Column(modifier = modifier) {
        title?.let {
            Text(
                text = title,
                style = com.example.sevenwindsproject.ui.theme.SfUiDisplayNormal15.copy(color = com.example.sevenwindsproject.ui.theme.PrimaryMain)
            )
            Spacer(spacerModifier)
        }
        OutlinedTextField(
            modifier = fillMaxWidthModifier,
            value = value,
            onValueChange = {
                if (it.length <= (maxQuantityOfChar ?: (it.length + 1))) {
                    onTextChange(it)
                }
            },
            textStyle = com.example.sevenwindsproject.ui.theme.SfUiDisplayNormal18.copy(
                color = com.example.sevenwindsproject.ui.theme.PrimaryMain
            ),
            placeholder = {
                description?.let {
                    Text(
                        text = it,
                        style = SfUiDisplayNormal18.copy(color = PrimaryMain)
                    )
                }
            },
            enabled = isEnabled,
            isError = isError,
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                unfocusedIndicatorColor = com.example.sevenwindsproject.ui.theme.FieldIndicatorColor,
                focusedIndicatorColor = com.example.sevenwindsproject.ui.theme.FieldIndicatorColor,
                unfocusedTextColor = com.example.sevenwindsproject.ui.theme.PrimaryMain,
                focusedTextColor = com.example.sevenwindsproject.ui.theme.PrimaryMain
            ),
            supportingText = {
                if (isError) {
                    errorText?.let {
                        Text(
                            modifier = fillMaxWidthModifier,
                            text = it,
                            style = com.example.sevenwindsproject.ui.theme.SfUiDisplayNormal15,
                            textAlign = TextAlign.Start
                        )
                    }
                } else {
                    supportText?.let {
                        Text(
                            modifier = fillMaxWidthModifier,
                            text = it,
                            style = com.example.sevenwindsproject.ui.theme.SfUiDisplayNormal15,
                            textAlign = TextAlign.Start
                        )
                    }
                    maxQuantityOfChar?.let {
                        Text(
                            modifier = fillMaxWidthModifier,
                            text = stringResource(
                                id = R.string.limit_of_max_char,
                                value.length,
                                maxQuantityOfChar
                            ),
                            style = com.example.sevenwindsproject.ui.theme.SfUiDisplayNormal15,
                            textAlign = TextAlign.End
                        )
                    }
                }
            },
            maxLines = maxLines,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isOnlyNumbers) {
                    KeyboardType.Number
                } else {
                    KeyboardType.Text
                }
            ),
            shape = RoundedCornerShape(size = dimensions.standardShape)
        )
    }
}

@Preview
@Composable
fun PreviewPrimaryTextField() {
    Surface {
        PrimaryTextField(
            modifier = Modifier.width(500.dp),
            title = "Проект",
            description = "Введите имя",
            supportText = "Только латиница",
            maxQuantityOfChar = 20,
            isEnabled = true,
            onTextChange = {}
        )
    }
}
