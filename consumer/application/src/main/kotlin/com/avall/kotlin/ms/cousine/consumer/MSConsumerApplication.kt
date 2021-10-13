package com.avall.kotlin.ms.cousine.consumer

import com.avall.kotlin.ms.cousine.consumer.arch.annotation.Interactor
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType

@SpringBootApplication
@ComponentScan(
	basePackages = ["com.avall.kotlin.ms.cousine.consumer.*"],
	includeFilters = [ComponentScan.Filter(
		type = FilterType.ANNOTATION,
		classes = [Interactor::class]
	)]
)
class MSConsumerApplication {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(
				MSConsumerApplication::class.java,
				*args
			)
		}
	}
}