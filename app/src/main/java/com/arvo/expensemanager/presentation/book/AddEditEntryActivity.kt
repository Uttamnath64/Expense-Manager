package com.arvo.expensemanager.presentation.book

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arvo.expensemanager.app.theme.ExpenseManagerColor
import com.arvo.expensemanager.app.theme.ExpenseManagerTheme
import com.arvo.expensemanager.app.theme.ExpenseManagerTypography
import com.arvo.expensemanager.app.widget.DropdownMenu
import com.arvo.expensemanager.app.widget.TextFieldComposable
import com.arvo.expensemanager.app.widget.TopBarComposable
import com.arvo.expensemanager.domain.model.Book
import com.arvo.expensemanager.presentation.book.events.AddEditEntryEvent
import com.arvo.expensemanager.presentation.book.viewModels.AddEditEntryViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookEntryActivity(
    nevController: NavHostController,
    viewModel: AddEditEntryViewModel = hiltViewModel(),
) {

    val entryTitle = viewModel.entryTitle.value
    val entryDescription = viewModel.entryDescription.value
    val entryAmount = viewModel.entryAmount.value
    val entryPaymentMethod = viewModel.entryPaymentMethod.value

    val screenType = viewModel.screenType.value

    val isActive = (entryTitle.trim() != "" && !entryAmount.isEmpty()  && entryAmount.toDoubleOrNull() != null)

    var style: TextStyle
    var topBarTitle: String
    var selectedIndex by remember { mutableIntStateOf(entryPaymentMethod) }


    style = ExpenseManagerTypography.titleMedium.copy(
        color = ExpenseManagerColor.outline
    )

    topBarTitle = "Edit Entry"

    when (screenType) {
        0 -> {
            style = ExpenseManagerTypography.titleMedium.copy(
                color = ExpenseManagerColor.tertiary
            )
            topBarTitle = "Add Cash In Entry"
        }
        1 -> {
            style = ExpenseManagerTypography.titleMedium.copy(
                color = ExpenseManagerColor.error
            )
            topBarTitle = "Add Cash Out Entry"
        }
        else -> {
            style = ExpenseManagerTypography.titleMedium.copy(
                color = ExpenseManagerColor.outline
            )
            topBarTitle = "Edit Entry"
        }
    }

    Scaffold(
        topBar = {
            TopBarComposable(
                text = topBarTitle,
                navController =  nevController,
                style = style,
                extraBtn = screenType == 2
            ){
                DropdownMenuItem(
                    onClick = {
                        viewModel.onEvent(AddEditEntryEvent.DeleteEntry)
                        nevController.popBackStack()
                    }
                ){
                    Text(text = "Delete")
                }
            }
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
                    text = entryTitle,
                    onValueChange = {
                        viewModel.onEvent(AddEditEntryEvent.EnteredTitle(it))
                    },
                    hint = "Enter title"
                )
                TextFieldComposable(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    text = entryDescription,
                    onValueChange = {
                        viewModel.onEvent(AddEditEntryEvent.EnteredDescription(it))
                    },
                    hint = "Enter description"
                )
                TextFieldComposable(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    text = entryAmount,
                    onValueChange = {
                        viewModel.onEvent(AddEditEntryEvent.EnteredAmount(it))
                    },
                    hint = "Enter amount",
                    type = KeyboardType.Number
                )
                DropdownMenu(
                    label = "Enter payment method",
                    items = listOf("Cash", "UPI", "Bank"),
                    selectedIndex = selectedIndex,
                    onItemSelected = {
                            index, _ -> selectedIndex = index
                            viewModel.onEvent(AddEditEntryEvent.SelectPaymentType(index))
                        },
                )
            }

            Button(
                enabled = isActive,
                onClick = {
                    viewModel.onEvent(AddEditEntryEvent.SaveEntry)
                    nevController.popBackStack()
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
fun AddBookEntryActivityPreview(){
    ExpenseManagerTheme {
        Surface {
            AddBookEntryActivity(rememberNavController()
            )
        }
    }
}