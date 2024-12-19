package com.example.drawapp.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

// Corrigir o nome do parâmetro para "start" e garantir que a definição da classe seja única
data class Line(
    val start: Offset,  // Correção do nome para "start"
    val end: Offset,
    val color: Color,
    val strokeWidth: Float = 5f
)
