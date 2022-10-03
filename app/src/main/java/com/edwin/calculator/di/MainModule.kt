package com.edwin.calculator.di

import com.edwin.calculator.manager.calculator.CalculatorManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    fun provideCalculatorManager() = CalculatorManager()

}