package com.example.finance_vura_aplication.ui.views

import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.OndemandVideo
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.finance_vura_aplication.R
import com.example.finance_vura_aplication.ui.theme.AvatarBlue
import com.example.finance_vura_aplication.ui.theme.CardGray
import com.example.finance_vura_aplication.ui.theme.DarkBlue
import com.example.finance_vura_aplication.ui.theme.TextGray
import kotlinx.coroutines.delay
import java.util.Locale

@OptIn(UnstableApi::class)
@Composable
fun VideoScreen(
    title: String = "Video educativo: Importancia de las Finanzas"
) {
    val context = LocalContext.current
    val mainScrollState = rememberScrollState()

    var isPlaying by remember { mutableStateOf(false) }
    var currentPositionMs by remember { mutableLongStateOf(0L) }
    var totalDurationMs by remember { mutableLongStateOf(1L) }
    var isUserSeeking by remember { mutableStateOf(false) }
    var sliderFraction by remember { mutableFloatStateOf(0f) }

    // Instancia de Media3 ExoPlayer
    val exoPlayer = remember(context) {
        ExoPlayer.Builder(context).build().apply {
            val videoUri = Uri.parse("android.resource://${context.packageName}/${R.raw.importancia_finanzas}")
            setMediaItem(MediaItem.fromUri(videoUri))
            prepare()
            playWhenReady = false

            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    if (playbackState == Player.STATE_READY && duration > 0) {
                        totalDurationMs = duration
                    }
                }

                override fun onIsPlayingChanged(playing: Boolean) {
                    isPlaying = playing
                }
            })
        }
    }

    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }

    LaunchedEffect(exoPlayer) {
        while (true) {
            if (exoPlayer.playbackState == Player.STATE_READY && exoPlayer.duration > 0) {
                totalDurationMs = exoPlayer.duration
            }

            if (!isUserSeeking) {
                val pos = exoPlayer.currentPosition.coerceAtLeast(0L)
                currentPositionMs = pos
                isPlaying = exoPlayer.isPlaying
                sliderFraction = if (totalDurationMs > 0) {
                    (pos.toFloat() / totalDurationMs.toFloat()).coerceIn(0f, 1f)
                } else {
                    0f
                }
            }
            delay(200)
        }
    }

    fun adelantar10s() {
        val dur = if (totalDurationMs > 1000L) totalDurationMs else if (exoPlayer.duration > 0) exoPlayer.duration else 300000L
        val target = (exoPlayer.currentPosition + 10000L).coerceAtMost(dur)
        exoPlayer.seekTo(target)
        currentPositionMs = target
        if (dur > 0) {
            sliderFraction = (target.toFloat() / dur.toFloat()).coerceIn(0f, 1f)
        }
    }

    fun retroceder10s() {
        val dur = if (totalDurationMs > 1000L) totalDurationMs else if (exoPlayer.duration > 0) exoPlayer.duration else 300000L
        val target = (exoPlayer.currentPosition - 10000L).coerceAtLeast(0L)
        exoPlayer.seekTo(target)
        currentPositionMs = target
        if (dur > 0) {
            sliderFraction = (target.toFloat() / dur.toFloat()).coerceIn(0f, 1f)
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val availableWidth = maxWidth
        val isCompact = availableWidth < 360.dp
        val outerPadding = if (isCompact) 10.dp else 16.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(mainScrollState)
                .padding(outerPadding)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.OndemandVideo,
                    contentDescription = null,
                    tint = DarkBlue,
                    modifier = Modifier.size(if (isCompact) 22.dp else 26.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Reproductor de Video Educativo",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = DarkBlue,
                    fontSize = if (isCompact) 18.sp else 22.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = Color.Black)
            ) {
                Column {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f)
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        AndroidView(
                            factory = { ctx ->
                                PlayerView(ctx).apply {
                                    player = exoPlayer
                                    useController = true
                                    showController()
                                    controllerShowTimeoutMs = 3000
                                    setControllerHideOnTouch(true)
                                    setShowBuffering(PlayerView.SHOW_BUFFERING_ALWAYS)
                                }
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    }


                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF0F172A))
                            .padding(horizontal = 12.dp, vertical = 10.dp)
                    ) {

                        Slider(
                            value = sliderFraction.coerceIn(0f, 1f),
                            onValueChange = { newValue ->
                                isUserSeeking = true
                                sliderFraction = newValue
                            },
                            onValueChangeFinished = {
                                val dur = if (totalDurationMs > 1000L) totalDurationMs else if (exoPlayer.duration > 0) exoPlayer.duration else 1L
                                val targetMs = (sliderFraction * dur).toLong().coerceIn(0L, dur)
                                exoPlayer.seekTo(targetMs)
                                currentPositionMs = targetMs
                                isUserSeeking = false
                            },
                            valueRange = 0f..1f,
                            colors = SliderDefaults.colors(
                                thumbColor = DarkBlue,
                                activeTrackColor = AvatarBlue,
                                inactiveTrackColor = Color.DarkGray
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(24.dp)
                        )


                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = formatTimeMs(currentPositionMs),
                                color = Color.LightGray,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = formatTimeMs(totalDurationMs),
                                color = Color.LightGray,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Rebobinar -10s
                            IconButton(onClick = { retroceder10s() }) {
                                Icon(
                                    imageVector = Icons.Default.Replay10,
                                    contentDescription = "Retroceder 10s",
                                    tint = Color.White,
                                    modifier = Modifier.size(26.dp)
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .background(DarkBlue, CircleShape)
                                    .clickable {
                                        if (exoPlayer.isPlaying) {
                                            exoPlayer.pause()
                                        } else {
                                            exoPlayer.play()
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                    contentDescription = if (isPlaying) "Pausar" else "Reproducir",
                                    tint = Color.White,
                                    modifier = Modifier.size(28.dp)
                                )
                            }


                            IconButton(onClick = { adelantar10s() }) {
                                Icon(
                                    imageVector = Icons.Default.Forward10,
                                    contentDescription = "Adelantar 10s",
                                    tint = Color.White,
                                    modifier = Modifier.size(26.dp)
                                )
                            }


                            IconButton(onClick = {
                                exoPlayer.seekTo(0)
                                exoPlayer.play()
                            }) {
                                Icon(
                                    imageVector = Icons.Default.RestartAlt,
                                    contentDescription = "Reiniciar",
                                    tint = Color.White,
                                    modifier = Modifier.size(26.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CardGray, shape = RoundedCornerShape(12.dp))
                    .padding(14.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = if (isCompact) 14.sp else 16.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "FinanceVura Academy • Video Educativo de Finanzas",
                    color = TextGray,
                    fontSize = if (isCompact) 11.sp else 12.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Aprende conceptos fundamentales sobre la gestión de ingresos, control de presupuesto y la importancia del ahorro e inversión para tu futuro financiero.",
                    fontSize = if (isCompact) 12.sp else 13.sp,
                    color = Color(0xFF333333),
                    lineHeight = if (isCompact) 17.sp else 19.sp
                )
            }
        }
    }
}

private fun formatTimeMs(millis: Long): String {
    val totalSeconds = (millis / 1000).coerceAtLeast(0)
    val mins = totalSeconds / 60
    val secs = totalSeconds % 60
    return String.format(Locale.US, "%02d:%02d", mins, secs)
}
