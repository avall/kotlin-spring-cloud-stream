package com.avall.kotlin.ms.cousine.consumer.application.exceptions

import com.avall.kotlin.ms.cousine.consumer.arch.exception.EntityAlreadyExistsException


class EmailAlreadyUsedException(message: String?) : EntityAlreadyExistsException("Email already used")