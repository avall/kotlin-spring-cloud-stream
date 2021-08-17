package com.avall.ms.attachments.domain.port.output

import com.avall.ms.attachments.domain.model.AuthenticationUserPassword

interface AuthenticationPort{

    fun authenticate(authentication: AuthenticationUserPassword): Object?;
    fun generateToken(authentication: Object?): String

}