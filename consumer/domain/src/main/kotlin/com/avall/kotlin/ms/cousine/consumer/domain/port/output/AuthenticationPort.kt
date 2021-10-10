package com.avall.kotlin.ms.cousine.consumer.domain.port.output

import com.avall.kotlin.ms.cousine.consumer.domain.model.AuthenticationUserPassword

interface AuthenticationPort{

    fun authenticate(authentication: AuthenticationUserPassword): Object?;
    fun generateToken(authentication: Object?): String

}