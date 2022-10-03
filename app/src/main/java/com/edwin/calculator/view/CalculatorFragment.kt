package com.edwin.calculator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.edwin.calculator.R
import com.edwin.calculator.databinding.FragmentCalculatorBinding
import com.edwin.calculator.viewmodel.CalculatorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalculatorFragment : Fragment() {

    lateinit var viewDataBinding: FragmentCalculatorBinding
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FragmentCalculatorBinding.inflate(inflater, container, false)
        viewDataBinding.viewModel = viewModel
        viewDataBinding.lifecycleOwner = this
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerOnClickEvents()
    }

    private fun registerOnClickEvents() {
        viewDataBinding.controlsContainer.children.iterator().forEach {
            (it as? Button)?.apply {
                setOnClickListener {eventView ->
                    eventView?.also {view ->
                        when(view.id) {
                            R.id.btn_enter -> {
                                viewModel.submitOperation()
                            }
                            R.id.btn_delete -> {
                                viewModel.deleteOperationCharacter()
                            }
                            else -> {
                                viewModel.addOperationCharacter(this.text.toString())
                            }
                        }
                    }
                }
            }
        }
    }
}