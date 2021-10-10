package com.avall.kotlin.ms.cousine.producer.arch.stereotype

interface Query<Input, Output> {
    fun execute(input: Input): Output
}