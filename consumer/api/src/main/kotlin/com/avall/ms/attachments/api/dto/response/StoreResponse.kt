package com.avall.ms.attachments.api.dto.response

data class StoreResponse (
    var id: Long,
    var name: String,
    var address: String,
    var cousine: CousineResponse,
)