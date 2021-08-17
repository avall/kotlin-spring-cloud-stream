package com.avall.ms.attachments.api

import com.avall.ms.attachments.api.dto.response.CousineResponse
import com.avall.ms.attachments.api.dto.response.StoreResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture


@RestController
@RequestMapping("/cousine")
interface CousineResource {
    @GetMapping("/{id}/store")
    fun getStoresByCousineId(@PathVariable id: String): CompletableFuture<List<StoreResponse?>?>

    @GetMapping
    fun allCousines(): CompletableFuture<List<Any?>?>

    @GetMapping("/search/{text}")
    fun getAllCousinesByNameMatching(@PathVariable text: String): CompletableFuture<List<CousineResponse?>?>
}