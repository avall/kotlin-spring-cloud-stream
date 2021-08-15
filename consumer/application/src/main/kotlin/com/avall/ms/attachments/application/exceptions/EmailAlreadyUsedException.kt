package com.avall.ms.attachments.application.exceptions

import com.avall.ms.attachments.arch.exception.DomainException

class EmailAlreadyUsedException(message: String?) : DomainException("Email already used",406 , message!!, listOf())