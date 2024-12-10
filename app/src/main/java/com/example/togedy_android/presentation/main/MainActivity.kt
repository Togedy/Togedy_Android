package com.example.togedy_android.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MainScreen()
//            Togedy_AndroidTheme {
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
////                    BottomNavigationBar()
//                    val navController = rememberNavController()
//                    val navBackStackEntry by navController.currentBackStackEntryAsState()
//                    val currentRoute = navBackStackEntry?.destination?.route
//                    Scaffold(
//                        modifier = Modifier.fillMaxSize(),
//                        bottomBar = {
//                            if (currentRoute !in listOf(
//                                    "launchPage",
//                                    "signIn",
//                                    "joinMembership",
//                                    "ResignMemberShip"
//                                )
//                            ) {
//                                NavigationBar(
//                                    containerColor = colorResource(id = R.color.white),
//                                    tonalElevation = 10.dp
//                                ) {
//                                    BottomNavigationItem().bottomNavigationItems()
//                                        .forEachIndexed { _, item ->
//                                            NavigationBarItem(
//                                                selected = item.route == currentRoute,
//                                                label = {
//                                                    Text(
//                                                        text = item.label,
//                                                        fontWeight = FontWeight.Medium,
//                                                        fontSize = 12.sp
//                                                    )
//                                                },
//                                                onClick = {
//                                                    navController.navigate(item.route) {
//                                                        popUpTo(navController.graph.findStartDestination().id) {
//                                                            saveState = true
//                                                        }
//                                                        launchSingleTop = true
//                                                        restoreState = true
//                                                    }
//                                                },
//                                                icon = {
//                                                    Icon(
//                                                        painterResource(id = item.icon),
//                                                        contentDescription = item.label
//                                                    )
//                                                },
//                                                colors = NavigationBarItemDefaults.colors(
//                                                    selectedIconColor = colorResource(id = R.color.yellow_main),
//                                                    unselectedIconColor = colorResource(id = R.color.gray400),
//                                                    selectedTextColor = colorResource(id = R.color.yellow_main),
//                                                    unselectedTextColor = colorResource(id = R.color.gray400),
//                                                    indicatorColor = Color.Transparent
//                                                )
//                                            )
//                                        }
//                                }
//                            }
//                        }
//                    ) {
//                        Box(
//                            modifier = Modifier.padding(it)
//                        ) {
//                            NavGraph(navController)
//                        }
//                    }
//                }
//            }
        }
    }
}