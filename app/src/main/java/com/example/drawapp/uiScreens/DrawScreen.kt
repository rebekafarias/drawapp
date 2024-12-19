package com.example.drawapp.uiScreens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.drawapp.model.Line  // Certifique-se de que a importação está correta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawScreen() {
    val lines = remember { mutableStateListOf<Line>() }  // Lista para armazenar as linhas desenhadas
    var currentColor by remember { mutableStateOf(Color.Black) }  // Cor atual da linha

    Column {
        // Barra superior com os botões de seleção de cor
        TopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.primary),
            title = {
                Text(
                    text = "Color Picker",
                    color = Color.White,
                    fontSize = 18.sp
                )
            },
            actions = {
                // Botões de seleção de cor
                Button(
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    onClick = { currentColor = Color.Black },
                    modifier = Modifier.padding(3.dp).width(40.dp)
                ) { /* BLACK COLOR */ }

                Button(
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    onClick = { currentColor = Color.Red },
                    modifier = Modifier.padding(3.dp).width(40.dp)
                ) { /* RED COLOR */ }

                Button(
                    colors = ButtonDefaults.buttonColors(Color.Green),
                    onClick = { currentColor = Color.Green },
                    modifier = Modifier.padding(3.dp).width(40.dp)
                ) { /* GREEN COLOR */ }

                Button(
                    colors = ButtonDefaults.buttonColors(Color.Blue),
                    onClick = { currentColor = Color.Blue },
                    modifier = Modifier.padding(3.dp).width(40.dp)
                ) { /* BLUE COLOR */ }

                Button(
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    onClick = { lines.clear() },
                    modifier = Modifier.padding(3.dp)
                ) {
                    Text(
                        text = "Clear",
                        color = Color.White)
                }

            }
        )

        // Canvas onde as linhas serão desenhadas
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(true) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()

                        val line = Line(
                            start = change.position - dragAmount, // Posição inicial
                            end = change.position, // Posição final
                            color = currentColor // Cor da linha
                        )

                        lines.add(line) // Adiciona a linha à lista de linhas
                    }
                }
        ) {
            // Desenha cada linha na lista de linhas
            lines.forEach { line ->
                drawLine(
                    color = line.color,   // Cor da linha
                    start = line.start,   // Posição inicial
                    end = line.end,       // Posição final
                    strokeWidth = line.strokeWidth, // Largura da linha
                    cap = StrokeCap.Round // Extremidade arredondada da linha
                )
            }
        }
    }
}
