package com.avall.kotlin.ms.cousine.producer.arch.stereotype

interface UseCase<Input, Output> {
    fun execute(input: Input) : Output
}