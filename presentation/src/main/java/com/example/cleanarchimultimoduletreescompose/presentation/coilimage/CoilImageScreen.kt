package com.example.cleanarchitreescompose.trees_feature.presentation.coilimage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun CoilImageScreen() {
    val painter =
        rememberImagePainter(data = "https://images.unsplash.com/photo-1628373383885-4be0bc0172fa?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1301&q=80")
    Image(
        painter = painter,
        contentDescription = "Forest Image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Text(
        text = "Other tab",
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onSurface
    )
}