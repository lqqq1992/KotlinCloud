package com.example.lqqq.jetpackcompose.pages

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.lqqq.jetpackcompose.R
import com.example.lqqq.jetpackcompose.model.User
import com.example.lqqq.jetpackcompose.ui.theme.Typography

/**
 * 消息列表
 */
@Composable
fun DemoItem(name: String, desc: String) {

    // mutableState对象
    val expand = remember {
        mutableStateOf(false)
    }
    // value对象
    val value by remember {
        mutableStateOf(false)
    }
    // value是值，setValue修改value
    val (value1, setValue) = remember {
        mutableStateOf(false)
    }
    val expandHeight by animateDpAsState(targetValue = if (expand.value) 210.dp else 70.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ))
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color.White)
        .padding(16.dp)
        .height(expandHeight)
        .clickable {
            expand.value = !expand.value
        }) {
        Image(painter = painterResource(id = R.mipmap.avatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .border(1.dp, Color.LightGray,
                    CircleShape))
        // 空占位符
        Spacer(modifier = Modifier.width(8.dp))
        Column(horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(70.dp)
                .padding(top = 8.dp, bottom = 8.dp)) {
            Text(text = name,
                style = Typography.body1,
                softWrap = false,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(4.dp))
            CompositionLocalProvider(LocalContentAlpha.provides(ContentAlpha.medium)) {
                Text(text = desc,
                    style = Typography.body2,
                    modifier = Modifier.padding(4.dp))
            }
        }
        Spacer(modifier = Modifier.weight(1f, true))
        Column(horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(70.dp)
                .padding(top = 8.dp, bottom = 8.dp)) {
            Text(text = "17:30",
                modifier = Modifier
                    .padding(4.dp))
            Icon(imageVector = Icons.Outlined.Favorite,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp))
        }
    }
}

@Composable
fun Demo(list: List<User>) {
    LazyColumn {
        items(list) { user ->
            DemoItem(user.name, user.desc)
            // 分割线
            Divider(color = Color.LightGray,
                thickness = 1.dp,
                // 距起始位置偏移量
                startIndent = 94.dp)
        }
    }
}