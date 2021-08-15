package com.avall.ms.attachments.arch.usecase

interface UseCase<I : UseCase.InputValues, O : UseCase.OutputValues> {
    fun execute(input: I): O
    interface InputValues
    interface OutputValues
}