package com.avall.ms.attachments.application.exceptions

import com.avall.ms.attachments.arch.exception.EntityAlreadyExistsException


class EmailAlreadyUsedException(message: String?) : EntityAlreadyExistsException("Email already used")