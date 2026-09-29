package com.example.finance_vura_aplication.ui.views

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SmartButton
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finance_vura_aplication.ui.theme.AvatarBlue
import com.example.finance_vura_aplication.ui.theme.CardGray
import com.example.finance_vura_aplication.ui.theme.DarkBlue

@Composable
fun Accionescreen(
    title: String = "Pantalla de Acciones"
) {
    val scrollState = rememberScrollState()

    var contador by remember { mutableIntStateOf(0) }
    var ultimaAccion by remember { mutableStateOf("Ninguna interacción aún") }
    var mostrarAlerta by remember { mutableStateOf(false) }
    var mensajeAlerta by remember { mutableStateOf("") }
    var tituloAlerta by remember { mutableStateOf("Aviso del Sistema") }
    var esFavorito by remember { mutableStateOf(false) }
    var categoriaSeleccionada by remember { mutableStateOf("Todos") }

    fun dispararAlerta(titulo: String, mensaje: String, accionNombre: String) {
        contador++
        ultimaAccion = accionNombre
        tituloAlerta = titulo
        mensajeAlerta = mensaje
        mostrarAlerta = true
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val availableWidth = maxWidth
        val isCompact = availableWidth < 360.dp
        val outerPadding = if (isCompact) 10.dp else 16.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(outerPadding),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.SmartButton,
                    contentDescription = null,
                    tint = DarkBlue,
                    modifier = Modifier.size(if (isCompact) 22.dp else 26.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title.ifBlank { "Botones e Interacciones" },
                    fontWeight = FontWeight.Bold,
                    fontSize = if (isCompact) 18.sp else 22.sp,
                    color = DarkBlue
                )
            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AvatarBlue, shape = RoundedCornerShape(12.dp))
                    .padding(14.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "🔢 Contador de Clics:",
                            fontWeight = FontWeight.Bold,
                            fontSize = if (isCompact) 13.sp else 14.sp,
                            color = DarkBlue
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(DarkBlue)
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "$contador",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Última acción: $ultimaAccion",
                        fontSize = if (isCompact) 11.sp else 12.sp,
                        color = Color(0xFF222222),
                        fontWeight = FontWeight.Medium
                    )
                }
            }


            Text(
                text = "1. Lanzador de Alerta (`AlertDialog`):",
                fontWeight = FontWeight.Bold,
                fontSize = if (isCompact) 12.sp else 13.sp,
                color = Color.Black
            )

            Button(
                onClick = {
                    dispararAlerta(
                        titulo = "🔔 Alerta de Notificación",
                        mensaje = "¡Has activado la alerta del sistema! El contador de clics ha aumentado a ${contador + 1}.",
                        accionNombre = "Lanzar Alerta Principal"
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DarkBlue)
            ) {
                Icon(
                    imageVector = Icons.Default.NotificationsActive,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Lanzar Alerta Pop-up", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }


            Text(
                text = "2. Botones de Acción (Filled & Outlined):",
                fontWeight = FontWeight.Bold,
                fontSize = if (isCompact) 12.sp else 13.sp,
                color = Color.Black
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CardGray, shape = RoundedCornerShape(12.dp))
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Button(
                    onClick = {
                        dispararAlerta(
                            titulo = "✅ Transacción Guardada",
                            mensaje = "Se guardó con éxito el registro financiero en la base de datos.",
                            accionNombre = "Guardar Registro"
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
                ) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Guardar Registro Financiero", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }


                Button(
                    onClick = {
                        contador++
                        ultimaAccion = "Enviar Transferencia"
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = DarkBlue)
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Enviar Transferencia", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }


                OutlinedButton(
                    onClick = {
                        contador = 0
                        ultimaAccion = "Contador reiniciado"
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = null, tint = DarkBlue, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Reiniciar Contador", color = DarkBlue, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }


            Text(
                text = "3. Botones Conmutadores de Categoría (Chips):",
                fontWeight = FontWeight.Bold,
                fontSize = if (isCompact) 12.sp else 13.sp,
                color = Color.Black
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("Todos", "Ingresos", "Gastos", "Ahorros").forEach { cat ->
                    val selected = categoriaSeleccionada == cat
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (selected) DarkBlue else CardGray)
                            .clickable {
                                categoriaSeleccionada = cat
                                contador++
                                ultimaAccion = "Filtro: $cat"
                            }
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = cat,
                            color = if (selected) Color.White else Color.Black,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 11.sp
                        )
                    }
                }
            }


            Text(
                text = "4. Botones de Icono e Interactivos:",
                fontWeight = FontWeight.Bold,
                fontSize = if (isCompact) 12.sp else 13.sp,
                color = Color.Black
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CardGray, shape = RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        esFavorito = !esFavorito
                        contador++
                        ultimaAccion = if (esFavorito) "Añadido a Favoritos ❤️" else "Quitado de Favoritos"
                    }) {
                        Icon(
                            imageVector = if (esFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorito",
                            tint = if (esFavorito) Color.Red else DarkBlue,
                            modifier = Modifier.size(26.dp)
                        )
                    }

                    IconButton(onClick = {
                        contador++
                        ultimaAccion = "Compartir reporte"
                    }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Compartir",
                            tint = DarkBlue,
                            modifier = Modifier.size(26.dp)
                        )
                    }

                    IconButton(onClick = {
                        dispararAlerta(
                            titulo = "⚠️ Confirmar Eliminación",
                            mensaje = "¿Estás seguro de que deseas eliminar este registro?",
                            accionNombre = "Eliminar Registro"
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar",
                            tint = Color(0xFFC62828),
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "5. Botón Flotante de Acción (FAB):",
                    fontWeight = FontWeight.Bold,
                    fontSize = if (isCompact) 12.sp else 13.sp,
                    color = Color.Black
                )

                FloatingActionButton(
                    onClick = {
                        dispararAlerta(
                            titulo = "➕ Nuevo Registro",
                            mensaje = "Se ha abierto el formulario para crear un nuevo registro de gasto/ingreso.",
                            accionNombre = "Nuevo Registro (+ FAB)"
                        )
                    },
                    containerColor = DarkBlue,
                    contentColor = Color.White,
                    modifier = Modifier.size(46.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir")
                }
            }
        }


        if (mostrarAlerta) {
            AlertDialog(
                onDismissRequest = { mostrarAlerta = false },
                title = {
                    Text(
                        text = tituloAlerta,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = DarkBlue
                    )
                },
                text = {
                    Text(
                        text = mensajeAlerta,
                        fontSize = 14.sp,
                        color = Color(0xFF333333)
                    )
                },
                confirmButton = {
                    TextButton(onClick = { mostrarAlerta = false }) {
                        Text("Aceptar", fontWeight = FontWeight.Bold, color = DarkBlue)
                    }
                },
                shape = RoundedCornerShape(12.dp),
                containerColor = Color.White
            )
        }
    }
}
