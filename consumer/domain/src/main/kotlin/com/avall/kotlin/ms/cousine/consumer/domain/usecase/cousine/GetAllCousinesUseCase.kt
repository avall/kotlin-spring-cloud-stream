package com.avall.kotlin.ms.cousine.consumer.domain.usecase.cousine

import com.avall.kotlin.ms.cousine.consumer.arch.usecase.UseCase
import com.avall.kotlin.ms.cousine.consumer.arch.annotation.Interactor
import com.avall.kotlin.ms.cousine.consumer.domain.model.Cousine
import com.avall.kotlin.ms.cousine.consumer.domain.port.output.ICousineRepository

@Interactor
open class GetAllCousinesUseCase(private val repository: ICousineRepository) :
    UseCase<GetAllCousinesUseCase.InputValues, GetAllCousinesUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        return OutputValues(repository.all())
    }

    class InputValues() : UseCase.InputValues

    data class OutputValues(
        var cousines: List<Cousine>
    ) : UseCase.OutputValues
}