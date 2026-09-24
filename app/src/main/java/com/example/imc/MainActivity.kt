package com.example.imc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imc.ui.theme.IMCTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IMCScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun IMCScreen(modifier: Modifier = Modifier) {
    var corResultado by remember { mutableStateOf(Color(0xFF4CAF50)) }
    var altura by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var imc by remember { mutableStateOf(0.0) }
    var categoriaImc by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxWidth()) {

                // -- header --
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(color = colorResource(id = R.color.cor_app)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.bmi),
                        contentDescription = "Logo App",
                        modifier = Modifier
                            .size(80.dp)
                            .padding(vertical = 16.dp)
                    )

                    Text(
                        text = "Calculadora IMC",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                // -- formulário --
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .height(300.dp)
                        .offset(y = (-30).dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF9F6F6)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                    ) {

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Seus dados",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.cor_app)
                        )


                        Spacer(modifier = Modifier.height(18.dp))

                        OutlinedTextField(
                            value = altura,
                            onValueChange = { altura = it },
                            label = { Text(text = "Altura") },
                            modifier = Modifier.fillMaxWidth()

                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        OutlinedTextField(
                            value = peso,
                            onValueChange = { peso = it },
                            label = { Text(text = "Peso") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                imc = calcularIMC(
                                    altura = altura.toDouble(),
                                    peso = peso.toDouble()
                                )
                                categoriaImc = determinarCategoriaIMC(imc)
                                corResultado = determinarCorIMC(imc)
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "CALCULAR")
                        }
                    }
                }

                // -- card resultado --
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .offset(y = (-10).dp),
                    colors = CardDefaults.cardColors(
                        containerColor = corResultado
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = String.format("%.1f", imc),
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 28.sp
                        )
                        Text(
                            text = categoriaImc,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}