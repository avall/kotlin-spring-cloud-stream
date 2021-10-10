package com.avall.kotlin.ms.cousine.consumer.arch.stereotype

interface Query<Input, Output> {
    fun execute(input: Input): Output
}