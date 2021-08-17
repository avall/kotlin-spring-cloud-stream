package com.avall.ms.attachments.api

import com.avall.ms.attachments.api.dto.response.ProductResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture


@RestController
@RequestMapping("/product")
interface ProductResource {
    @GetMapping
    fun allProducts(): CompletableFuture<List<Any?>?>

    @GetMapping("/{id}")
    fun getByString(@PathVariable id: String): CompletableFuture<ProductResponse?>?

    @GetMapping("/search/{text}")
    fun getByMatchingName(@PathVariable text: String): CompletableFuture<List<ProductResponse?>?>?
}