package com.avall.ms.attachments.arch.usecase

import com.avall.ms.attachments.arch.annotation.Interactor
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