package kirillkitten.shikimori.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import kirillkitten.shikimori.ui.theme.ShikimoriTheme

/**
 * [Activity][android.app.Activity] that displays an anime grid.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * @see androidx.activity.ComponentActivity.onCreate
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShikimoriTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "grid") {
                    composable("grid") {
                        val viewModel = hiltViewModel<AnimeGridViewModel>()
                        AnimeGridScreen(
                            viewModel
                        ) { id ->
                            navController.navigate("info/$id")
                        }
                    }
                    composable(
                        "info/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.IntType })
                    ) { backStackEntry ->
                        backStackEntry.arguments?.getInt("id")?.let { id ->
                            val viewModel = hiltViewModel<AnimeInfoViewModel>()
                            viewModel.id = id
                            AnimeInfoScreen(viewModel)
                        }
                    }
                }
            }
        }
    }
}
