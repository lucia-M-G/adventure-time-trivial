package com.example.trivial.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trivial.viewmodel.TrivialViewModel
import kotlinx.coroutines.delay
import androidx.compose.ui.layout.ContentScale

@Composable
fun LaunchView(
    modifier: Modifier = Modifier,
    trivialViewModel: TrivialViewModel,
    navController: NavController
) {
    var isVisible by remember { mutableStateOf(true) }

    // El "launch" dura 3 segundos
    LaunchedEffect(Unit) {
        delay(3000L)
        isVisible = false
        navController.navigate(Routes.Menu.route) {
            popUpTo(Routes.Launch.route) { inclusive = true }
        }
    }

    if (isVisible) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF1A237E)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(top = 150.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = com.example.trivial.R.drawable.logo_trivial),
                    contentDescription = "Logo Hora de Aventuras",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(bottom = 20.dp),
                    contentScale = ContentScale.Fit
                )

                Text(
                    text = "TRIVIAL",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    letterSpacing = 4.sp
                )

                Text(
                    text = "HORA DE AVENTURAS",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFD600), // Amarillo
                    letterSpacing = 2.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(modifier = Modifier.height(40.dp))

                CircularProgressIndicator(
                    modifier = Modifier.size(40.dp),
                    color = Color(0xFFFFD600),
                    strokeWidth = 4.dp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Cargando aventura...",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.7f)
                )

                // "Créditos"
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 30.dp)
                        .align(Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Por: Lucía & Jordi",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.5f)
                    )
                    Text(
                        text = "© Hora de Aventuras - Cartoon Network",
                        fontSize = 10.sp,
                        color = Color.White.copy(alpha = 0.4f),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}