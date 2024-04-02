package com.stu74533.lab2_74533

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SimpleText(text : String, modifier: Modifier = Modifier) {
    Text(
        text = text.toUpperCase(),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontFamily = robotoCondensed,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier
            .padding(horizontal = 10.dp)
    )
}

@Composable
fun DisplayFilmList(navController: NavController, filmList: List<Film>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        content = {
            items(filmList.size) { index ->
                FilmCard(film = filmList[index], modifier = Modifier.padding(20.dp), navController = navController, filmInd = index)
            }
        }
    )
}

@Composable
fun AppBar() {
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))
            .background(Color.White)
    )
    {
        Text(text = "BookingApp",
            fontFamily = robotoCondensed,
            fontSize = 42.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun FilmCard(film: Film, modifier: Modifier = Modifier, navController: NavController, filmInd: Int) {
    Box(modifier = modifier
        .width(130.dp)
        .height(220.dp)
        .clickable {
            val value = Screen.DetailsScreen.withArgs(filmInd.toString());
            navController.navigate(Screen.DetailsScreen.withArgs(filmInd.toString()))
        }
    ) {
        Column {
            UrlImage(
                url = film.image,
                modifier = Modifier
                    .width(130.dp)
                    .height(190.dp)
            )
            Box (contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(1.dp)
                    .background(Color.White)
            ) {
                SimpleText(text = film.title)
            }
        }
        if (film.seats_selected > 0) {
            Box(modifier = Modifier
                .padding(10.dp)
                .border(3.dp, Color.Red)
                .background(Color.White)

            ) {
                Text(
                    text = film.seats_selected.toString() + " seats selected",
                    fontFamily = robotoCondensed,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(5.dp)
                )
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController, filmList: List<Film>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFD9D9D9)
    ) {
        Column {
            AppBar()
            DisplayFilmList(navController, filmList)
        }
    }
}