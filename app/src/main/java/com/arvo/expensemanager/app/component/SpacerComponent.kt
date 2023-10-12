package com.arvo.expensemanager.app.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
    Composable Spacer
 */

@Composable
fun DefaultSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(4.dp)
)

@Composable
fun ExtraSmallSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(6.dp)
)

@Composable
fun SmallSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(12.dp)
)

@Composable
fun MediumSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(18.dp)
)

@Composable
fun LargeSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(24.dp)
)

@Composable
fun ExtraLargeSpacer() = Spacer(
    modifier = Modifier
        .fillMaxWidth()
        .height(30.dp)
)