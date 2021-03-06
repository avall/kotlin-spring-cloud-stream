package com.avall.kotlin.ms.cousine.consumer.api

import com.avall.kotlin.ms.cousine.consumer.api.dto.response.ProductResponse
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.StoreResponse
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
    fun getStoreByString(@PathVariable id: String): CompletableFuture<StoreResponse?>

    @GetMapping("/{id}/product")
    fun getProductsBy(@PathVariable id: String): CompletableFuture<List<ProductResponse?>?>
}
