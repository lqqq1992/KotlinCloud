package com.example.lqqq.kotlincloud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.lqqq.kotlincloud.notification.NotificationUtils
import com.example.lqqq.kotlincloud.ui.theme.KotlinCloudTheme

class MainActivity : ComponentActivity() {
    private val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinCloudTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting()
                }
            }
        }
//        NotificationUtils(this).createNotification()
    }
}

@Composable
fun Greeting() {
    // 通过createRefFor创建id，通过Modifier.layoutId()关联id
    BoxWithConstraints(Modifier.background(color = Color.White)) {
        val constraintSet = ConstraintSet {
            val guideline = createGuidelineFromStart(0.5f)
            val button = createRefFor("button")
            val text = createRefFor("text")
            constrain(button){
                top.linkTo(parent.top,margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(guideline)
            }
            constrain(text){
                top.linkTo(parent.top,margin = 16.dp)
                start.linkTo(guideline)
                end.linkTo(parent.end)
            }
        }
        ConstraintLayout(constraintSet = constraintSet,modifier = Modifier.fillMaxSize()) {
            Button(onClick = { /*TODO*/ },Modifier.layoutId("button")) {
                Text(text = "button")
            }
            Text(text = "text",Modifier.layoutId("text"))
        }
    }
}
@Composable
fun Greeting2() {
    // 在createRefs()创建，在constrainAs()关联
    ConstraintLayout(modifier = Modifier.background(color = Color.White)) {
        val guideline = createGuidelineFromStart(0.5f)
        val (button, text) = createRefs()
        Button(onClick = { /*TODO*/ },modifier = Modifier.constrainAs(button) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(guideline)
        }) {
            Text(text = "button")
        }
        Text(text = "text",modifier = Modifier.constrainAs(text) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(guideline)
            end.linkTo(parent.end)
        })
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    KotlinCloudTheme {
        Greeting()

    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    KotlinCloudTheme {
        Greeting2()
    }
}