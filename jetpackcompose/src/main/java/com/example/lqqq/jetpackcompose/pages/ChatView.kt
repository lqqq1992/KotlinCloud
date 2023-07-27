package com.example.lqqq.jetpackcompose.pages

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.rounded.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lqqq.jetpackcompose.model.Message

/**
 * chat对话页面
 */

@Composable
fun ChatView() {
    val msgList = ArrayList<Message>()
    for (index in 1..10) {
        val msg = Message(id = index.toLong(),
            type = if (index % 2 == 0) "receive" else "send",
            contentType = "voice",
            content = "内容$index")
        msgList.add(msg)
    }
    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
        items(msgList) { msg ->
            if (msg.type == "receive") {
                ItemReceive(msg)
            } else {
                ItemSend(msg)
            }
        }
    }
}

@Composable
fun ItemReceive(msg: Message) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)) {
        Image(imageVector = Icons.Rounded.Face,
            contentDescription = null,
            modifier = Modifier
                .width(48.dp)
                .height(48.dp))
        Spacer(modifier = Modifier
            .width(8.dp)
            .fillMaxHeight())
        when (msg.contentType) {
            "text" -> {
                Text(text = msg.content,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .border(width = 1.dp, Color.LightGray, shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp))
            }
            "image" -> {
                Image(imageVector = Icons.Default.DateRange, contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .width(90.dp)
                        .height(160.dp)
                        .border(width = 1.dp, Color.LightGray, shape = RoundedCornerShape(5.dp)))
            }
            "voice" -> {
                Text(text = msg.content,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .border(width = 1.dp, Color.LightGray, shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp))
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemSend(msg: Message) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.End) {
        when (msg.contentType) {
            "text" -> {
                Text(text = msg.content,
                    modifier = Modifier
                        .combinedClickable(onLongClick = {}, onDoubleClick = {}) {}
                        .padding(top = 16.dp)
                        .border(width = 1.dp, Color.LightGray, shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp))
            }
            "image" -> {
                Image(imageVector = Icons.Default.DateRange, contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .width(90.dp)
                        .height(160.dp)
                        .border(width = 1.dp, Color.LightGray, shape = RoundedCornerShape(5.dp)))
            }
            "voice" -> {
                Row(horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .width(60.dp)
                        .height(40.dp)
                        .background(Color.Gray)
                        .padding(end = 8.dp)) {
                    Icon(imageVector = Icons.Default.Phone,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp))
                }
            }
            "video" -> {

            }
        }
        Spacer(modifier = Modifier
            .width(8.dp)
            .fillMaxHeight())
        Image(imageVector = Icons.Rounded.Face,
            contentDescription = null,
            modifier = Modifier
                .width(48.dp)
                .height(48.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Show1() {
    ChatView()
}