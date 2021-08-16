package com.avall.ms.attachments

import com.avall.ms.attachments.arch.annotation.Interactor
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType

@SpringBootApplication
@ComponentScan(
	basePackages = ["com.avall.ms.attachments.*"],
	includeFilters = [ComponentScan.Filter(
		type = FilterType.ANNOTATION,
		classes = [Interactor::class]
	)]
)
open class MSAttachmentApplication

fun main(args: Array<String>) {
	runApplication<MSAttachmentApplication>(*args)
}
