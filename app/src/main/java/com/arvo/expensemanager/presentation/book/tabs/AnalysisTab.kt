package com.arvo.expensemanager.presentation.book.tabs

import android.os.Build
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arvo.expensemanager.app.theme.ExpenseManagerColor
import com.arvo.expensemanager.app.theme.ExpenseManagerTypography
import com.arvo.expensemanager.app.theme.colorGreen
import com.arvo.expensemanager.app.theme.colorGreen900
import com.arvo.expensemanager.app.theme.colorRed
import com.arvo.expensemanager.app.theme.colorRed900
import com.arvo.expensemanager.app.widget.CustomTab
import com.arvo.expensemanager.presentation.book.viewModels.AnalysisViewModel
import java.text.DecimalFormat
import java.time.Instant
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AnalysisTab(
    nevController: NavController,
    bookId: Int,
    viewModel: AnalysisViewModel = hiltViewModel(),
) {
    val analysis = viewModel.state.value


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(ExpenseManagerColor.background)
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
                    .fillMaxHeight(),
                colors = CardDefaults.cardColors(
                    containerColor = ExpenseManagerColor.primary,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 1.dp,
                ),
                shape = RoundedCornerShape(20.dp)
            ){
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp)
                ) {
                    var (title, total, lable, caseIn, caseOut) = createRefs()
                    Text(
                        text = "Analysis",
                        style = ExpenseManagerTypography.labelLarge.copy(
                            color = ExpenseManagerColor.surface
                        ),
                        modifier = Modifier
                            .constrainAs(title){
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                            }
                    )
                    Text(
                        text = DecimalFormat("#.##").format(analysis.total).toString(),
                        style = ExpenseManagerTypography.titleLarge.copy(
                            fontSize = 32.sp,
                            color = ExpenseManagerColor.background,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .constrainAs(total){
                                top.linkTo(title.bottom)
                                bottom.linkTo(lable.top)
                            }
                    )
                    Text(
                        text = "last month",
                        style = ExpenseManagerTypography.labelLarge.copy(
                            color = ExpenseManagerColor.surface
                        ),
                        modifier = Modifier
                            .constrainAs(lable){
                                start.linkTo(parent.start)
                                bottom.linkTo(caseIn.top, 5.dp)
                            }
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .constrainAs(caseIn){
                                start.linkTo(parent.start)
                                bottom.linkTo(caseOut.top, 2.dp)
                            }
                    ) {
                        LinearProgressIndicator(
                            progress = 0.0f,
                            color = colorGreen,
                            modifier = Modifier
                                .height(8.dp)
                                .weight(1f)
                                .clip(RoundedCornerShape(16.dp)),
                        )
                        Text(
                            text = "0%",
                            style = ExpenseManagerTypography.labelLarge.copy(
                                color = ExpenseManagerColor.surface
                            ),
                            modifier = Modifier
                                .padding(start = 8.dp),
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .constrainAs(caseOut){
                                start.linkTo(parent.start)
                                bottom.linkTo(parent.bottom,10.dp)
                            }
                    ) {
                        LinearProgressIndicator(
                            progress = 0.0f,
                            color = colorRed,
                            modifier = Modifier
                                .height(8.dp)
                                .weight(1f)
                                .clip(RoundedCornerShape(16.dp)),
                        )
                        Text(
                            text = "0%",
                            style = ExpenseManagerTypography.labelLarge.copy(
                                color = ExpenseManagerColor.surface
                            ),
                            modifier = Modifier
                                .padding(start = 8.dp),
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .weight(.75f)
                    .fillMaxHeight(),
            ){
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                        .height(80.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = ExpenseManagerColor.surface,
                    ),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 1.dp,
                    ),
                ){
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        var (total,lable) = createRefs()
                        Text(
                            text = DecimalFormat("#.##").format(analysis.cashInTotalThisMonth).toString(),
                            style = ExpenseManagerTypography.titleLarge.copy(
                                fontSize = 28.sp,
                                color = colorGreen,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .constrainAs(total){
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start,8.dp)
                                    bottom.linkTo(lable.top)
                                }
                        )
                        Text(
                            text = "This month all case in.",
                            style = ExpenseManagerTypography.labelMedium,
                            modifier = Modifier
                                .constrainAs(lable) {
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                }
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                        .height(80.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = ExpenseManagerColor.surface,
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 1.dp,
                    ),
                ){
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        var (total,lable) = createRefs()
                        Text(
                            text = DecimalFormat("#.##").format(analysis.cashOutTotalThisMonth).toString(),
                            style = ExpenseManagerTypography.titleLarge.copy(
                                fontSize = 28.sp,
                                color = colorRed,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .constrainAs(total){
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start,8.dp)
                                    bottom.linkTo(lable.top)
                                }
                        )
                        Text(
                            text = "This month all case out.",
                            style = ExpenseManagerTypography.labelMedium,
                            modifier = Modifier
                                .constrainAs(lable) {
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                }
                        )
                    }
                }
            }
        }
        Text(
            text = "Payment Method",
            style = ExpenseManagerTypography.bodyMedium,
            modifier = Modifier
                .padding(10.dp, 10.dp)
        )
        Card(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = ExpenseManagerColor.surface,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 1.dp,
            ),
        ) {
            Row(
                modifier = Modifier.padding(5.dp,15.dp)
            ) {
                Text(
                    text = "Cash",
                    style = ExpenseManagerTypography.labelMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
                Text(
                    text = DecimalFormat("#.##").format(analysis.cash).toString(),
                    style = ExpenseManagerTypography.labelMedium.copy(
                        color = colorGreen900,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    textAlign = TextAlign.End
                )
            }
        }
        Card(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = ExpenseManagerColor.surface,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 1.dp,
            ),
        ) {
            Row(
                modifier = Modifier.padding(5.dp,15.dp)
            ) {
                Text(
                    text = "UPI",
                    style = ExpenseManagerTypography.labelMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
                Text(
                    text = DecimalFormat("#.##").format(analysis.upi).toString(),
                    style = ExpenseManagerTypography.labelMedium.copy(
                        color = colorGreen900,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    textAlign = TextAlign.End
                )
            }
        }
        Card(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = ExpenseManagerColor.surface,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 1.dp,
            ),
        ) {
            Row(
                modifier = Modifier.padding(5.dp,15.dp)
            ) {
                Text(
                    text = "Back",
                    style = ExpenseManagerTypography.labelMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
                Text(
                    text = DecimalFormat("#.##").format(analysis.bank).toString(),
                    style = ExpenseManagerTypography.labelMedium.copy(
                        color = colorGreen900,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AnalysisTabPreview() {
    AnalysisTab(rememberNavController(), 0)
}