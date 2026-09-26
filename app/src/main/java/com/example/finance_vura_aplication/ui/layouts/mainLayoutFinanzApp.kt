package com.example.finance_vura_aplication.ui.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.finance_vura_aplication.ui.components.StaticSideMenu
import com.example.finance_vura_aplication.ui.navigation.AppNavigation
import com.example.finance_vura_aplication.ui.navigation.menuItems
import com.example.finance_vura_aplication.ui.theme.DarkBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mainLayoutFinanzApp() {

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "FINANCE-VURA - G10-HPMB01 - 🧑‍💻", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBlue,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {

            StaticSideMenu(
                navController = navController,
                items = menuItems,
                modifier = Modifier.width(110.dp)
            )


            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(Color.LightGray.copy(alpha = 0.3f))
            )


            AppNavigation(navController)




        }
    }
}

