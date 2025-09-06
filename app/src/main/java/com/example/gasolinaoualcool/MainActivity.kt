
package com.example.gasolinaoualcool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GasolinaOuAlcoolApp()
        }
    }
}

@Composable
fun GasolinaOuAlcoolApp() {
    var precoGasolina by remember { mutableStateOf("") }
    var precoAlcool by remember { mutableStateOf("") }
    var melhorCombustivel by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Gasolina ou Álcool",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        )

        if (melhorCombustivel.isNotEmpty()) {
            Text(
                text = "Melhor opção: ${melhorCombustivel}",
                style = TextStyle(
                    color = Color.Red,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        fun Calcular() {
            val valorGasolina = precoGasolina.toDoubleOrNull() ?: 0.0
            val valorAlcool = precoAlcool.toDoubleOrNull() ?: 0.0

            if (valorGasolina > 0 && valorAlcool > 0) {
                melhorCombustivel = if (valorAlcool / valorGasolina <= 0.7) {
                    "Álcool"
                } else {
                    "Gasolina"
                }
            } else {
                melhorCombustivel = "Valores inválidos"
            }

        }


        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = precoGasolina,
            onValueChange = { precoGasolina = it
                            Calcular()},
            label = { Text("Preço da Gasolina") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(fontSize = 18.sp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = precoAlcool,
            onValueChange = { precoAlcool = it
                            Calcular()},
            label = { Text("Preço do Álcool") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(fontSize = 18.sp)
        )

        Spacer(modifier = Modifier.height(24.dp))

    }
}

@Composable
@Preview(showBackground = true)
fun GasolinaOuAlcoolPreview() {
    GasolinaOuAlcoolApp()
}
