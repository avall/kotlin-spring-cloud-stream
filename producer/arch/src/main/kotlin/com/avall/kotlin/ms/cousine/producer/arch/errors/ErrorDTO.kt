package com.avall.kotlin.ms.cousine.producer.arch.errors;

data class ErrorDTO (

  val error:String,
  val errorDescription:String,
  val errorMessage:String?=null,
  val errorReasons:List<ErrorReasonDTO>?=null
)
