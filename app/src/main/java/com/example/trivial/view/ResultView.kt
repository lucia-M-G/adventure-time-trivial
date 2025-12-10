package com.example.trivial.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trivial.viewmodel.TrivialViewModel

@Composable
fun ResultView(
    modifier: Modifier = Modifier,
    trivialViewModel: TrivialViewModel,
    navController: NavController
) {
    val resultado by trivialViewModel.valorResult.observeAsState()

    // Mensaje según puntuación
    val puntuacion = resultado?.puntuacion ?: 0
    val mensaje = when {
        puntuacion >= 80 -> "¡MATEMÁTICAMENTE PERFECTO!\nEres un héroe de Ooo."
        puntuacion >= 50 -> "¡BUENA AVENTURA!\nTienes el corazón de un aventurero."
        else -> "¡La aventura continúa!\nSigue explorando y aprendiendo."
    }

    // Colores según puntuación
    val colorFondo = Color(0xFF1A237E)
    val colorDestacado = if (puntuacion >= 80) Color(0xFFFFD600) else Color(0xFF4CAF50)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorFondo
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Text(
                text = "AVENTURA\nCOMPLETADA",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge,
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.1f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // Puntuación en grande
                    Text(
                        text = "$puntuacion",
                        fontSize = 72.sp,
                        fontWeight = FontWeight.Black,
                        color = colorDestacado,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "PUNTOS",
                        fontSize = 18.sp,
                        color = Color.White.copy(alpha = 0.7f),
                        letterSpacing = 2.sp
                    )

                    Divider(
                        modifier = Modifier.fillMaxWidth(0.6f),
                        color = Color.White.copy(alpha = 0.2f),
                        thickness = 1.dp
                    )

                    Text(
                        text = mensaje,
                        fontSize = 18.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 24.sp
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Jugar de nuevo
                Button(
                    onClick = {
                        trivialViewModel.resetearRonda()
                        navController.navigate(Routes.Menu.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorDestacado,
                        contentColor = Color.Black
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
                ) {
                    Text(
                        text = "JUGAR DE NUEVO",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Volver al menú
                OutlinedButton(
                    onClick = {
                        navController.navigate(Routes.Menu.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White
                    ),
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color.White.copy(alpha = 0.5f)
                    )
                ) {
                    Text(
                        text = "VOLVER AL MENÚ",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}