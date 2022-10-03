package com.edwin.calculator.manager.calculator.operations

abstract class CalculatorOperation {

    fun execute(number1: String, number2: String): String {
        try {
            val number1Float = number1.toFloat()
            val number2Float = number2.toFloat()
            val result = operation(number1Float, number2Float)
            if (number1Float % number2Float == 0F) {
                return result.toInt().toString()
            }
            return result.toString()
        } catch (e: NumberFormatException) {
            return e.toString()
        }
    }

    protected abstract fun operation(number1: Float, number2: Float): Float
}