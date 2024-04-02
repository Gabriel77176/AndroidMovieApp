package com.stu74533.lab2_74533

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DetailsScreen(filmInd: String, navController: NavController, filmList: List<Film>) {

    val filmIndInt = filmInd.toInt();
    val film = filmList[filmIndInt];

    val seatTaken = remember { mutableStateOf(film.seats_selected) }
    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .verticalScroll(scrollState)
    ) {
        UrlImage(
            url = film.image,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .clip(shape = RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)

        ){
            Text(
                text = film.title,
                fontFamily = robotoCondensed,
                fontSize = 42.sp,
                fontWeight = FontWeight.ExtraBold,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = film.description,
                fontFamily = robotoCondensed,
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontFamily = robotoCondensed, fontWeight = FontWeight.Bold, fontSize = 25.sp)) {
                        append("Certification: ")
                    }
                    withStyle(style = SpanStyle(fontFamily = robotoCondensed, fontSize = 25.sp)) {
                        append(film.certification)
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontFamily = robotoCondensed, fontWeight = FontWeight.Bold, fontSize = 25.sp)) {
                        append("Actors: ")
                    }
                    withStyle(style = SpanStyle(fontFamily = robotoCondensed, fontSize = 25.sp)) {
                        for (i in 0..<(film.starring.size-1)) {
                            append("${film.starring[i]}, ")
                        }
                        if (film.starring.isNotEmpty()) {
                            append(film.starring[film.starring.size - 1])
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontFamily = robotoCondensed, fontWeight = FontWeight.Bold, fontSize = 25.sp)) {
                        append("Seats Available: ")
                    }
                    withStyle(style = SpanStyle(fontFamily = robotoCondensed, fontSize = 25.sp)) {
                        append("${film.seats_remaining}")
                    }
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "My reservation: ",
                fontFamily = robotoCondensed,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                Button(onClick = {
                    if (film.seats_selected > 0)
                    {
                        film.seats_selected -= 1
                        film.seats_remaining += 1
                        seatTaken.value -= 1
                    }
                },
                    contentPadding = PaddingValues(10.dp),
                    colors = if (seatTaken.value == 0)
                    {
                        ButtonDefaults.buttonColors(Color(0xFF888888))
                    }
                    else
                    {
                        ButtonDefaults.buttonColors(Color(0xFFD9D9D9))
                    },
                    //enabled = seatTaken.value > 0,
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(all = 0.dp)
                        .width(60.dp)
                        .height(60.dp)

                ) {
                    Image(painter = painterResource(id = R.drawable.minus_ico), contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .width(50.dp)
                            .height(50.dp)
                    )
                }
                Spacer(modifier = Modifier.width(30.dp))
                Text(text = seatTaken.value.toString(),
                    fontFamily = robotoCondensed,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp)
                Spacer(modifier = Modifier.width(30.dp))
                Button(onClick = {
                    if (film.seats_remaining != 0)
                    {
                        film.seats_selected += 1
                        film.seats_remaining -= 1
                        seatTaken.value += 1
                    }
                },
                    contentPadding = PaddingValues(10.dp),
                    colors = if (seatTaken.value == film.seats_remaining)
                    {
                        ButtonDefaults.buttonColors(Color(0xFF888888))
                    }
                    else
                    {
                        ButtonDefaults.buttonColors(Color(0xFFD9D9D9))
                    },
                    //enabled = seatTaken.value < film.seatAvailable,
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(all = 0.dp)
                        .width(60.dp)
                        .height(60.dp)

                ) {
                    Image(painter = painterResource(id = R.drawable.plus_ico), contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .width(50.dp)
                            .height(50.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center,
            ){
                Button(
                    onClick = {
                        navController.navigate(Screen.MainScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFD9D9D9)),
                    shape = RectangleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .padding(10.dp)
                ) {
                    Text(text = "OK",
                        fontFamily = robotoCondensed,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                }
            }
        }
    }
}