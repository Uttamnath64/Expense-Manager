package com.arvo.expensemanager.presentation.home

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arvo.expensemanager.R
import com.arvo.expensemanager.app.theme.ExpenseManagerColor
import com.arvo.expensemanager.app.theme.ExpenseManagerTypography
import com.arvo.expensemanager.model.dto.PageDto.PageDto
import com.arvo.expensemanager.presentation.Routes
import com.arvo.expensemanager.presentation.home.helper.PageItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeActivity(nevController: NavController) {

    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("MMM dd, Y")

    ConstraintLayout(
        modifier = Modifier
            .padding(top = 20.dp, start = 15.dp, end = 15.dp)
            .fillMaxSize()
    ) {

        val (welcomeBox, spacer1, nav, accountBtn, floatingBtn) = createRefs()

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

        // Account Button
        IconButton(
            onClick = {},
            modifier = Modifier
                .padding(end = 8.dp)
                .constrainAs(accountBtn) {
                    top.linkTo(welcomeBox.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(spacer1.bottom)
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_circle),
                contentDescription = "Account Icon",
                tint = ExpenseManagerColor.outline,
                modifier = Modifier
                    .size(28.dp)
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
                .constrainAs(nav){
                    top.linkTo(spacer1.bottom)
                    start.linkTo(parent.start)
                }
        ) {
//                    CustomTab(
//                        items = listOf("MUSIC", "PODCAST"),
//                        selectedItemIndex = selected,
//                        onClick = setSelected,
//                        tabWidth = 150.dp
//                    )

            CreatePageList(LocalContext.current, nevController)

        }

        // Floating Button
        FloatingActionButton(
            onClick = { nevController.navigate(Routes.ADD_BOOK_SCREEN) },
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreatePageList(context: Context, nevController: NavController) {
    var pageList: List<PageDto>
    pageList = ArrayList<PageDto>()


    pageList = listOf(
        PageDto(
            1,
            "Room Expense",
            "All the expenses related to room items or groceries",
            "Oct 8, 2023",
            true,
            125.75
        ),
        PageDto(
            2,
            "Transportation Costs",
            "Expenses for daily transportation, including fuel and public transit",
            "Oct 8, 2023",
            false,
            -54.20
        ),
        PageDto(
            3,
            "Dining Out",
            "Spending on restaurants and food outside the home",
            "Oct 8, 2023",
            true,
            98.50
        ),
        PageDto(
            4,
            "Entertainment",
            "Costs related to movies, concerts, and leisure activities",
            "Oct 8, 2023",
            false,
            -72.30
        ),
        PageDto(
            5,
            "Utilities",
            "Monthly utility bills, including electricity, water, and gas",
            "Oct 8, 2023",
            true,
            223.45
        ),
        PageDto(
            6,
            "Healthcare Expenses",
            "Medical bills, prescriptions, and healthcare-related costs",
            "Oct 8, 2023",
            false,
            -32.50
        ),
        PageDto(
            7,
            "Education Costs",
            "Tuition fees, textbooks, and educational expenses",
            "Oct 8, 2023",
            true,
            -92.80
        ),
        PageDto(
            8,
            "Home Maintenance",
            "Expenditures on home repairs and maintenance tasks",
            "Oct 8, 2023",
            false,
            -112.60
        ),
        PageDto(
            9,
            "Savings",
            "Allocations to savings accounts and investments",
            "Oct 8, 2023",
            true,
            342.90
        ),
        PageDto(
            10,
            "Miscellaneous",
            "Unspecified or miscellaneous expenses",
            "Oct 8, 2023",
            false,
            -15.75
        )
    )

    LazyColumn{
        itemsIndexed(pageList) { _, item ->
            PageItem(item, context) {
                nevController.navigate(Routes.BOOK_VIEW_SCREEN+"/${item.id}")
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HomeActivityPreview(){
    HomeActivity(nevController = rememberNavController())
}