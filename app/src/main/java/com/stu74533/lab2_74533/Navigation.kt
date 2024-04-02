import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.stu74533.lab2_74533.Film
import com.stu74533.lab2_74533.GetFilmList
import com.stu74533.lab2_74533.MainScreen
import com.stu74533.lab2_74533.DetailsScreen
import com.stu74533.lab2_74533.Screen
import com.stu74533.lab2_74533.UpdateData

@Composable
fun Navigation(context: Context) {
    val navController = rememberNavController();
    val filmList = GetFilmList(context)
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            UpdateData(context = context, films = filmList)
            MainScreen(navController = navController, filmList = filmList)
        }
        composable(
            route = Screen.DetailsScreen.route + "/{filmInd}",
            arguments = listOf(
                navArgument("filmInd") {
                    type = NavType.StringType
                    defaultValue = "0"
                    nullable = true
                }
            )
        ) {
            val filmInd = it.arguments?.getString("filmInd") ?: "3"
            DetailsScreen(filmInd = filmInd, navController = navController, filmList = filmList)
        }
    }
}