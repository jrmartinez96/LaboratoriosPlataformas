package com.josemartinez.laboratorio2

interface LapHistory {

    val lapHistory: ArrayList<Int> // Historial de vueltas

    fun clear() // Limpiar historial
    fun add(element: Int) // Agregar elemento
    fun del(elementIndex: Int) // Elimina elemento en la posición
}