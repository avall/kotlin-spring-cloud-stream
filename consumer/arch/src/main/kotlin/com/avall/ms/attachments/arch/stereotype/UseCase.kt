package com.avall.ms.attachments.arch.stereotype

interface UseCase<Input, Output> {
    fun execute(input: Input) : Output
}