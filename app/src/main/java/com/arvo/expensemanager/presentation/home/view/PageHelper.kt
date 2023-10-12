package com.arvo.expensemanager.presentation.home.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arvo.expensemanager.R
import com.arvo.expensemanager.app.theme.ExpenseManagerColor
import com.arvo.expensemanager.app.theme.ExpenseManagerTypography
import com.arvo.expensemanager.app.theme.colorGreen900
import com.arvo.expensemanager.model.dto.PageDto.PageDto
import com.arvo.expensemanager.presentation.Routes
import com.arvo.expensemanager.presentation.home.HomeActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageItem(item: PageDto, context: Context, onCLick: (Int) -> Unit) {
    Card(
        onClick = {
            onCLick(item.id)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp),
        colors = CardDefaults.cardColors(
            containerColor = ExpenseManagerColor.surface,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp,
        ),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(12.dp),
        ) {
            val (icon, mainData, amount, moreBtn, description) = createRefs()

            // Icon
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(ExpenseManagerColor.secondaryContainer)
                    .constrainAs(icon) {
                        top.linkTo(mainData.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(mainData.bottom)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${item.title.toUpperCase().first()}",
                    style = ExpenseManagerTypography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = ExpenseManagerColor.primary
                    )
                )
            }

            // Main Data (Title and Date)
            Column(
                modifier = Modifier
                    .padding(8.dp, 4.dp, 0.dp, 10.dp)
                    .constrainAs(mainData) {
                        top.linkTo(parent.top)
                        start.linkTo(icon.end)
                    }
            ) {
                Text(
                    text = "${item.title}",
                    style = ExpenseManagerTypography.titleMedium.copy(
                        fontWeight =  FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = if (item.isUpdated) "Updated at ${item.date}" else "Created at ${item.date}",
                    style = ExpenseManagerTypography.labelMedium.copy(
                        color = ExpenseManagerColor.outline
                    ),
                    modifier = Modifier
                        .padding(top = 3.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Amount
            Text(
                text = "${item.amount}",
                style = ExpenseManagerTypography.titleMedium.copy(
                    color = if(item.amount >= 0) colorGreen900 else ExpenseManagerColor.error
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .constrainAs(amount) {
                        top.linkTo(mainData.top)
                        end.linkTo(moreBtn.start)
                        bottom.linkTo(mainData.bottom)
                    }
            )

            // More Button
            Icon(
                painter = painterResource(id = R.drawable.ic_more),
                contentDescription = "more button",
                modifier = Modifier
                    .size(30.dp)
                    .padding(8.dp)
                    .constrainAs(moreBtn) {
                        top.linkTo(amount.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(amount.bottom)
                    },
                tint = ExpenseManagerColor.outline
            )

            // Description (Updated Date)
            Text(
                text = "${item.description}",
                style = ExpenseManagerTypography.titleSmall.copy(
                    color = ExpenseManagerColor.outline
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 12.dp)
                    .constrainAs(description) {
                        top.linkTo(icon.bottom)
                        start.linkTo(icon.start)
                        end.linkTo(parent.end)
                    },
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}

@Preview
@Composable
fun PageHelperPreview(){
    PageItem(PageDto(
        1,
        "Room Expense",
        "All the expenses related to room items or groceries All the expenses related to room items or groceries All the expenses related to room items or groceries",
        "Oct 8, 2023",
        true,
        125.75
    ), LocalContext.current,{}
    )
}