package com.avall.ms.attachments.api

import com.avall.ms.attachments.api.dto.response.ProductResponse
import com.avall.ms.attachments.api.dto.response.StoreResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/store")
interface StoreResource {
    @GetMapping
    fun all(): CompletableFuture<List<Any?>?>?

    @GetMapping("/search/{text}")
    fun getAllStoresByNameMatching(@PathVariable text: String): CompletableFuture<List<StoreResponse?>?>

    @GetMapping("/{id}")
    fun getStoreByIdentity(@PathVariable id: Long): CompletableFuture<StoreResponse?>

    @GetMapping("/{id}/product")
    fun getProductsBy(@PathVariable id: Long): CompletableFuture<List<ProductResponse?>?>
}
