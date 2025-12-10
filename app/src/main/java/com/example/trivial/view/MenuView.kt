package com.example.trivial.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trivial.viewmodel.TrivialViewModel

@Composable
fun MenuView(
    modifier: Modifier = Modifier,
    trivialViewModel: TrivialViewModel,
    navController: NavController
) {
    // 1=Fácil, 2=Normal, 3=Difícil
    var dificultadSeleccionada by rememberSaveable { mutableIntStateOf(1) }

    // Recordar cambiar colores, estos son muy feos
    val colorFacil = Color(0xFF4CAF50)
    val colorNormal = Color(0xFFFF9800)
    val colorDificil = Color(0xFFF44336)
    val colorFondo = Color(0xFF1A237E)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorFondo
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp, 50.dp, 24.dp, 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            // Título
            Text(
                text = "¡TRIVIAL\nHORA DE AVENTURAS!",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                lineHeight = 40.sp
            )

            Text(
                text = "Elige tu dificultad",
                fontSize = 20.sp,
                color = Color.White.copy(alpha = 0.8f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Selector de dificultad
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Fácil
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(
                        containerColor = if (dificultadSeleccionada == 1) colorFacil else colorFacil.copy(alpha = 0.3f)
                    ),
                    border = if (dificultadSeleccionada == 1) CardDefaults.outlinedCardBorder() else null,
                    elevation = CardDefaults.cardElevation(defaultElevation = if (dificultadSeleccionada == 1) 8.dp else 2.dp),
                    onClick = {
                        dificultadSeleccionada = 1
                        trivialViewModel.cambiarDificultad(1)
                    }
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "FÁCIL",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (dificultadSeleccionada == 1) Color.White else colorFacil
                        )
                        Text(
                            text = "Para principiantes de Ooo",
                            fontSize = 14.sp,
                            color = if (dificultadSeleccionada == 1) Color.White.copy(alpha = 0.9f) else colorFacil.copy(alpha = 0.8f)
                        )
                    }
                }

                // Normal
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(
                        containerColor = if (dificultadSeleccionada == 2) colorNormal else colorNormal.copy(alpha = 0.3f)
                    ),
                    border = if (dificultadSeleccionada == 2) CardDefaults.outlinedCardBorder() else null,
                    elevation = CardDefaults.cardElevation(defaultElevation = if (dificultadSeleccionada == 2) 8.dp else 2.dp),
                    onClick = {
                        dificultadSeleccionada = 2
                        trivialViewModel.cambiarDificultad(2)
                    }
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "NORMAL",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (dificultadSeleccionada == 2) Color.White else colorNormal
                        )
                        Text(
                            text = "Un aventurero experimentado",
                            fontSize = 14.sp,
                            color = if (dificultadSeleccionada == 2) Color.White.copy(alpha = 0.9f) else colorNormal.copy(alpha = 0.8f)
                        )
                    }
                }

                // Difícil
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(
                        containerColor = if (dificultadSeleccionada == 3) colorDificil else colorDificil.copy(alpha = 0.3f)
                    ),
                    border = if (dificultadSeleccionada == 3) CardDefaults.outlinedCardBorder() else null,
                    elevation = CardDefaults.cardElevation(defaultElevation = if (dificultadSeleccionada == 3) 8.dp else 2.dp),
                    onClick = {
                        dificultadSeleccionada = 3
                        trivialViewModel.cambiarDificultad(3)
                    }
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "DIFÍCIL",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (dificultadSeleccionada == 3) Color.White else colorDificil
                        )
                        Text(
                            text = "¡Matemáticamente desafiante!",
                            fontSize = 14.sp,
                            color = if (dificultadSeleccionada == 3) Color.White.copy(alpha = 0.9f) else colorDificil.copy(alpha = 0.8f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botón de empezar
            Button(
                onClick = {
                    trivialViewModel.resetearRonda()
                    trivialViewModel.generarPregunta()
                    navController.navigate(Routes.Game.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFD600),
                    contentColor = Color.Black
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 4.dp
                )
            ) {
                Text(
                    text = "¡COMENZAR AVENTURA!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}