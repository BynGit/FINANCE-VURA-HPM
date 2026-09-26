package com.example.finance_vura_aplication.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finance_vura_aplication.ui.components.DashboardCard
import com.example.finance_vura_aplication.ui.theme.AvatarBlue
import com.example.finance_vura_aplication.ui.theme.DarkBlue
import com.example.finance_vura_aplication.ui.theme.TextGray

@Composable
fun PerfilScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(AvatarBlue, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "BR", color = DarkBlue, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = "Brayan Ramírez", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                Text(text = "Meta de ahorro activa", color = TextGray, fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))


        DashboardCard(title = "Ahorro del mes", value = "$420.000")
        Spacer(modifier = Modifier.height(16.dp))
        DashboardCard(title = "Gastos registrados", value = "32")
    }
}

