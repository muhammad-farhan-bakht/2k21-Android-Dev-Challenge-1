package com.example.androiddevchallenge.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.model.Cats
import com.example.androiddevchallenge.utils.DataProvider.getCats

@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}

@ExperimentalFoundationApi
@Composable
fun HomeScreen(catClicked: (Cats) -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Adopt Meow",
                        style = TextStyle(
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                },
                backgroundColor = Color.White,
                elevation = 8.dp
            )
        },
        content = {
            HomeScreenContent(catClicked)
        }
    )
}

@ExperimentalFoundationApi
@Composable
fun HomeScreenContent(catClicked: (Cats) -> Unit = {}) {
    Column(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        CatsList(catClicked)
    }
}

@ExperimentalFoundationApi
@Composable
fun CatsList(catClicked: (Cats) -> Unit = {}) {
    Text(
        text = "Newest Cats",
        style = TextStyle(
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    )
    Spacer(modifier = Modifier.height(5.dp))

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(
            items = getCats(),
            itemContent = { item ->
                CatItem(item, catClicked)
            }
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun CatItem(catModel: Cats, catClicked: (Cats) -> Unit = {}) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(15.dp)
            )
            .clickable {
                catClicked.invoke(catModel)
            }
    ) {
        Image(
            painter = painterResource(id = catModel.image),
            contentScale = ContentScale.Crop,
            contentDescription = catModel.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(15.dp)),
        )
        Text(
            text = catModel.name,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(
                    color = Color(0x40000000),
                    shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp, topEnd = 15.dp, topStart = 15.dp)
                )
                .requiredWidth(200.dp)
                .align(alignment = Alignment.BottomCenter)
                .padding(5.dp)
        )
    }
}