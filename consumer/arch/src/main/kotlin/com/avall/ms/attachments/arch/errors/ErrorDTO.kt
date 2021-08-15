package com.avall.ms.attachments.arch.errors;

data class ErrorDTO (

  val error:String,
  val errorDescription:String,
  val errorMessage:String?=null,
  val errorReasons:List<ErrorReasonDTO>?=null
)
