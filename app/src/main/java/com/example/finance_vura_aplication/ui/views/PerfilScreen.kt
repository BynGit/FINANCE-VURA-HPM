package com.example.finance_vura_aplication.ui.views

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finance_vura_aplication.ui.components.DashboardCard
import com.example.finance_vura_aplication.ui.theme.AvatarBlue
import com.example.finance_vura_aplication.ui.theme.CardGray
import com.example.finance_vura_aplication.ui.theme.DarkBlue
import com.example.finance_vura_aplication.ui.theme.TextGray

@Composable
fun PerfilScreen() {
    val mainScrollState = rememberScrollState()

    var esModoEdicion by remember { mutableStateOf(false) }

    var nombre by remember { mutableStateOf("Carlos Ramírez") }
    var cargo by remember { mutableStateOf("Ing. de Sistemas & Dev Android") }
    var email by remember { mutableStateOf("carlos.ramirez@financevura.com") }
    var telefono by remember { mutableStateOf("+57 300 987 6543") }
    var ubicacion by remember { mutableStateOf("Bogotá, Colombia") }

    var sobreMi by remember {
        mutableStateOf(
            "Ingeniero de Sistemas y Desarrollador Móvil con más de 5 años de experiencia diseñando y construyendo aplicaciones nativas para Android.\n" +
                    "Especializado en Kotlin, Jetpack Compose, arquitecturas limpias (MVVM/MVI) y optimización de UX.\n" +
                    "Apasionado por el desarrollo de software seguro y la creación de interfaces modernas e intuitivas."
        )
    }

    var estudios by remember {
        mutableStateOf(
            "🎓 Magíster en Ingeniería de Software\n" +
                    "Universidad Nacional de Colombia (2022 - 2024)\n" +
                    "Enfoque en arquitectura móvil y rendimiento.\n\n" +
                    "🎓 Pregrado en Ingeniería de Sistemas\n" +
                    "Universidad Distrital F.J.C. (2016 - 2021)\n" +
                    "Graduado con honores por proyecto meritorio.\n\n" +
                    "📜 Certificación Associate Android Dev - Google (2022)\n\n" +
                    "📜 Certificación Jetpack Compose & KMP (2023)"
        )
    }

    var experiencia by remember {
        mutableStateOf(
            "💼 Desarrollador Android Lead - FinanceVura (2022 - Presente)\n" +
                    "- Liderazgo técnico móvil con Jetpack Compose y Coroutines.\n" +
                    "- Diseño e integración de módulos financieros y Room.\n" +
                    "- Optimización de UI reduciendo latencia en un 40%.\n\n" +
                    "💼 Desarrollador Mobile Sr - TechSolutions (2020 - 2022)\n" +
                    "- Bibliotecas UI personalizadas e integración RESTful.\n" +
                    "- Cobertura de pruebas unitarias al 85% con JUnit.\n\n" +
                    "💼 Desarrollador Jr - InnovaSoft (2018 - 2020)\n" +
                    "- Mantenimiento y nuevas funciones en Android nativo."
        )
    }

    var habilidades by remember {
        mutableStateOf(
            "⚡ Habilidades Técnicas:\n" +
                    "Kotlin, Java, Jetpack Compose, Coroutines, StateFlow, Room, Retrofit, Hilt, Clean Arch, Git, CI/CD, Material 3.\n\n" +
                    "💡 Habilidades Blandas:\n" +
                    "Liderazgo técnico, resolución de problemas, Scrum/Kanban.\n\n" +
                    "🚀 Proyectos Destacados:\n" +
                    "- App Móvil FinanceVura (Compose & Material 3)\n" +
                    "- Billetera Digital con Autenticación Biométrica\n" +
                    "- Dashboard Analítico Financiero"
        )
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(contentAlignment = Alignment.BottomEnd) {
                        Box(
                            modifier = Modifier
                                .size(if (isCompact) 50.dp else 58.dp)
                                .background(AvatarBlue, shape = CircleShape)
                                .border(2.dp, DarkBlue, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (nombre.length >= 2) nombre.take(2).uppercase() else "BR",
                                color = DarkBlue,
                                fontWeight = FontWeight.Bold,
                                fontSize = if (isCompact) 17.sp else 20.sp
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(if (isCompact) 18.dp else 20.dp)
                                .background(DarkBlue, shape = CircleShape)
                                .border(1.dp, Color.White, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.CameraAlt,
                                contentDescription = "Foto",
                                tint = Color.White,
                                modifier = Modifier.size(if (isCompact) 10.dp else 11.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = nombre,
                            fontWeight = FontWeight.Bold,
                            fontSize = if (isCompact) 16.sp else 18.sp,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = cargo,
                            color = TextGray,
                            fontSize = if (isCompact) 11.sp else 12.sp,
                            fontWeight = FontWeight.Medium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Spacer(modifier = Modifier.width(6.dp))


                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (esModoEdicion) DarkBlue else AvatarBlue)
                        .clickable { esModoEdicion = !esModoEdicion }
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = if (esModoEdicion) Icons.Default.Save else Icons.Default.Edit,
                            contentDescription = "Editar",
                            tint = if (esModoEdicion) Color.White else DarkBlue,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (esModoEdicion) "Guardar" else "Editar",
                            color = if (esModoEdicion) Color.White else DarkBlue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            TarjetaContacto(
                email = email,
                telefono = telefono,
                ubicacion = ubicacion,
                isCompact = isCompact
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (isCompact) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    DashboardCard(title = "Experiencia", value = "5+ Años")
                    DashboardCard(title = "Ahorro del mes", value = "$420.000")
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        DashboardCard(title = "Experiencia", value = "5+ Años")
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        DashboardCard(title = "Ahorro del mes", value = "$420.000")
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))


            ScrollableCampoTexto(
                titulo = "Perfil Profesional",
                icono = Icons.Default.Person,
                texto = sobreMi,
                onTextoChange = { sobreMi = it },
                esModoEdicion = esModoEdicion,
                isCompact = isCompact,
                maxHeight = 115.dp
            )

            Spacer(modifier = Modifier.height(12.dp))


            ScrollableCampoTexto(
                titulo = "Estudios y Formación Académica",
                icono = Icons.Default.School,
                texto = estudios,
                onTextoChange = { estudios = it },
                esModoEdicion = esModoEdicion,
                isCompact = isCompact,
                maxHeight = 125.dp
            )

            Spacer(modifier = Modifier.height(12.dp))


            ScrollableCampoTexto(
                titulo = "Experiencia Laboral",
                icono = Icons.Default.Work,
                texto = experiencia,
                onTextoChange = { experiencia = it },
                esModoEdicion = esModoEdicion,
                isCompact = isCompact,
                maxHeight = 125.dp
            )

            Spacer(modifier = Modifier.height(12.dp))

            ScrollableCampoTexto(
                titulo = "Habilidades y Logros",
                icono = Icons.Default.Star,
                texto = habilidades,
                onTextoChange = { habilidades = it },
                esModoEdicion = esModoEdicion,
                isCompact = isCompact,
                maxHeight = 125.dp
            )
        }
    }
}

@Composable
private fun TarjetaContacto(
    email: String,
    telefono: String,
    ubicacion: String,
    isCompact: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardGray, shape = RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            RowContactoItem(icon = Icons.Default.Email, text = email, isCompact = isCompact)
            RowContactoItem(icon = Icons.Default.Phone, text = telefono, isCompact = isCompact)
            RowContactoItem(icon = Icons.Default.LocationOn, text = ubicacion, isCompact = isCompact)
        }
    }
}

@Composable
private fun RowContactoItem(
    icon: ImageVector,
    text: String,
    isCompact: Boolean
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = DarkBlue,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = Color(0xFF424242),
            fontSize = if (isCompact) 11.sp else 12.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun ScrollableCampoTexto(
    titulo: String,
    icono: ImageVector,
    texto: String,
    onTextoChange: (String) -> Unit,
    esModoEdicion: Boolean,
    isCompact: Boolean,
    maxHeight: Dp = 120.dp
) {
    val scrollState = rememberScrollState()
    val paddingInside = if (isCompact) 10.dp else 14.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardGray, shape = RoundedCornerShape(12.dp))
            .padding(paddingInside)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(if (isCompact) 26.dp else 30.dp)
                        .background(AvatarBlue, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icono,
                        contentDescription = titulo,
                        tint = DarkBlue,
                        modifier = Modifier.size(if (isCompact) 15.dp else 17.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = titulo,
                    fontWeight = FontWeight.Bold,
                    fontSize = if (isCompact) 13.sp else 15.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = maxHeight)
            ) {
                if (esModoEdicion) {
                    OutlinedTextField(
                        value = texto,
                        onValueChange = onTextoChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = maxHeight)
                            .padding(end = 6.dp)
                            .verticalScroll(scrollState)
                            .drawVerticalScrollbar(scrollState),
                        textStyle = TextStyle(
                            fontSize = if (isCompact) 12.sp else 13.sp,
                            color = Color.Black,
                            lineHeight = if (isCompact) 16.sp else 18.sp
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = DarkBlue,
                            unfocusedBorderColor = Color.LightGray,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        )
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp)
                            .verticalScroll(scrollState)
                            .drawVerticalScrollbar(scrollState)
                    ) {
                        Text(
                            text = texto,
                            fontSize = if (isCompact) 12.sp else 13.sp,
                            color = Color(0xFF333333),
                            lineHeight = if (isCompact) 16.sp else 18.sp
                        )
                    }
                }
            }
        }
    }
}

private fun Modifier.drawVerticalScrollbar(
    scrollState: ScrollState,
    color: Color = DarkBlue,
    width: Dp = 4.dp
): Modifier = this.drawWithContent {
    drawContent()
    if (scrollState.maxValue > 0) {
        val viewportHeight = size.height
        val totalHeight = viewportHeight + scrollState.maxValue
        val scrollbarHeight = ((viewportHeight / totalHeight) * viewportHeight).coerceAtLeast(20.dp.toPx())
        val scrollbarTop = (scrollState.value.toFloat() / scrollState.maxValue) * (viewportHeight - scrollbarHeight)


        drawRoundRect(
            color = Color.LightGray.copy(alpha = 0.35f),
            topLeft = Offset(size.width - width.toPx(), 0f),
            size = Size(width.toPx(), viewportHeight),
            cornerRadius = CornerRadius(width.toPx() / 2, width.toPx() / 2)
        )

        drawRoundRect(
            color = color,
            topLeft = Offset(size.width - width.toPx(), scrollbarTop),
            size = Size(width.toPx(), scrollbarHeight),
            cornerRadius = CornerRadius(width.toPx() / 2, width.toPx() / 2)
        )
    }
}
