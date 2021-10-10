package com.avall.kotlin.ms.cousine.consumer.arch.annotation

import java.lang.annotation.Inherited

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Inherited
annotation class Interactor {
}