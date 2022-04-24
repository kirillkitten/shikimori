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
import kirillkitten.shikimori.data.Anime
import kirillkitten.shikimori.ui.theme.ShikimoriTheme
import java.time.LocalDate

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

val AnimePreview = Anime(
    id = 1,
    name = "Тетрадь Смерти",
    imgPreview = "",
    imgOriginal = "",
    airDate = LocalDate.parse("2017-01-01"),
    releaseDate = LocalDate.parse("2022-04-11"),
    format = Anime.Format.TV,
    score = 8.0f,
    status = Anime.Status.RELEASED,
    episodes = 24,
    duration = 23,
    description = "Многострадальный остров Парадиз вновь погружается в войну... После " +
        "разрушительных действий [character=40882]Эрена Йегера[/character] марлийцы начинают " +
        "вторжение на остров, и на их стороне без малого весь мир. Эрену вновь предстоит " +
        "столкнуться в безжалостной схватке с марлийскими воинами, в том числе с " +
        "[character=46484]Райнером Брауном[/character]. И это в разгар нового государственного " +
        "переворота на Парадизе, учинённого радикальными сторонниками действий Эрена. И вновь в " +
        "некогда родной для Эрена Сигансине разворачивается поистине судьбоносная битва, исход " +
        "которой может изменить весь мир.\\r\\n\\r\\nСможет ли Эрен, отвернувшийся от друзей и " +
        "товарищей, добиться цели и достичь подлинной свободы? Готов ли он уничтожить всех своих " +
        "врагов? Какой выбор придётся в этой схватке сделать [character=40881]Микасе[/character]," +
        " [character=46494]Армину[/character] и всем товарищам и друзьям Эрена? И каковы будут " +
        "последствия для них самих и для всего мира? Эпическая драма достигнет своей кульминации.",
)
