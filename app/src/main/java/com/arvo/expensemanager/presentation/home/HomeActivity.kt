package com.arvo.expensemanager.presentation.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arvo.expensemanager.R
import com.arvo.expensemanager.app.theme.ExpenseManagerColor
import com.arvo.expensemanager.app.theme.ExpenseManagerTypography
import com.arvo.expensemanager.presentation.Routes
import com.arvo.expensemanager.presentation.home.components.PageItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SuppressLint("WeekBasedYear")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeActivity(
    nevController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("MMM dd, Y")
    val bookTime = DateTimeFormatter.ofPattern("MMM dd, Y h:mm a")

    val state = viewModel.state.value

    ConstraintLayout(
        modifier = Modifier
            .padding(top = 20.dp, start = 15.dp, end = 15.dp)
            .fillMaxSize()
    ) {

        val (welcomeBox, spacer1, nav, floatingBtn) = createRefs()

        // Welcome Box
        Column(
            modifier = Modifier
                .padding(top = 30.dp, start = 8.dp)
                .constrainAs(welcomeBox) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        ) {
            Text(
                text = "Welcome",
                style = ExpenseManagerTypography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = formatter.format(currentDate),
                style = ExpenseManagerTypography.labelLarge.copy(
                    color = ExpenseManagerColor.outline
                )
            )
        }

        // Spacer 1
        Spacer(
            modifier = Modifier
                .size(30.dp)
                .constrainAs(spacer1) {
                    top.linkTo(welcomeBox.bottom)
                    start.linkTo(parent.start)
                }
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(nav) {
                    top.linkTo(spacer1.bottom)
                    start.linkTo(parent.start)
                }
        ) {
            LazyColumn{
                itemsIndexed(state.homeBooks) { _, item ->
                    PageItem(
                        item = item,
                        formatter = bookTime,
                        onCLick = {
                            nevController.navigate(Routes.VIEW_BOOK_SCREEN+"?bookId=${item.book.id}")
                        },
                        onEdit = {
                            nevController.navigate(Routes.ADD_EDIT_BOOK_SCREEN+"?bookId=${item.book.id}")
                        },
                        onDelete = {
                            viewModel.onEvent(HomeEvent.DeleteBook(item.book))
                        }
                    )
                }
            }

        }

        // Floating Button
        FloatingActionButton(
            onClick = { nevController.navigate(Routes.ADD_EDIT_BOOK_SCREEN) },
            shape = CircleShape,
            modifier = Modifier
                .constrainAs(floatingBtn){
                    bottom.linkTo(parent.bottom,15.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
            containerColor = ExpenseManagerColor.primary
        ) {
            Icon(
                modifier = Modifier
                    .size(45.dp),
                painter = painterResource(id = R.drawable.ic_plus),
                tint = ExpenseManagerColor.background,
                contentDescription = ""
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HomeActivityPreview(){
    HomeActivity(nevController = rememberNavController())
}