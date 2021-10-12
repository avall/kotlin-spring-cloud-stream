package com.avall.kotlin.ms.cousine.consumer.arch.stereotype

interface UseCaseInput<Input> {
    fun execute(input: Input)
}