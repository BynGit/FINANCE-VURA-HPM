package com.example.finance_vura_aplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.finance_vura_aplication.ui.theme.DarkBlue
import com.example.finance_vura_aplication.ui.theme.SelectedBlueBg
import com.example.finance_vura_aplication.ui.theme.TextGray

@Composable
fun StaticSideMenu(navController: NavHostController, items: List<String>, modifier: Modifier = Modifier) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "Perfil"

    Column(
        modifier = modifier.fillMaxHeight() ,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        items.forEach { item ->
            val isSelected = currentRoute == item

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(if (isSelected) SelectedBlueBg else Color.Transparent)
                    .clickable {
                        navController.navigate(item) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = item,
                    color = if (isSelected) DarkBlue else TextGray,
                    fontSize = 14.sp
                )


                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .width(4.dp)
                            .fillMaxHeight()
                            .background(DarkBlue)
                    )
                }
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

    }
}

