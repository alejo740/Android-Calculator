package com.edwin.calculator.manager.calculator.operations

class AddOperation : CalculatorOperation() {

    override fun operation(number1: Float, number2: Float): Float = number1 + number2

}