package com.example.myapplicationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplicationcompose.ui.theme.MyApplicationComposeTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationComposeTheme {
                CalculatorScreen()
            }
        }
    }

    @Composable
    fun CalculatorScreen() {
        var num1 by remember { mutableStateOf("") }
        var num2 by remember { mutableStateOf("") }
        var resultado by remember { mutableStateOf<String?>(null) }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Calculadora",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Blue,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                OutlinedTextField(
                    value = num1,
                    onValueChange = { num1 = it },
                    label = { Text("Número 1") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = num2,
                    onValueChange = { num2 = it },
                    label = { Text("Número 2") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            resultado = calculate(num1, num2) { a, b -> a + b }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Sumar")
                    }

                    Button(
                        onClick = {
                            resultado = calculate(num1, num2) { a, b -> a - b }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Restar")
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            resultado = calculate(num1, num2) { a, b -> a * b }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Multiplicar")
                    }

                    Button(
                        onClick = {
                            resultado = if (num2.toFloatOrNull() == 0f) {
                                "Error: División por 0"
                            } else {
                                calculate(num1, num2) { a, b -> a / b }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Dividir")
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                resultado?.let {
                    Text("Resultado: $it", style = MaterialTheme.typography.headlineSmall)
                }
            }
        }
    }

    private fun calculate(n1: String, n2: String, operation: (Float, Float) -> Float): String {
        val a = n1.toFloatOrNull()
        val b = n2.toFloatOrNull()
        return if (a != null && b != null) {
            operation(a, b).toString()
        } else {
            "Ingresa números válidos"
        }
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun PreviewCalculator() {
        MyApplicationComposeTheme {
            CalculatorScreen()
        }
    }
}
