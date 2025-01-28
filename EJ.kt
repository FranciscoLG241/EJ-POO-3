

import kotlin.math.pow

class Persona(val peso: Double, var altura: Double) {

    var nombre: String = ""
        set(value) {
            require(value.isNotBlank()) { "El nombre no puede ser nulo o vacío." }
            field = value
        }

    val imc: Double
        get() = calcularIMC()

    constructor(nombre: String, peso: Double, altura: Double) : this(peso, altura) {
        this.nombre = nombre
    }

    private fun calcularIMC(): Double {
        return peso / altura.pow(2)
    }

    fun saludar(): String {
        return "Hola, soy $nombre."
    }

    fun alturaEncimaMedia(): Boolean {
        return altura >= 1.75
    }

    fun pesoEncimaMedia(): Boolean {
        return peso >= 70
    }

    private fun obtenerDescImc(): String {
        return when {
            imc < 18.5 -> "peso insuficiente"
            imc in 18.5..24.9 -> "peso saludable"
            imc in 25.0..29.9 -> "sobrepeso"
            else -> "obesidad"
        }
    }

    fun obtenerDesc(): String {
        val alturaEstado = if (alturaEncimaMedia()) "Por encima de la media" else "Por debajo de la media"
        val pesoEstado = if (pesoEncimaMedia()) "Por encima de la media" else "Por debajo de la media"
        val descImc = obtenerDescImc()
        return "$nombre con una altura de ${"%.2f".format(altura)}m ($alturaEstado) y un peso ${"%.1f".format(peso)}kg ($pesoEstado) tiene un IMC de ${"%.2f".format(imc)} ($descImc)."
    }

    override fun toString(): String {
        return "Persona(nombre='$nombre', peso=$peso, altura=$altura, imc=${"%.2f".format(imc)})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Persona) return false

        return peso == other.peso && altura == other.altura && nombre == other.nombre
    }

    override fun hashCode(): Int {
        var result = peso.hashCode()
        result = 31 * result + altura.hashCode()
        result = 31 * result + nombre.hashCode()
        return result
    }
}

fun main() {
    val personas = listOf(
        Persona("Julia", 64.7, 1.72),
        Persona("Carlos", 85.0, 1.80),
        Persona("Ana", 54.0, 1.60),
        Persona("Luis", 92.0, 1.78),
        Persona("María", 68.0, 1.65)
    )

    for (persona in personas) {
        println(persona.saludar())
        println(persona.obtenerDesc())
    }
}