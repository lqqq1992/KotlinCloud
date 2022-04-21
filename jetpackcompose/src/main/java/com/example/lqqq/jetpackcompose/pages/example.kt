package com.example.lqqq.jetpackcompose.pages

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lqqq.jetpackcompose.R
import com.example.lqqq.jetpackcompose.model.User
import java.util.ArrayList

@Composable
fun demoItem(name: String, desc: String) {
    val expand = remember {
        mutableStateOf(false)
    }
    val expandHeight by animateDpAsState(targetValue = if (expand.value) 210.dp else 70.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ))
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.LightGray)
        .padding(bottom = 2.dp)
        .height(expandHeight)) {
        Image(painter = painterResource(id = R.mipmap.avatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colors.secondary,
                    CircleShape))
        Spacer(modifier = Modifier.width(8.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.height(70.dp)) {
            Text(text = name, Modifier.padding(4.dp))
            Text(text = desc, Modifier.padding(4.dp))
        }
        OutlinedButton(onClick = { expand.value = !expand.value }) {
            Text(text = "点击改变")
        }
    }
}

@Composable
fun demo(list: List<User>) {
    LazyColumn {
        items(list) { user ->
            demoItem(user.name, user.desc)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun show() {
    val list = ArrayList<User>()
    for (index in 1 until 11) {
        val user = User(name = "姓名$index", gender = "男", age = index, desc = "描述$index")
        list.add(user)
    }
    demo(list)
}