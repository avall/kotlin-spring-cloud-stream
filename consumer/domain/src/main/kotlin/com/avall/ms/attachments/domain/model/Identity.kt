package com.avall.ms.attachments.domain.model

data class Identity(
    val number: Long = Long.MIN_VALUE
) {
    companion object {
        fun nothing(): Identity {
            return Identity(Long.MIN_VALUE)
        }
    }
}