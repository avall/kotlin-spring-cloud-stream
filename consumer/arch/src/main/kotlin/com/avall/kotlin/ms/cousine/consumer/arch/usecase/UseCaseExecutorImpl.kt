package com.avall.kotlin.ms.cousine.consumer.arch.usecase

import com.avall.kotlin.ms.cousine.consumer.arch.annotation.Interactor
import java.util.concurrent.CompletableFuture
import java.util.function.Function

@Interactor
class UseCaseExecutorImpl : UseCaseExecutor {

    override fun <RX, I : UseCase.InputValues, O : UseCase.OutputValues> execute(
        useCase: UseCase<I, O>,
        input: I,
        outputMapper: Function<O, RX>
    ): CompletableFuture<RX> {
        return CompletableFuture
            .supplyAsync { input }
            .thenApplyAsync<O>(useCase::execute)
            .thenApplyAsync<RX>(outputMapper)
    }
}