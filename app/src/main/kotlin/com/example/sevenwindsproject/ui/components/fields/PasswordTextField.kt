package com.example.sevenwindsproject.ui.components.fields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sevenwindsproject.R
import com.example.sevenwindsproject.ui.theme.FieldIndicatorColor
import com.example.sevenwindsproject.ui.theme.LocalDimensions
import com.example.sevenwindsproject.ui.theme.PrimaryMain
import com.example.sevenwindsproject.ui.theme.SfUiDisplayNormal15
import com.example.sevenwindsproject.ui.theme.SfUiDisplayNormal18


@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    placeholderText: String? = null,
    errorText: String? = null,
    maxCharCount: Int? = null,
    maxLines: Int = 1,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    onTextChange: (password: String) -> Unit
) {
    var passwordValue by rememberSaveable { mutableStateOf(value) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val dimensions = LocalDimensions.current

    OutlinedTextField(
        modifier = modifier,
        value = passwordValue,
        onValueChange = {
            if (it.length <= (maxCharCount ?: (it.length + 1))) {
                passwordValue = it
                onTextChange(it)
            }
        },
        visualTransformation = if (isPasswordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        textStyle = SfUiDisplayNormal15.copy(
            color = PrimaryMain
        ),
        placeholder = {
            placeholderText?.let {
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
            unfocusedIndicatorColor = FieldIndicatorColor,
            focusedIndicatorColor = FieldIndicatorColor,
            unfocusedTextColor = PrimaryMain,
            focusedTextColor = PrimaryMain
        ),
        trailingIcon = {
            if (isPasswordVisible) {
                IconButton(
                    onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }
                ) {
                    Icon(
                        painterResource(id = R.drawable.icon_visibility_on),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            } else {
                IconButton(
                    onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }
                ) {
                    Icon(
                        painterResource(id = R.drawable.icon_visibility_off),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
        },
        supportingText = {
            errorText?.let {
                if (isError) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = it,
                        style = SfUiDisplayNormal15,
                        textAlign = TextAlign.Start
                    )
                }
            }
        },
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        shape = RoundedCornerShape(size = dimensions.standardShape)
    )
}

@Preview
@Composable
fun PasswordTextFieldPreview() {
    Surface {
        PasswordTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholderText = "Введите пароль",
            errorText = "Максимум символов",
            isEnabled = true,
            isError = false,
            onTextChange = {}
        )
    }
}
