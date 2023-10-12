package com.arvo.expensemanager.presentation.splash

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.arvo.expensemanager.R
import com.arvo.expensemanager.app.theme.ExpenseManagerColor
import com.arvo.expensemanager.app.theme.ExpenseManagerTheme
import com.arvo.expensemanager.app.theme.ExpenseManagerTypography
import com.arvo.expensemanager.presentation.MainActivity
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseManagerTheme {
                Surface{
                    SplashLayout()
                }
            }
        }
    }

    @Composable
    fun SplashLayout() {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val (logoAndName, companyName) = createRefs()
            Column(
                modifier = Modifier
                    .constrainAs(logoAndName) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arvo_png),
                    contentDescription = stringResource(id = R.string.splash_s_img_des),
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(ExpenseManagerColor.primary),
                    modifier = Modifier
                        .size(200.dp)
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .padding(top = 10.dp),
                    style = ExpenseManagerTypography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Text(
                text = stringResource(id = R.string.splash_s_company),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .constrainAs(companyName) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                style = ExpenseManagerTypography.labelLarge.copy(
                    color = ExpenseManagerColor.outline
                )
            )
        }

        LaunchedEffect(key1 = true){
            delay(1500)
            navToHome()
        }
    }

    private fun navToHome() {
        startActivity(Intent(
            this,
            MainActivity::class.java
        ))
        finish()
    }
    @Preview(showBackground = true, name = "Light Mode")
//    @Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark Mode")
    @Composable
    fun GreetingPreview2() {
        ExpenseManagerTheme{
            Surface {
                SplashLayout()
            }
        }
    }
}


