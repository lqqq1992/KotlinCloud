package com.example.lqqq.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lqqq.jetpackcompose.model.PageInfo
import com.example.lqqq.jetpackcompose.ui.theme.KotlinCloudTheme

class ComposeMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinCloudTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainContent()
//                    Greeting()
                }
            }
        }
//        startActivity(Intent(this,TestActivity::class.java))
    }
}

@Composable
fun MainContent() {
    val pageInfoList = listOf(
        PageInfo("home", "首页", Icons.Default.Home),
        PageInfo("favorite", "最爱", Icons.Default.Favorite),
        PageInfo("mine", "我的", Icons.Default.Person)
    )
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomBar(pageInfoList) {
                navController.popBackStack()
                navController.navigate("${pageInfoList[it].route}/${pageInfoList[it].name}") {
                    // 通过使用 saveState 和 restoreState 标志，当您在底部导航项之间切换时，
                    // 系统会正确保存并恢复该项的状态和返回堆栈。
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = "${pageInfoList[0].route}/{name}"
        ) {
            composable(
                "${pageInfoList[0].route}/{name}",
                arguments = listOf(navArgument("name") {
                    type = NavType.StringType; defaultValue = "首页"
                })
            ) { navBackStackEntry ->
                Text(text = "${navBackStackEntry.arguments?.getString("name")}")
            }
            composable(
                "${pageInfoList[1].route}/{name}",
                arguments = listOf(navArgument("name") { type = NavType.StringType })
            ) { navBackStackEntry ->
                Text(text = "${navBackStackEntry.arguments?.getString("name")}")
            }
            composable(
                "${pageInfoList[2].route}/{name}",
                arguments = listOf(navArgument("name") { type = NavType.StringType })
            ) { navBackStackEntry ->
                Text(text = "${navBackStackEntry.arguments?.getString("name")}")
            }
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "标题") },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun BottomBar(pageInfoList: List<PageInfo>, onItemClick: (index: Int) -> Unit) {
    BottomNavigation() {
        var selectedItem by remember { mutableStateOf(0) }
        pageInfoList.forEachIndexed { index, item ->
            BottomNavigationItem(selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onItemClick(index)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = item.name)
                })
        }
    }
}

@Composable
fun Greeting() {
    Column(// 垂直排列
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(// 行排列
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .background(color = Color.LightGray)
                .fillMaxWidth()
        ) {
            Text(text = "123")
            Text(text = "456")
        }
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = "123")
            Text(text = "456")
            TextField(value = "默认文字", onValueChange = {})
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable

fun DefaultPreview() {
    KotlinCloudTheme {
        Surface(color = MaterialTheme.colors.background) {
            MainContent()
        }
    }
}