package com.example.adefault.newsapp.Screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.adefault.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NewsScreen(viewModel: NewsViewModel) {
    val customFontFamily = FontFamily(Font(R.font.poppinsbold))
    val customFontFamily2 = FontFamily(Font(R.font.poppinslight))

    val newsArticles by viewModel.newsArticles.collectAsState()
    val pagerState = rememberPagerState()

    if (newsArticles.isNotEmpty()) {
        val context = LocalContext.current

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            // Background Image
            Image(
                painter = painterResource(id = R.drawable.design),
                contentDescription = "ShortNews",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // News Content
            VerticalPager(
                count = newsArticles.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                val article = newsArticles[page]
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectHorizontalDragGestures { _, dragAmount ->
                                if (dragAmount < -20) { // Left swipe threshold
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                                    context.startActivity(intent)
                                }
                            }
                        }
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .fillMaxHeight(0.7f)
                            .clip(RoundedCornerShape(32.dp))
                            .shadow(8.dp)
                            .border(width = 10.dp, color = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp)),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        Column(modifier = Modifier.padding(32.dp)) {
                            AsyncImage(
                                model = article.urlToImage,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(16f / 9f)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = article.title,
                                style = MaterialTheme.typography.headlineMedium,
                                fontFamily = customFontFamily,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = article.description,
                                style = MaterialTheme.typography.bodyLarge,
                                fontFamily = customFontFamily2,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "By ShortNews",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}
