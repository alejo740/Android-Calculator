package com.edwin.calculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edwin.calculator.manager.calculator.CalculatorManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(private val calculatorManager: CalculatorManager) :
    ViewModel() {

    private val _output: MutableLiveData<String> = MutableLiveData("")
    val output: LiveData<String>
        get() = _output

    fun addOperationCharacter(value: String) {
        _output.value?.apply {
            if (this.isNotEmpty() || !value.contains(calculatorManager.regexPatternForNumbers)) {
                _output.value = "${_output.value}${value.trim()}"
            }
        }
    }

    fun deleteOperationCharacter() {
        _output.value?.apply {
            if (this.isNotEmpty()) {
                _output.value = dropLast(1)
            }
        }
    }

    fun submitOperation() {
        _output.value?.apply {
            if (this.isNotEmpty()) {
                _output.value = calculatorManager.getOperationResult(this.trim())
            }
        }
    }
}