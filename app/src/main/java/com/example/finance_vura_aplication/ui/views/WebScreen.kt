package com.example.finance_vura_aplication.ui.views

import android.annotation.SuppressLint
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebScreen(title: String = "Pantalla de Web") {
    var urlText by remember { mutableStateOf("https://www.google.com") }
    var currentUrlToLoad by remember { mutableStateOf("https://www.google.com") }

    fun cargarUrl(input: String) {
        var formatted = input.trim()
        if (formatted.isEmpty()) return
        if (!formatted.startsWith("http://") && !formatted.startsWith("https://")) {
            formatted = "https://$formatted"
        }
        currentUrlToLoad = formatted
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAEAEA))
            .padding(horizontal = 18.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp)
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(horizontal = 14.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                BasicTextField(
                    value = urlText,
                    onValueChange = { urlText = it },
                    singleLine = true,
                    textStyle = TextStyle(
                        color = Color(0xFF5C5C5C),
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Box(
                modifier = Modifier
                    .height(42.dp)
                    .width(58.dp)
                    .background(Color(0xFF0F6BB7), RoundedCornerShape(12.dp))
                    .clickable { cargarUrl(urlText) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Ir",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Bienvenido a FinanzApp",
            color = Color(0xFF1F1F1F),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 32.sp
        )

        Text(
            text = if (title.isNotBlank() && title != "Pantalla de Web") title else "Educación financiera para todos",
            color = Color(0xFF4A4A4A),
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp)
                    .background(Color(0xFFE3EAF2), RoundedCornerShape(12.dp))
                    .clickable {
                        urlText = "https://www.google.com/search?q=articulos+finanzas+personales"
                        cargarUrl("https://www.google.com/search?q=articulos+finanzas+personales")
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Artículos",
                    color = Color(0xFF3A516D),
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp)
                    .background(Color(0xFFEAF5E6), RoundedCornerShape(12.dp))
                    .clickable {
                        urlText = "https://www.google.com/search?q=calculadora+financiera"
                        cargarUrl("https://www.google.com/search?q=calculadora+financiera")
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Calculadoras",
                    color = Color(0xFF2F6B3A),
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // CONTENEDOR VISTA WEB / IFRAME QUE CARGA LA URL DIGITADA SIN ERR_CACHE_MISS
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
        ) {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        webViewClient = object : WebViewClient() {
                            @Suppress("OverridingDeprecatedMember")
                            override fun shouldOverrideUrlLoading(
                                view: WebView?,
                                url: String?
                            ): Boolean {
                                url?.let {
                                    urlText = it
                                    view?.loadUrl(it)
                                }
                                return true
                            }
                        }
                        // Ajustes para asegurar navegación fluida sin ERR_CACHE_MISS
                        settings.javaScriptEnabled = true
                        settings.domStorageEnabled = true
                        settings.cacheMode = WebSettings.LOAD_DEFAULT
                        settings.databaseEnabled = true
                        settings.loadWithOverviewMode = true
                        settings.useWideViewPort = true
                        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

                        loadUrl(currentUrlToLoad)
                    }
                },
                update = { webView ->
                    if (webView.url != currentUrlToLoad) {
                        webView.loadUrl(currentUrlToLoad)
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
