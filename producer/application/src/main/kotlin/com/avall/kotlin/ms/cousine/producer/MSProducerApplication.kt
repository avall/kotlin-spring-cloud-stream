package com.avall.kotlin.ms.cousine.producer

import com.avall.kotlin.ms.cousine.producer.arch.annotation.Interactor
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType

@SpringBootApplication
@ComponentScan(
	basePackages = ["com.avall.kotlin.ms.cousine.producer.*"],
	includeFilters = [ComponentScan.Filter(
		type = FilterType.ANNOTATION,
		classes = [Interactor::class]
	)]
)
class MSProducerApplication {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(
				MSProducerApplication::class.java,
				*args
			)
		}
	}
}