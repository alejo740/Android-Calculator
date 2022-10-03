package com.edwin.calculator.manager.calculator.operations

import java.lang.NumberFormatException

class DivideOperation : CalculatorOperation() {
    override fun operation(number1: Float, number2: Float): Float = if (number2 == 0F) {
        throw NumberFormatException("Division By Zero Not Allowed")
    } else {
        number1 / number2
    }
}