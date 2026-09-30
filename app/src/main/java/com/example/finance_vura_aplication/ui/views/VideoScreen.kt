package com.example.finance_vura_aplication.ui.views

import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.finance_vura_aplication.R

@Composable
fun VideoScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Video educativo", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))
        AndroidView(
            modifier = Modifier.fillMaxWidth().aspectRatio(16f / 9f),
            factory = { ctx ->
                VideoView(ctx).apply {
                    setMediaController(MediaController(ctx).also { it.setAnchorView(this) })
                    setVideoURI(Uri.parse("android.resource://${ctx.packageName}/${R.raw.importancia_finanzas}"))
                }
            }
        )
    }
}
