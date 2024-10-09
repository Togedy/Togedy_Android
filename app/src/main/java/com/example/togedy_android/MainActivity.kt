package com.example.togedy_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.togedy_android.ui.bottom_nav.BottomNavigationItem
import com.example.togedy_android.ui.model.NavGraph
import com.example.togedy_android.ui.theme.Pretendard
import com.example.togedy_android.ui.theme.Togedy_AndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Togedy_AndroidTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    BottomNavigationBar()
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        bottomBar = {
                            if (currentRoute !in listOf(
                                    "launchPage",
                                    "signIn",
                                    "joinMembership",
                                    "ResignMemberShip"
                                )
                            ) {
                                NavigationBar(
                                    containerColor = colorResource(id = R.color.white),
                                    tonalElevation = 10.dp
                                ) {
                                    BottomNavigationItem().bottomNavigationItems()
                                        .forEachIndexed { _, item ->
                                            NavigationBarItem(
                                                selected = item.route == currentRoute,
                                                label = {
                                                    Text(
                                                        text = item.label,
                                                        fontFamily = Pretendard,
                                                        fontWeight = FontWeight.Medium,
                                                        fontSize = 12.sp
                                                    )
                                                },
                                                onClick = {
                                                    navController.navigate(item.route) {
                                                        popUpTo(navController.graph.findStartDestination().id) {
                                                            saveState = true
                                                        }
                                                        launchSingleTop = true
                                                        restoreState = true
                                                    }
                                                },
                                                icon = {
                                                    Icon(
                                                        painterResource(id = item.icon),
                                                        contentDescription = item.label
                                                    )
                                                },
                                                colors = NavigationBarItemDefaults.colors(
                                                    selectedIconColor = colorResource(id = R.color.yellow_main),
                                                    unselectedIconColor = colorResource(id = R.color.gray400),
                                                    selectedTextColor = colorResource(id = R.color.yellow_main),
                                                    unselectedTextColor = colorResource(id = R.color.gray400),
                                                    indicatorColor = Color.Transparent
                                                )
                                            )
                                        }
                                }
                            }
                        }
                    ) {
                        Box(
                            modifier = Modifier.padding(it)
                        ) {
                            NavGraph(navController)
                        }
                    }
                }
            }
        }
    }
}

//@Composable
//fun Greeting() {
//    BottomNavigationBar()
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    Togedy_AndroidTheme {
//        Greeting()
//    }
//}