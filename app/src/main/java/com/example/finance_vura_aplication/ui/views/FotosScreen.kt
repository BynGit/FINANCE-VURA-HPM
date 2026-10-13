package com.example.finance_vura_aplication.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.finance_vura_aplication.R
import com.example.finance_vura_aplication.ui.theme.CardGray
import com.example.finance_vura_aplication.ui.theme.DarkBlue
import com.example.finance_vura_aplication.ui.theme.TextGray

data class ImagenData(
    val id: Int,
    val resId: Int,
    val titulo: String,
    val descripcion: String
)

@Composable
fun FotosScreen(
    message: String = "Haz clic sobre cualquier imagen para expandirla a pantalla completa."
) {
    val imagenes = remember {
        listOf(
            ImagenData(
                id = 1,
                resId = R.drawable.imagen_1,
                titulo = "Imagen 1: Distribución de Gastos",
                descripcion = "Guía explicativa para la distribución responsable de gastos e ingresos mensuales."
            ),
            ImagenData(
                id = 2,
                resId = R.drawable.imagen_2,
                titulo = "Imagen 2: Plan de Ahorro",
                descripcion = "Estrategia paso a paso para construir un fondo de reserva y hábito de ahorro."
            ),
            ImagenData(
                id = 3,
                resId = R.drawable.imagen_3,
                titulo = "Imagen 3: Diagnóstico Financiero",
                descripcion = "Análisis de salud financiera, control de presupuesto y hábitos de consumo."
            ),
            ImagenData(
                id = 4,
                resId = R.drawable.imagen_4,
                titulo = "Imagen 4: Gestión de Inversiones",
                descripcion = "Conceptos fundamentales para la planificación de metas financieras e inversiones."
            )
        )
    }

    var imagenExpandida by remember { mutableStateOf<ImagenData?>(null) }
    val mainScrollState = rememberScrollState()

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
            // ENCABEZADO
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = null,
                    tint = DarkBlue,
                    modifier = Modifier.size(if (isCompact) 20.dp else 24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Galería de Imágenes",
                    fontWeight = FontWeight.Bold,
                    fontSize = if (isCompact) 16.sp else 18.sp,
                    color = DarkBlue
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = message,
                fontSize = if (isCompact) 11.sp else 12.sp,
                color = TextGray
            )

            Spacer(modifier = Modifier.height(14.dp))

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ImagenCard(
                        imagenData = imagenes[0],
                        isCompact = isCompact,
                        modifier = Modifier.weight(1f),
                        onClick = { imagenExpandida = imagenes[0] }
                    )
                    ImagenCard(
                        imagenData = imagenes[1],
                        isCompact = isCompact,
                        modifier = Modifier.weight(1f),
                        onClick = { imagenExpandida = imagenes[1] }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ImagenCard(
                        imagenData = imagenes[2],
                        isCompact = isCompact,
                        modifier = Modifier.weight(1f),
                        onClick = { imagenExpandida = imagenes[2] }
                    )
                    ImagenCard(
                        imagenData = imagenes[3],
                        isCompact = isCompact,
                        modifier = Modifier.weight(1f),
                        onClick = { imagenExpandida = imagenes[3] }
                    )
                }
            }
        }

        imagenExpandida?.let { img ->
            Dialog(
                onDismissRequest = { imagenExpandida = null },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.92f))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = img.titulo,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )

                            IconButton(
                                onClick = { imagenExpandida = null },
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(Color.DarkGray, CircleShape)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Cerrar",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f, fill = false)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White)
                        ) {
                            Image(
                                painter = painterResource(id = img.resId),
                                contentDescription = img.titulo,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(CardGray, shape = RoundedCornerShape(10.dp))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = img.descripcion,
                                color = Color(0xFF222222),
                                fontSize = 13.sp,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ImagenCard(
    imagenData: ImagenData,
    isCompact: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(if (isCompact) 160.dp else 190.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardGray)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Imagen recortada para la miniatura de la Card
            Image(
                painter = painterResource(id = imagenData.resId),
                contentDescription = imagenData.titulo,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Indicador de zoom/expandir
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(28.dp)
                    .background(DarkBlue.copy(alpha = 0.85f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ZoomIn,
                    contentDescription = "Expandir",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }

            // Pie de la tarjeta con el título de la imagen
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.65f))
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                Text(
                    text = imagenData.titulo,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = if (isCompact) 10.sp else 12.sp,
                    maxLines = 1
                )
            }
        }
    }
}
