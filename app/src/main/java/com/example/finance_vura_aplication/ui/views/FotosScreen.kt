package com.example.finance_vura_aplication.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val CardBlue = Color(0xFFE3F0FC)
private val CardGreen = Color(0xFFE4F5DF)
private val CardOrange = Color(0xFFFBEFDA)
private val CardPink = Color(0xFFFDE4E4)

@Composable
fun FotosScreen(
    message: String = "Infografía seleccionada: distribución de gastos mensuales."
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ColorCard(CardBlue)
            ColorCard(CardGreen)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ColorCard(CardOrange)
            ColorCard(CardPink)
        }

        Text(
            text = message,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray,
            modifier = Modifier.padding(top = 18.dp),
            fontSize = 18.sp
        )
    }
}

@Composable
private fun RowScope.ColorCard(color: Color) {
    Card(
        modifier = Modifier
            .weight(1f)
            .height(200.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {}
}
