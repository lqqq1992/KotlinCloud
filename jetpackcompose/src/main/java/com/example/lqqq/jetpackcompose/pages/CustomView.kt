package com.example.lqqq.jetpackcompose.pages

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.lqqq.jetpackcompose.ui.theme.KotlinCloudTheme
import kotlin.math.cos
import kotlin.math.sin

// 自定义布局
@Composable
fun CustomWidget(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            // 测量子元素
            measurable.measure(constraints)
        }

        // 设置布局宽高
        layout(constraints.maxWidth, constraints.maxHeight) {
            // 定义子元素y坐标
            var yPosition = 0

            // Place children in the parent layout
            placeables.forEach { placeable ->
                // 设置子元素的位置
                placeable.placeRelative(x = 0, y = yPosition)

                // Record the y co-ord placed up to
                yPosition += placeable.height
            }
        }
    }
}

// 自定义绘制控件
@Composable
fun CustomView(modifier: Modifier) {
    Canvas(modifier = modifier) {
        withTransform({
            scale(scaleX = 1f, scaleY = 1f)
            translate(left = 0f, top = 1f)
            rotate(0f)
            // 设置内边距
            inset(horizontal = 4f, vertical = 4f)
        }) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawLine(
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = canvasWidth, y = canvasHeight),
                color = Color.Green,
                strokeWidth = 10f,
            )
            drawOval(color = Color.Cyan)
            drawCircle(color = Color.Blue)
            val path = Path()
            path.moveTo(0f, 0f)
            path.lineTo(canvasWidth, 0f)
            path.lineTo(canvasWidth / 2f, canvasHeight / 2f)
            path.lineTo(0f, 0f)
            drawPath(path = path, color = Color.Magenta)
        }
    }
}

/**
 * 六边形属性控件
 */
@OptIn(ExperimentalTextApi::class)
@Composable
fun AttributeView(width: Dp) {
    val textMeasure = rememberTextMeasurer()
    Canvas(
        modifier = Modifier
            .size(width)
            .padding(8.dp)
    ) {
//        val pointLists = calculatePoints(size.center, 4)
        Log.e("---center", size.toString())
        val text = "绘制文本"
        val measuredText = textMeasure.measure(AnnotatedString(text))
        val pointLists = calculatePoints2(size.center, size.center.x-measuredText.size.width, 6, 2)
        // 绘制中心到最外层连线和文本
        pointLists[0].forEach {
            drawLine(
                start = size.center,
                end = it,
                color = Color.Black,
                strokeWidth = 1f,
            )
            drawText(
                measuredText,
                topLeft = Offset(
                    if (it.x < size.center.x) it.x - measuredText.size.width else it.x,
                    it.y - measuredText.size.height / 2
                )
            )
        }
        pointLists.forEachIndexed { index, pointList ->
            // 绘制辅助点
            drawPoints(
                pointList,
                pointMode = PointMode.Points,
                color = Color.Black,
                strokeWidth = 15f,
                pathEffect = PathEffect.cornerPathEffect(15f)
            )
            // 从外到内循环绘制每一圈
            pointList.forEachIndexed { index, it ->
                if (index < pointList.size - 1) {
                    drawLine(
                        start = it,
                        end = pointList[index + 1],
                        color = Color.Black,
                        strokeWidth = 1f,
                    )
                } else {
                    drawLine(
                        start = it,
                        end = pointList[0],
                        color = Color.Black,
                        strokeWidth = 1f,
                    )
                }
            }
        }
    }
}

/**
 * 计算每一圈的坐标点
 */
fun calculatePoints(center: Offset, circleNum: Int): MutableList<MutableList<Offset>> {
    // 如果设置圈数小于1则默认设置为1
    val circleNumber = if (circleNum <= 0) 1 else circleNum
    val pointsLists = mutableListOf<MutableList<Offset>>()
    for (i in 0 until circleNumber) {
        val pointsList = mutableListOf<Offset>()
        pointsList.add(
            Offset(
                center.x / 2f * (1 + i / circleNumber.toFloat()),
                center.y / circleNumber.toFloat() * i
            )
        )
        pointsList.add(
            Offset(
                center.x + center.x / 2f * (1 - i / circleNumber.toFloat()),
                center.y / circleNumber.toFloat() * i
            )
        )
        pointsList.add(Offset(center.x * 2 - center.x / circleNumber.toFloat() * i, center.y))
        pointsList.add(
            Offset(
                center.x + center.x / 2f * (1 - i / circleNumber.toFloat()),
                center.y * 2 - center.y / circleNumber.toFloat() * i
            )
        )
        pointsList.add(
            Offset(
                center.x / 2f * (1 + i / circleNumber.toFloat()),
                center.y * 2 - center.y / circleNumber.toFloat() * i
            )
        )
        pointsList.add(Offset(center.x / circleNumber.toFloat() * i, center.y))
        pointsLists.add(pointsList)
    }
    return pointsLists
}

/**
 * 计算每一圈的坐标点
 */
fun calculatePoints2(
    center: Offset, // 中心点坐标
    radius: Float,    // 辅助圆半径
    pointNum: Int,  // 多边形边数
    circleNum: Int, // 圈数
): MutableList<MutableList<Offset>> {
    // 如果设置多边形边数小于3则默认设置为3
    val pointNumber = if (pointNum < 3) 3 else pointNum
    // 如果设置圈数小于1则默认设置为1
    val circleNumber = if (circleNum <= 0) 1 else circleNum
    // 每一份角度
    val angle = 360f / pointNumber
    // 转化为π
    val radian = Math.toRadians(angle.toDouble())
    // 初始点角度
    val firstAngle = if (pointNumber % 2 == 1) {
        0.0
    } else {
        -radian / 2
    }
    Log.e("---center", center.toString())
    Log.e("---pointNumber", pointNumber.toString())
    Log.e("---circleNumber", circleNumber.toString())
    Log.e("---angle", angle.toString())
    Log.e("---radian", radian.toString())
    Log.e("---firstAngle", firstAngle.toString())
    Log.e("---radius", radius.toString())
    val pointsLists = mutableListOf<MutableList<Offset>>()
    for (i in 0 until circleNumber) {
        val pointsList = mutableListOf<Offset>()
        for (j in 0 until pointNumber) {
            pointsList.add(
                Offset(
                    (radius * ((circleNumber - i) / circleNumber.toFloat()) * sin(firstAngle + j * radian) + center.x).toFloat(),
                    (radius * ((circleNumber - i) / circleNumber.toFloat()) * cos(
                        firstAngle + j * radian - Math.toRadians(
                            180.0
                        )
                    ) + center.y).toFloat()
                )
            )
        }
        pointsLists.add(pointsList)
    }
    Log.e("---pointsLists", pointsLists.toString())
    return pointsLists
}

@Preview(showBackground = true, showSystemUi = true)
@Composable

fun DefaultPreview() {
    KotlinCloudTheme {
        Surface(color = MaterialTheme.colors.background) {
//            CustomWidget(modifier = Modifier.padding(4.dp)) {
//                Text(text = "测试111111")
//                Text(text = "测试222222")
//                Text(text = "测试333333")
//            }
//            CustomView(modifier = Modifier.fillMaxSize())
            AttributeView(300.dp)
        }
    }
}