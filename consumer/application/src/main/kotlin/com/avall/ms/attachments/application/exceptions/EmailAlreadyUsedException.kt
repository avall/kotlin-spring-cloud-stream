package com.ferraobox.qamyapp.application.core.domain

import com.avall.ms.attachments.arch.exception.DomainException

class EmailAlreadyUsedException(message: String?) : DomainException("",406 , message!!, listOf())