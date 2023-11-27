package com.arvo.expensemanager.app.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arvo.expensemanager.R
import com.arvo.expensemanager.app.theme.ExpenseManagerColor
import com.arvo.expensemanager.app.theme.ExpenseManagerTheme
import com.arvo.expensemanager.app.theme.ExpenseManagerTypography
import com.arvo.expensemanager.presentation.book.BookViewActivity

@Composable
fun TopBarComposable(
    text: String, navController: NavController,
    style: TextStyle = ExpenseManagerTypography.titleMedium.copy(
        color = ExpenseManagerColor.outline
    ),
    extraBtn: Boolean = false,
    content: @Composable () -> Unit = {},
){
    var expanded by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 5.dp, 10.dp, 0.dp),
    ) {
        val (backBtn, name, menuBtn, menu) = createRefs()
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .constrainAs(backBtn) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        ) {
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .padding(top = 4.dp, start = 2.dp, end = 4.dp, bottom = 4.dp),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = ExpenseManagerColor.outline,
            )
        }
        Text(
            modifier = Modifier
                .padding(start = 10.dp)
                .constrainAs(name) {
                    top.linkTo(backBtn.top)
                    bottom.linkTo(backBtn.bottom)
                    start.linkTo(backBtn.end)
                },
            text = text,
            style = style
        )
        if (extraBtn){
            IconButton(
                onClick = { expanded = true },
                modifier = Modifier
                    .constrainAs(menuBtn) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
            ) {
                Icon(
                    modifier = Modifier
                        .size(28.dp)
                        .padding(top = 4.dp, start = 2.dp, end = 4.dp, bottom = 4.dp),
                    painter =  painterResource(id = R.drawable.ic_more),
                    contentDescription = null,
                    tint = ExpenseManagerColor.outline,
                )
            }
            Box(modifier = Modifier
                .constrainAs(menu) {
                    top.linkTo(menuBtn.bottom)
                    end.linkTo(parent.end)
                }) {
                androidx.compose.material3.DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    content()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextBox(text: String, placeholder: String,onTextChanged: (String) -> Unit) {
    var textValue by remember { mutableStateOf(TextFieldValue(text)) }
    TextField(
        value = textValue,
        onValueChange = {
            textValue = it
            onTextChanged(it.toString())
        },
        label = {
            Text(text = placeholder, style = ExpenseManagerTypography.bodyMedium)
        },
        modifier = Modifier
            .padding(16.dp)
            .background(Color.Transparent),
    )
}