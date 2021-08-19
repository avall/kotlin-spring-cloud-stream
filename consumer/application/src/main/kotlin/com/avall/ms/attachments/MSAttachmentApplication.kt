package com.avall.ms.attachments

import com.avall.ms.attachments.arch.annotation.Interactor
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
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
class MSAttachmentApplication {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(
				MSAttachmentApplication::class.java,
				*args
			)
		}
	}
}