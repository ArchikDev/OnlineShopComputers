package com.ar4uk.onlineshopcomputers.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ar4uk.onlineshopcomputers.R
import com.ar4uk.onlineshopcomputers.core.Constants.SIDE_PADDING
import com.ar4uk.onlineshopcomputers.presentation.navigation.NavigationState
import com.ar4uk.onlineshopcomputers.presentation.ui.theme.Grey

@Composable
fun TopBarHome(
    navigationState: NavigationState,
) {
    val paddingStatusBar = WindowInsets.systemBars
        .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
        .asPaddingValues()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingStatusBar)
            .padding(top = 10.dp, start = SIDE_PADDING, end = SIDE_PADDING)
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = "Welcome back",
                color = Grey,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )
            Text(
                text = "Jackie",
                fontWeight = FontWeight.Bold
            )
        }

        Row {
            Image(
                modifier = Modifier
                    .clickable {},
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(10.dp))

            Image(
                modifier = Modifier
                    .clickable {},
                painter = painterResource(id = R.drawable.bell_icon),
                contentDescription = null
            )
        }


    }
}