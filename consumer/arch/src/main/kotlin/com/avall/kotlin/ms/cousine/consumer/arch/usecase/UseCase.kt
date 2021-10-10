package com.avall.kotlin.ms.cousine.consumer.arch.usecase

interface UseCase<I : UseCase.InputValues, O : UseCase.OutputValues> {
    fun execute(input: I): O
    interface InputValues
    interface OutputValues
}