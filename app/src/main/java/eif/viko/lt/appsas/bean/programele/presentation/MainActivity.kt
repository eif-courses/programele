package eif.viko.lt.appsas.bean.programele.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import eif.viko.lt.appsas.bean.programele.domain.utils.PreferencesManager
import eif.viko.lt.appsas.bean.programele.domain.utils.ServiceLocator
import eif.viko.lt.appsas.bean.programele.ui.theme.ProgrameleTheme
import eif.viko.lt.appsas.bean.programele.domain.utils.Route

// https://www.youtube.com/watch?v=QpaTcx2nnHM
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ServiceLocator.preferencesManager = PreferencesManager(applicationContext)


        setContent {
            ProgrameleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                     BottomNavigationBar()
                     //SignInGoogleScreen()
                }
            }
        }
    }

    data class BottomNavigationItem(
        val label: String = "",
        val icon: ImageVector = Icons.Filled.Home,
        val route: String = ""
    ) {

        //function to get the list of bottomNavigationItems
        fun bottomNavigationItems(): List<BottomNavigationItem> {
            return listOf(
                BottomNavigationItem(
                    label = "Home",
                    icon = Icons.Filled.Home,
                    route = Route.AUTH_SCREEN
                ),
                BottomNavigationItem(
                    label = "Search",
                    icon = Icons.Filled.Search,
                    route = Route.SEARCH_SCREEN
                ),
                BottomNavigationItem(
                    label = "Profile",
                    icon = Icons.Filled.AccountCircle,
                    route = Route.PROFILE_SCREEN
                ),
            )
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BottomNavigationBar() {
        var navigationSelectedItem by remember { mutableStateOf(0) }
        val navController = rememberNavController() // router



        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                NavigationBar {
                    BottomNavigationItem().bottomNavigationItems()
                        .forEachIndexed { index, navigationItem ->
                            NavigationBarItem(
                                selected = index == navigationSelectedItem,
                                label = {
                                    Text(navigationItem.label)
                                },
                                icon = {
                                    Icon(
                                        navigationItem.icon,
                                        contentDescription = navigationItem.label
                                    )
                                },
                                onClick = {
                                    navigationSelectedItem = index
                                    navController.navigate(navigationItem.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                }
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Route.AUTH_SCREEN,
                modifier = Modifier.padding(paddingValues = paddingValues)
            ) {
                composable(Route.AUTH_SCREEN) {
                    //SignInGoogleScreen()
                    SignInGoogleScreen(navController)
                }
                composable(Route.SEARCH_SCREEN) {
                    SearchScreen(navController)
//                    SearchScreen(
//                        navController
//                    )
                }
                composable(Route.HOME_SCREEN) {
                    HomeScreen(navController)
                }
                composable(Route.PROFILE_SCREEN) {
                    ProfileScreen()

//                    ProfileScreen(
//                        navController
//                    )
                }
            }
        }
    }


}