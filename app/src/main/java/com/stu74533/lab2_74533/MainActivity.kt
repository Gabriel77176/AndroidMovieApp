package com.stu74533.lab2_74533

import Navigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.android.volley.toolbox.ImageLoader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import coil.compose.rememberImagePainter
import coil.imageLoader
import coil.request.ImageRequest

val robotoCondensed = FontFamily(
    Font(R.font.robotocondensed_black, FontWeight.Black),
    Font(R.font.robotocondensed_blackitalic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.robotocondensed_bold, FontWeight.Bold),
    Font(R.font.robotocondensed_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.robotocondensed_extrabold, FontWeight.ExtraBold),
    Font(R.font.robotocondensed_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.robotocondensed_extralight, FontWeight.ExtraLight),
    Font(R.font.robotocondensed_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.robotocondensed_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.robotocondensed_light, FontWeight.Light),
    Font(R.font.robotocondensed_lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.robotocondensed_medium, FontWeight.Medium),
    Font(R.font.robotocondensed_mediumitalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.robotocondensed_regular, FontWeight.Normal),
    Font(R.font.robotocondensed_semibold, FontWeight.SemiBold),
    Font(R.font.robotocondensed_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.robotocondensed_thin, FontWeight.Thin),
    Font(R.font.robotocondensed_thinitalic, FontWeight.Thin, FontStyle.Italic),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.setDefaultUncaughtExceptionHandler { _, e ->
            Log.e("UncaughtException", "Uncaught Exception detected", e)
        }

        setContent {
            Navigation(this);
        }
    }
}

@Composable
fun UrlImage(url: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = url,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}


class Film(
    val title: String,
    val image: String,
    val certification: String,
    val description: String,
    val starring: List<String>,
    var seats_selected: Int,
    var seats_remaining: Int,
) {}

fun RandomSeats(): Int {
    return (0..15).random()
}

@Composable
fun saveFilmData(context: Context) {
    val gson = Gson()
    val films = listOf(
        Film(
            title = "Ghostbusters: Frozen Empire",
            description = "In Ghostbusters: Frozen Empire, the Spengler family returns to where it all started – the iconic New York City firehouse – to team up with the original Ghostbusters, who’ve developed a top-secret research lab to take busting ghosts to the next level. But when the discovery of an ancient artifact unleashes an evil force, Ghostbusters new and old must join forces to protect their home and save the world from a second Ice Age.",
            seats_selected = 0,
            seats_remaining = RandomSeats(),
            image = "https://www.myvue.com/-/jssmedia/vuecinemas/film-and-events/feb-2024/ghostbusters-poster.jpg",
            certification = "PG-13",
            starring = listOf("Bill Murray", "Dan Aykroyd", "Ernie Hudson")
        ),
        Film(
            title = "Dune: Part Two",
            description = "Paul Atreides unites with Chani and the Fremen while on a warpath of revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the known universe, he endeavors to prevent a terrible future only he can foresee.",
            seats_selected = 0,
            seats_remaining = RandomSeats(),
            image = "https://www.myvue.com/-/jssmedia/vuecinemas/img/import/7a7a20aa-1064-46fd-96cc-4b271268f2c5_dune-part-ii_posters_one-sheet_712px.jpg",
            certification = "PG-13",
            starring = listOf("Timothee Chalamet", "Zendaya", "Rebecca Ferguson")
        ),
        Film(
            title = "Immaculate",
            description = "Cecilia, a woman of devout faith, is warmly welcomed to the picture-perfect Italian countryside where she is offered a new role at an illustrious convent. But it becomes clear to Cecilia that her new home harbors dark and horrifying secrets.",
            seats_selected = 0,
            seats_remaining = RandomSeats(),
            image = "https://www.myvue.com/-/jssmedia/vuecinemas/img/import/b3d828fc-f999-4d6b-8bf6-299a4998c46e_immaculate_posters_0825-bb-immaculate_1-sheet_aw-08022024_712px.jpg",
            certification = "R",
            starring = listOf("Alicia Vikander", "Charlotte Rampling", "Walton Goggins")
        ),
        Film(
            title = "Wicked Little Letters",
            description = "A 1920s English seaside town bears witness to a dark and absurd scandal in this riotous mystery comedy. Based on a stranger than fiction true story, WICKED LITTLE LETTERS follows two neighbours: deeply conservative local Edith Swan (Olivia Colman) and rowdy Irish migrant Rose Gooding (Jessie Buckley). When Edith and fellow residents begin to receive wicked letters full of unintentionally hilarious profanities, foulmouthed Rose is charged with the crime. The anonymous letters prompt a national uproar, and a trial ensues. However, as the town's women - led by Police Officer Gladys Moss (Anjana Vasan) - begin to investigate the crime themselves, they suspect that something is amiss, and Rose may not be the culprit after all.",
            seats_selected = 0,
            seats_remaining = RandomSeats(),
            image = "https://www.myvue.com/-/jssmedia/vuecinemas/img/import/2f93acca-53ff-41f7-a57c-42d191057f02_wicked-little-letters_posters_wicked-little-letters-poster-uk--.jpg",
            certification = "PG",
            starring = listOf("Olivia Colman", "Jessie Buckley", "Anjana Vasan")
        ),
        Film(
            title = "Imaginary",
            description = "From Blumhouse, the genre-defining masterminds behind FIVE NIGHTS AT FREDDY’S and M3GAN. When Jessica (DeWanda Wise) moves back into her childhood home with her family, her youngest stepdaughter Alice (Pyper Braun) develops an eerie attachment to a stuffed bear named Chauncey she finds in the basement. Alice starts playing games with Chauncey that begin playful and become increasingly sinister. As Alice’s behaviour becomes more and more concerning, Jessica intervenes only to realize Chauncey is much more than the stuffed toy bear she believed him to be.",
            seats_selected = 0,
            seats_remaining = RandomSeats(),
            image = "https://www.myvue.com/-/jssmedia/vuecinemas/img/import/f607e5c8-c8db-4dbb-92d0-dad9ab8c68c3_imaginary_posters_imaginary_1sheet_686x1016_712px.jpg",
            certification = "R",
            starring = listOf("DeWanda Wise", "Pyper Braun")
        ),
        Film(
            title = "Bob Marley: One Love",
            description = "BOB MARLEY: ONE LOVE celebrates the life and music of an icon who inspired generations through his message of love and unity. On the big screen for the first time, discover Bob’s powerful story of overcoming adversity and the journey behind his revolutionary music. Produced in partnership with the Marley family and starring Kingsley Ben-Adir as the legendary musician and Lashana Lynch as his wife Rita.",
            seats_selected = 0,
            seats_remaining = RandomSeats(),
            image = "https://www.myvue.com/-/jssmedia/vuecinemas/img/import/bob-marley-one-love_posters_bmol_intl_dgtl_teaser_1sheet_2025x3000_spotlight_uk_712px.jpg",
            certification = "PG-13",
            starring = listOf("Kingsley Ben-Adir", "Lashana Lynch")
        ),
        Film(
            title = "Anyone But You",
            description = "In the comedy Anyone But You, Bea (Sydney Sweeney) and Ben (Glen Powell) look like the perfect couple, but after an amazing first date something happens that turns their fiery hot attraction ice cold - until they find themselves unexpectedly thrust together at a destination wedding in Australia. So they do what any two mature adults would do: pretend to be a couple.",
            seats_selected = 0,
            seats_remaining = RandomSeats(),
            image = "https://www.myvue.com/-/jssmedia/vuecinemas/film-and-events/nov-2023/f98db126-5279-4b0d-b448-8d89e12a6601.jpg",
            certification = "R",
            starring = listOf("Sydney Sweeney", "Glen Powell")
        ),
        Film(
            title = "Shaitaan (Hindi)",
            description = "Kabir and his family's fun weekend retreat takes a nightmarish turn when they let a friendly but mysterious stranger into their house. As the clock ticks, the family will be forced to confront their worst fears in this gripping, edge-of-the-seat supernatural-thriller that deals with the sinister elements of Indian Black Magic.",
            seats_selected = 0,
            seats_remaining = RandomSeats(),
            image = "https://www.myvue.com/-/jssmedia/vuecinemas/img/import/e6c8f4b2-96fb-4dc4-80d6-a22a3a5decb9_shaitaan_posters_2_546px.jpg",
            certification = "PG",
            starring = listOf("Rajkummar Rao", "Kalki Koechlin")
        ),
        Film(
            title = "Late Night With The Devil",
            description = "October 31, 1977. Johnny Carson rival Jack Delroy hosts a syndicated late night talk show ‘Night Owls’ that has long been a trusted companion to insomniacs around the country. A year after the tragic death of Jack’s wife, ratings have plummeted. Desperate to turn his fortunes around, Jack plans a Halloween special like no other, unaware that he is about to unleash evil into the living rooms of America.",
            seats_selected = 0,
            seats_remaining = RandomSeats(),
            image = "https://www.myvue.com/-/jssmedia/vuecinemas/img/import/fbba555e-0b86-42ef-bf03-b6622edd07cb_late-night-with-the-devil_posters_lnwtd_6sht_p4p_712px.jpg",
            certification = "R",
            starring = listOf("Tobey Maguire", "Charlize Theron", "Hugh Jackman")
        )
    )
    val json = gson.toJson(films)
    val file = File(context.filesDir, "films.json")
    file.writeText(json)
}

@Composable
fun UpdateData(context: Context, films: List<Film>) {
    val gson = Gson()
    val json = gson.toJson(films)
    val file = File(context.filesDir, "films.json")
    file.writeText(json)
}

@Composable
fun GetFilmList(context: Context) : List<Film> {
    val file = File(context.filesDir, "films.json")

    // If the file doesn't exist, save the film data
    if (!file.exists()) {
        saveFilmData(context)
    }

    // Read the file and convert the JSON string back to a List<Film>
    val gson = Gson()
    val json = file.readText()
    val type = object : TypeToken<List<Film>>() {}.type
    var films: List<Film> = gson.fromJson(json, type)

    return films
}