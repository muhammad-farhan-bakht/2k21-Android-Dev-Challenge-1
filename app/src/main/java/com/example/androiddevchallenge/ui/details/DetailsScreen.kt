package com.example.androiddevchallenge.ui.details

import android.annotation.SuppressLint
import androidx.annotation.IdRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.model.Cats

@ExperimentalFoundationApi
@Composable
fun DetailsScreen(catModel: Cats, onBackPressed: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Cat Details",
                        style = TextStyle(
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                },
                backgroundColor = Color.White,
                elevation = 8.dp,
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.clickable {
                            onBackPressed.invoke()
                        }
                    )
                }
            )
        },
        content = {
            DetailsScreenContent(catModel)
        }
    )
}

@ExperimentalFoundationApi
@Composable
fun DetailsScreenContent(catModel: Cats) {
    LazyColumn(
        horizontalAlignment = Alignment.Start,
        contentPadding = PaddingValues(bottom = 10.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            HeaderPhoto(catModel.image)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = catModel.name,
                style = TextStyle(
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                modifier = Modifier.padding(start = 10.dp)
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 5.dp, 10.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoBox(infoKey = "Age", infoValue = catModel.age)
                InfoBox(infoKey = "Color", infoValue = catModel.color)
                InfoBox(infoKey = "Weight", infoValue = catModel.weight)
            }
        }

        item {
            Text(
                text = "Cat Story",
                style = TextStyle(
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                modifier = Modifier.padding(start = 10.dp, top = 10.dp)
            )
            Text(
                text = catModel.story,
                style = TextStyle(
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                ),
                modifier = Modifier.padding(start = 10.dp, top = 5.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Adopt Me",
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@SuppressLint("ResourceType")
@Composable
fun HeaderPhoto(@IdRes image: Int) {
    Image(
        painter = painterResource(id = image),
        contentScale = ContentScale.Crop,
        contentDescription = "Pet Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}

@ExperimentalFoundationApi
@Composable
fun InfoBox(infoKey: String, infoValue: String) {
    Box(
        modifier = Modifier
            .size(90.dp)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.wrapContentWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = infoValue,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                ),
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = infoKey,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            )
        }
    }
}
