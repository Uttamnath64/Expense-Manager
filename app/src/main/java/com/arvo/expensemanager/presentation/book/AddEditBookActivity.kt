package com.arvo.expensemanager.presentation.book

import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arvo.expensemanager.app.theme.ExpenseManagerColor
import com.arvo.expensemanager.app.theme.ExpenseManagerTheme
import com.arvo.expensemanager.app.theme.ExpenseManagerTypography
import com.arvo.expensemanager.app.widget.TextFieldComposable
import com.arvo.expensemanager.app.widget.TopBarComposable
import com.arvo.expensemanager.presentation.book.events.AddEditBookEvent
import com.arvo.expensemanager.presentation.book.viewModels.AddEditBookViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditBookActivity(
    nevController: NavHostController,
    viewModel: AddEditBookViewModel = hiltViewModel()
) {


    val bookTitleState = viewModel.bookTitle.value
    val bookDescriptionState = viewModel.bookDescription.value

    val isActive = (bookTitleState.trim() != "" )

    val screenType = viewModel.screenType

    Scaffold(
        topBar = {
            TopBarComposable(text = if(screenType.value == 0) "Add Book" else "Edit Book", nevController)
        },
    ) { padding ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            val (data, btn) = createRefs()
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
                    text = bookTitleState,
                    onValueChange = {
                        viewModel.onEvent(AddEditBookEvent.EnteredTitle(it))
                    },
                    hint = "Enter name"
                )
                TextFieldComposable(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    text = bookDescriptionState,
                    onValueChange = {
                         viewModel.onEvent(AddEditBookEvent.EnteredDescription(it))
                    },
                    hint = "Enter description"
                )
            }

            Button(
                enabled = isActive,
                onClick = {
                    if (isActive){
                        viewModel.onEvent(AddEditBookEvent.SaveBook)
                        nevController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .constrainAs(btn) {
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    },
                shape = CircleShape,
                colors =  ButtonDefaults.outlinedButtonColors(ExpenseManagerColor.primary)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "Save",
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
fun AddEditBookActivityPreview(){
    ExpenseManagerTheme {
        Surface {
            AddEditBookActivity(rememberNavController()
            )
        }
    }
}