package com.arvo.expensemanager.presentation.book

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arvo.expensemanager.app.theme.ExpenseManagerColor
import com.arvo.expensemanager.app.theme.ExpenseManagerTheme
import com.arvo.expensemanager.app.theme.ExpenseManagerTypography
import com.arvo.expensemanager.app.widget.TextFieldComposable
import com.arvo.expensemanager.app.widget.TopBarComposable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookActivity(onItemCreate: (Boolean) -> Unit, nevController: NavHostController) {

    val text = { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBarComposable(text = "Add Book", nevController)
        },
    ) { padding ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            var (data, btn) = createRefs()
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .constrainAs(data) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            ) {
                TextFieldComposable(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    value = "",
                    onValueChange = {

                    },
                    label = "Enter name",
                    isError = false,
                    errorText = "",
                    imeAction = ImeAction.Done
                )
                TextFieldComposable(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    value = "",
                    onValueChange = {

                    },
                    label = "Enter description",
                    isError = false,
                    errorText = "",
                    imeAction = ImeAction.Done,
                )
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .constrainAs(btn) {
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)

                    },
                shape = CircleShape,
                colors = ButtonDefaults.outlinedButtonColors(ExpenseManagerColor.primary)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Add",
                    style = ExpenseManagerTypography.titleMedium.copy(
                        color = ExpenseManagerColor.background
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun AddBookActivityPreview(){
    ExpenseManagerTheme {
        Surface {
            AddBookActivity({

            }, rememberNavController()
            )
        }
    }
}