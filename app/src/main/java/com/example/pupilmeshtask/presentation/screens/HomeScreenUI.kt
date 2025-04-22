package com.example.pupilmeshtask.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.pupilmeshtask.R
import com.example.pupilmeshtask.navigation.MangaDetailScreenRoute
import com.example.pupilmeshtask.presentation.components.LoadingBar
import com.example.pupilmeshtask.presentation.viewmodel.MangaViewModel

@Composable
fun HomeScreenUI(
    navController: NavController,
    viewModel: MangaViewModel = hiltViewModel()
) {
    val mangaItems = remember { viewModel.mangaList }.collectAsLazyPagingItems()
    if (mangaItems.itemCount == 0 && mangaItems.loadState.refresh is LoadState.Error) {
        val error = mangaItems.loadState.refresh as LoadState.Error
        Text("Error: ${error.error.message}", color = Color.Red)
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(mangaItems.itemCount) { index ->
                val manga = mangaItems[index]
                manga?.let {
                    Column(
                        modifier = Modifier
                            .padding(6.dp)
                            .clickable { navController.navigate(MangaDetailScreenRoute(id = it.id)) },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SubcomposeAsyncImage(
                            model =
                                ImageRequest.Builder(LocalContext.current)
                                    .data(if (it.thumb.isNotEmpty()) it.thumb else R.drawable.no_image)
                                    .crossfade(true)
                                    .diskCachePolicy(CachePolicy.ENABLED)
                                    .memoryCachePolicy(CachePolicy.ENABLED)
                                    .build(),

                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp)),
                            loading = {
                                LoadingBar(color = Color.LightGray)
                            }
                        )
                        Text(it.title.take(15), fontSize = 16.sp, fontWeight = FontWeight.Bold)

                    }
                }
            }


        }
        mangaItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    LoadingBar()
                }

                loadState.refresh is LoadState.Error -> {
                    val error = loadState.refresh as LoadState.Error
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Error: ${error.error.message}", color = Color.Red)
                    }
                }

                loadState.append is LoadState.Loading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}
