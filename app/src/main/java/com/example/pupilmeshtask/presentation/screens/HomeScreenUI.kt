package com.example.pupilmeshtask.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.pupilmeshtask.R
import com.example.pupilmeshtask.ResultState
import com.example.pupilmeshtask.presentation.components.LoadingBar
import com.example.pupilmeshtask.presentation.viewmodel.MangaViewModel

@Composable
fun HomeScreenUI(navController: NavController, viewModel: MangaViewModel = hiltViewModel()) {
    val state by viewModel.mangaState.collectAsState()
    val data = (state as? ResultState.Success)?.data ?: emptyList()

    LaunchedEffect(Unit) {
        if (state !is ResultState.Success) {
            Log.d("LaunchedEffect", "Fetching manga...")
            viewModel.fetchManga()
        }
    }
    when (state) {
        is ResultState.Loading -> {
            LoadingBar()
        }

        is ResultState.Error -> {
            Text("Error: ${(state as ResultState.Error).message}", color = Color.White)
        }

        is ResultState.Success -> {
            LazyColumn {
                items(data) { item ->

                    SubcomposeAsyncImage(
                        model = if (item.thumb.isNotEmpty()) item.thumb else R.drawable.no_image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(90.dp, 130.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        loading = {
                            LoadingBar(color = Color.LightGray)
                        }
                    )

                    Log.d("HomeScreenUI", "Manga Title: ${data}")
                }
            }
        }

    }

}
