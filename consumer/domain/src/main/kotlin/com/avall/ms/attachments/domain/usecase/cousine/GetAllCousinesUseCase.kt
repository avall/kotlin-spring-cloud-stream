package com.avall.ms.attachments.domain.usecase.cousine

import com.avall.ms.attachments.arch.usecase.UseCase
import com.avall.ms.attachments.domain.annotation.Interactor
import com.avall.ms.attachments.domain.model.Cousine
import com.avall.ms.attachments.domain.port.output.ICousineRepository

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