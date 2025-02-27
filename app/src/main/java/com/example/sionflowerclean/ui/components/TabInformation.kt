package com.example.sionflowerclean.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sionflowerclean.data.model.PlantIdentificationResult

@Composable
fun TabInformation(result: PlantIdentificationResult) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Home", "Taxonomy", "Settings")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> HomeScreen()
            1 -> TaxonomyScreen(result)
            2 -> SettingsScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home", fontSize = 25.sp)
    }
}

@Composable
fun TaxonomyScreen(result: PlantIdentificationResult) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Taxonomic Classification", fontSize = 25.sp)
        Spacer(modifier = Modifier.height(5.dp).padding(16.dp))
        Row (modifier = Modifier.fillMaxWidth(1f)) {
            ElevatedCard(
                Modifier.weight(1f),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Family",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = result.family,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            ElevatedCard(
                Modifier.weight(1f),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Genus",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = result.genus,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Scientific Name :", fontSize = 25.sp)
        Text(text = result.scientificNameFull, fontSize = 17.sp)
        Text(text = "(${result.scientificNameFull.takeLast(3)} indicates ${result.genus} as" +
                " the authority)", fontSize = 14.sp)

        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Common Names :", fontSize = 25.sp)
        for (name in result.commonNames) {
            Text(text = "●  $name", fontSize = 17.sp)
        }

    }
}

@Composable
fun SettingsScreen() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Settings", fontSize = 25.sp)
    }
}