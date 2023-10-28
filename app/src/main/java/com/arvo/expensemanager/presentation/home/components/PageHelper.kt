package com.arvo.expensemanager.presentation.home.components

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
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
import com.arvo.expensemanager.R
import com.arvo.expensemanager.app.theme.ExpenseManagerColor
import com.arvo.expensemanager.app.theme.ExpenseManagerTypography
import com.arvo.expensemanager.app.theme.colorGreen900
import com.arvo.expensemanager.data.local.HomeBook
import com.arvo.expensemanager.domain.model.Book
import java.text.DecimalFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageItem(
    item: HomeBook,
    formatter: DateTimeFormatter,
    onCLick: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val instant = Instant.ofEpochMilli(item.book.timestamp)
    val temporalAccessor = instant.atZone(ZoneId.systemDefault())

    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }


    Card(
        onClick = onCLick,
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
            val (icon, mainData, amount, moreBtn,menu, description) = createRefs()

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
                    text = "${item.book.title.uppercase(Locale.ROOT).first()}",
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
                    text = item.book.title,
                    style = ExpenseManagerTypography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = formatter.format(temporalAccessor),
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
                text = DecimalFormat("#.##").format(item.total).toString(),
                style = ExpenseManagerTypography.titleMedium.copy(
                    color = if (item.total >= 0) colorGreen900 else ExpenseManagerColor.error
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
            IconButton(
                onClick = { expanded = true },
                modifier = Modifier
                    .constrainAs(moreBtn) {
                        top.linkTo(amount.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(amount.bottom)
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = "more button",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(8.dp),
                    tint = ExpenseManagerColor.outline,
                )
            }
            Box(modifier = Modifier
                .constrainAs(menu) {
                    top.linkTo(moreBtn.bottom)
                    end.linkTo(parent.end)
                }){
                DropdownMenu(
                    expanded = expanded ,
                    onDismissRequest = { expanded = false },
                ) {
                    DropdownMenuItem(
                        onClick = onEdit
                    ){
                        Text(text = "Edit")
                    }
                    DropdownMenuItem(
                        onClick = onDelete
                    ){

                        Text(text = "Delete")
                    }
                }
            }

            // Description (Updated Date)
            Text(
                text = item.book.description,
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PageHelperPreview(){
    PageItem(HomeBook(
        Book(
            1,
            "Room Expense",
            "All the expenses related to room items or groceries All the expenses related to room items or groceries All the expenses related to room items or groceries",
            1634969058000L
        ),
        234.3
    ), DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm a"),{},{},{}
    )
}