package com.edwin.calculator.manager.calculator

import android.util.Log
import com.edwin.calculator.manager.calculator.operations.*

class CalculatorManager {

    val regexPatternForNumbers = "[x÷+-]+".toRegex()
    private val regexPatternForSymbols = "\\d+".toRegex()

    private val operationsMap: List<Pair<String, CalculatorOperation>> = listOf(
        "x" to MultiplyOperation(),
        "÷" to DivideOperation(),
        "+" to AddOperation(),
        "-" to SubtractOperation()
    )

    fun getOperationResult(operation: String): String {
        val numbers =
            regexPatternForNumbers.split(operation).filter { it.isNotEmpty() }.toMutableList()
        val symbols =
            regexPatternForSymbols.split(operation).filter { it.isNotEmpty() }.toMutableList()
        if (symbols.size >= numbers.size) return operation
        symbols.forEach { if (it.length > 1) return operation }
        Log.i("CalculatorManager", "Valid operation")
        runOperations(numbers, symbols)
        Log.i("CalculatorManager", "result ${numbers[0]}")
        return numbers[0]
    }

    private fun runOperations(numbers: MutableList<String>, symbols: MutableList<String>) {
        operationsMap.forEach {
            runOperation(numbers, symbols, it)
        }
    }

    private fun runOperation(
        numbers: MutableList<String>,
        symbols: MutableList<String>,
        entry: Pair<String, CalculatorOperation>
    ) {
        symbols.forEachIndexed { index, value ->
            if (value == entry.first) {
                val operationResult = entry.second.execute(numbers[index], numbers[index + 1])
                numbers[index] = operationResult
                numbers.removeAt(index + 1)
                symbols.removeAt(index)
                return runOperation(numbers, symbols, entry)
            }
        }
    }

}