package com.example.pupilmeshtask.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.pupilmeshtask.R
import com.example.pupilmeshtask.presentation.components.LoadingBar
import com.example.pupilmeshtask.presentation.viewmodel.MangaViewModel

@Composable
fun MangaDetailsScreenUI(
    navController: NavController,
    id: String,
    viewModel: MangaViewModel = hiltViewModel()
) {

    val state = viewModel.mangaList.collectAsLazyPagingItems()
    val manga = state.itemSnapshotList.items.find { it.id == id }

    manga?.let {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
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
                        .height(220.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .weight(0.4f),
                    loading = {
                        LoadingBar(color = Color.LightGray)
                    }
                )
                Column(
                    modifier = Modifier.weight(0.6f),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(it.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(it.sub_title, fontSize = 12.sp, lineHeight = 18.sp)
                }
            }
            Text(it.summary, fontSize = 16.sp, )


        }
    }
}
