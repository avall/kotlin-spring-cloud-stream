package com.avall.kotlin.ms.cousine.consumer.arch.stereotype

interface UseCase<Input, Output> {
    fun execute(input: Input) : Output
}