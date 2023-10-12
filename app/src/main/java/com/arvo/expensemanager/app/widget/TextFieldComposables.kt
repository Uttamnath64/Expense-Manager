package com.arvo.expensemanager.app.widget

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.arvo.expensemanager.app.theme.ExpenseManagerTypography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComposable(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    errorText: String = "",
    imeAction: ImeAction = ImeAction.Next,
    maxLines: Int = 1
) {
    val text = remember { mutableStateOf(TextFieldValue(value)) }
    OutlinedTextField(
        modifier = modifier,
        value = text.value.text,
        onValueChange = {
            text.value = TextFieldValue(it)
            onValueChange(it)
        },
        label = {
            Text(text = label, style = ExpenseManagerTypography.bodyMedium)
        },
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = imeAction
        ),
        isError = isError,
        supportingText = {
            if (isError) {
                ErrorTextInputField(text = errorText)
            }
        },
        shape = CircleShape
    )

}