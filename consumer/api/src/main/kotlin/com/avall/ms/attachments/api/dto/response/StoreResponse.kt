package com.avall.ms.attachments.api.dto.response

data class StoreResponse (
    var id: String,
    var name: String,
    var address: String,
    var cousine: CousineResponse,
)