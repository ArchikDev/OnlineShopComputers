package com.ar4uk.onlineshopcomputers.presentation.components

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.ar4uk.onlineshopcomputers.presentation.ui.theme.Black100Transparent
import com.ar4uk.onlineshopcomputers.presentation.ui.theme.Green
import com.ar4uk.onlineshopcomputers.presentation.ui.theme.Grey
import kotlin.math.log


@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Green,
        contentColor = Color.White,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = Color.Black
    ),
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = Modifier.height(48.dp).then(modifier),
        onClick = onClick,
        colors = colors,
        enabled = enabled,
        shape = RoundedCornerShape(7.dp),
        content = content
    )
}

@Composable
fun CheckBoxItem(
    name: String,
    isCheck: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: (isCheck: Boolean) -> Unit,
) {
    val checkedState = remember { mutableStateOf(isCheck) }

    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            CustomCheckBox(
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = it
                    onClick(it)
                }
            )
        }
    }

}

@Composable
fun ImageLoading(
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val progressAnimate = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,//animation duration
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .zIndex(2f)
            .background(Black100Transparent)
    ) {
        Canvas(
            modifier = Modifier
                .align(Alignment.Center)
                .size(70.dp),
            onDraw = {
                drawCircle(
                    color = Color(0xFF88888888),
                    style = Stroke(width = 10f)
                )
            }
        )
        Canvas(
            modifier = Modifier
                .align(Alignment.Center)
                .size(70.dp),
            onDraw = {
                drawArc(
                    color = Color.White,
                    style = Stroke(
                        width = 14f,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round,
                    ),
                    startAngle = progressAnimate.value,
                    sweepAngle = 360 / 4f,
                    useCenter = false
                )
            }
        )
    }
}



@Composable
fun CustomCheckBox(
    checked: Boolean,
    onCheckedChange: (isCheck: Boolean) -> Unit,
    isDisabled: Boolean = false,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(3.dp))
            .size(16.dp)
            .border(
                width = 1.dp,
                color = if (checked) Green else Grey,
                shape = RoundedCornerShape(3.dp)
            )
            .background(if (checked) Green else Color.Transparent)
            .clickable {
                onCheckedChange(!checked)
            },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                Icons.Default.Check,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}