package com.example.mad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mad.ui.NumbersScreens
import com.example.mad.ui.screens.numberDetail.NumberDetailScreen
import com.example.mad.ui.screens.numberType.NumberTypeScreen
import com.example.mad.ui.screens.numberType.TYPE_TRIVIA
import com.example.mad.ui.theme.MADTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MADTheme{
                NumbersApp()
            }
        }
    }
}

@Composable
fun NumbersApp() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {  Text(text = stringResource(id = R.string.app_name)) }
            )
        }
    ) { innerPadding ->
        NumbersNavHost(navController, modifier = Modifier.padding(innerPadding))
    }
}

/**
 * You can see this as a nav_graph.xml in compose
 */
@Composable
fun NumbersNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = NumbersScreens.NumbersOverview.name,
        modifier = modifier
    ) {
        //this one is from the previous step
        composable(NumbersScreens.NumbersOverview.name) {
            NumberTypeScreen(
                onClickDetail = {
                    navController.navigate("${NumbersScreens.NumberDetail.name}/${it}")
                },
            )
        }

        //add this one!
        val screenName = NumbersScreens.NumberDetail.name
        composable(
            route = "$screenName/{numberType}",
            arguments = listOf(
                navArgument("numberType") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val numberType = entry.arguments?.getString("numberType")
            NumberDetailScreen(numberType ?: TYPE_TRIVIA)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MADTheme {
    }
}