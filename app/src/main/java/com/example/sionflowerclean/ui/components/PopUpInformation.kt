package com.example.sionflowerclean.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.composables.core.Icon
import com.example.sionflowerclean.data.model.PlantIdentificationResult

@Composable
fun PopupSample(result : PlantIdentificationResult, onDismiss: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = {
        result.images.size
    })

    Popup(alignment = Alignment.Center, onDismissRequest = {  }) {
        Column(
            Modifier
                .background(Color.White, RoundedCornerShape(40.dp))
                .padding(16.dp)
                .fillMaxWidth(0.9f)
                .verticalScroll(rememberScrollState())
                .clickable { }
        ) {
            Column(
                horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth(1f)
            )
            {
                IconButton(
                    onClick = { onDismiss() },
                    modifier = Modifier
                        .size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Cerrar",
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.size(5.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(1f).height(350.dp)
            ){
                HorizontalPager(state = pagerState) { page ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(result.images[page].url)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Imagen de Internet",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize(1f)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }

                Spacer(modifier = Modifier.size(5.dp))

                Row(
                    Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(10.dp)
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.size(15.dp))

            Text(
                text = result.scientificName,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.size(3.dp))

            Text(
                text = result.commonNames[0],
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.size(8.dp))

            TabInformation(result)

            Spacer(modifier = Modifier.size(20.dp))

            Column(modifier = Modifier.fillMaxWidth(1f),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Button(onClick = {}){
                    Text(
                        text = "Save Information",
                        fontSize = 18.sp,
                    )
                }
            }

        }
    }
}