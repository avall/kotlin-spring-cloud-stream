package com.avall.ms.attachments.arch.stereotype

interface Query<Input, Output> {
    fun execute(input: Input): Output
}