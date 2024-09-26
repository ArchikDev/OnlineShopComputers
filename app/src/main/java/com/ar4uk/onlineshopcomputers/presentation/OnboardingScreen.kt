package com.ar4uk.onlineshopcomputers.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ar4uk.onlineshopcomputers.R
import com.ar4uk.onlineshopcomputers.presentation.components.DefaultButton
import com.ar4uk.onlineshopcomputers.presentation.ui.theme.DarkGrey
import com.ar4uk.onlineshopcomputers.presentation.ui.theme.Green

@Composable
fun OnboardingScreen() {
    Column {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.intro_pic),
            contentScale = ContentScale.Fit,
            alignment = Alignment.BottomCenter,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(2.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = buildAnnotatedString {
                    append("Welcome to ")

                    withStyle(SpanStyle(color = Green)) {
                        append("Digital Store\n")
                    }

                    append(" a ")

                    withStyle(SpanStyle(color = Green)) {
                        append("Gateway")
                    }

                    append(" to the Latest Tech")
                },
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                lineHeight = 35.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Browse the latest styles from top brands\nGet personalized recommendations\nEnjoy fast, free shipping",
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                lineHeight = 28.sp,
                color = DarkGrey
            )
            Spacer(modifier = Modifier.height(15.dp))
            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            ) {
                Text(
                    text = "Let's Get Started"
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row {
                Text(
                    text = "Already have an account? "
                )

                Text(
                    text = "Sign In",
                    color = Green
                )
            }

        }
    }
}