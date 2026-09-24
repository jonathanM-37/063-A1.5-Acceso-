package com.example.a063a15acceso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Aplicacion()
            }
        }
    }
}

@Composable
fun Aplicacion() {

    var usuarioAutenticado by remember {
        mutableStateOf(false)
    }

    if (usuarioAutenticado) {

        PantallaUsuario(
            onCerrarSesion = {
                usuarioAutenticado = false
            }
        )

    } else {

        PantallaLogin(
            onLoginCorrecto = {
                usuarioAutenticado = true
            }
        )
    }
}

@Composable
fun PantallaLogin(
    onLoginCorrecto: () -> Unit
) {

    var numeroControl by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var mensajeError by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // LOGO DEL PANEL LOGOTEC
        Image(
            painter = painterResource(
                id = R.drawable.panel_logotec
            ),
            contentDescription = "Logo Panel Logotec",
            modifier = Modifier.size(170.dp)
        )

        Spacer(
            modifier = Modifier.height(35.dp)
        )

        Text(
            text = "Inicio de sesión",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(
            modifier = Modifier.height(25.dp)
        )

        // NÚMERO DE CONTROL
        OutlinedTextField(
            value = numeroControl,

            onValueChange = {

                // Solamente permite números
                if (it.all { caracter -> caracter.isDigit() }) {

                    // Máximo 8 números
                    if (it.length <= 8) {
                        numeroControl = it
                    }
                }
            },

            label = {
                Text("Número de control")
            },

            placeholder = {
                Text("23270063")
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),

            singleLine = true,

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // CONTRASEÑA
        OutlinedTextField(
            value = password,

            onValueChange = {
                password = it
            },

            label = {
                Text("Password")
            },

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),

            // Oculta la contraseña
            visualTransformation = PasswordVisualTransformation(),

            singleLine = true,

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // MENSAJE DE ERROR
        if (mensajeError.isNotEmpty()) {

            Text(
                text = mensajeError,
                color = MaterialTheme.colorScheme.error
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )
        }

        // BOTÓN INGRESAR
        Button(
            onClick = {

                if (numeroControl.length != 8) {

                    mensajeError =
                        "El número de control debe contener 8 números."

                } else if (
                    numeroControl == "23270063" &&
                    password == "Password"
                ) {

                    mensajeError = ""

                    onLoginCorrecto()

                } else {

                    mensajeError =
                        "Número de control o password incorrectos."
                }
            },

            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Ingresar")
        }
    }
}

@Composable
fun PantallaUsuario(
    onCerrarSesion: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // FOTO DE JONATHAN
        Image(
            painter = painterResource(
                id = R.drawable.jonathan
            ),
            contentDescription = "Fotografía de Jonathan",
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        Text(
            text = "Nombre: Jonathan Mendez Martinez",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        Text(
            text = "Carrera: Ing. sistemas",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        Text(
            text = "Semestre: 8vo semestre",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        Text(
            text = "Nacimiento: 30/12/2004",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        Button(
            onClick = onCerrarSesion
        ) {

            Text("Cerrar sesión")
        }
    }
}