package com.example.pupilmeshtask.presentation.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pupilmeshtask.presentation.viewmodel.MangaViewModel

@Composable
fun HomeScreenUI(navController: NavController, viewModel: MangaViewModel = hiltViewModel()) {
    val mangaItems = viewModel.mangaList.collectAsLazyPagingItems()

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(mangaItems.itemCount) { index ->
            Log.d("HomeScreenUI", "Item index: $index")
            val manga = mangaItems[index]
            manga?.let {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { navController.navigate("mangaDetail/${it.id}") }
                ) {
                    AsyncImage(
                        model = it.thumb,
                        contentDescription = it.title,
                        modifier = Modifier
                            .height(160.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = it.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White
                    )
                }
            }
        }
    }
}

