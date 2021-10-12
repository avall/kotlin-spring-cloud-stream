package com.avall.kotlin.ms.cousine.producer.arch.stereotype

interface UseCaseInput<Input> {
    fun execute(input: Input)
}