package com.avall.kotlin.ms.cousine.consumer.api

import com.avall.kotlin.ms.cousine.consumer.api.dto.request.CreateAttachmentWrapperRequest
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.CreateAttachmentWrapperResponse
import com.avall.kotlin.ms.cousine.consumer.api.dto.response.GetAttachmentResponse
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestMapping("/attachments",produces = [MediaType.APPLICATION_JSON_VALUE])
@OpenAPIDefinition(
    info = Info(
        title = "Comms Domain API",
        version = "v1",
        description = "CRM Attachment (send attachments to Salesforce)",
        contact = Contact(
            url = "http://www.jandrinet.com/support",
            name = "Comms Team",
            email = "alex.vall.mainou@gmail.com"
        )
    )
)

interface AttachmentApi {
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = SwaggerConstants.CERATE_ATTACHMENT_DESC)
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "201",
            description = SwaggerConstants.GENERIC_MESSAGE201
        ), ApiResponse(
            responseCode = "400",
            description = SwaggerConstants.GENERIC_MESSAGE400,
            content = [Content()]
        ), ApiResponse(
            responseCode = "404",
            description = SwaggerConstants.GENERIC_MESSAGE404,
            content = [Content()]
        )]
    )
    fun createAttachment(@Valid @RequestBody req: CreateAttachmentWrapperRequest): CreateAttachmentWrapperResponse

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = SwaggerConstants.RETRIEVE_ATTACHMENTS_DESC)
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = SwaggerConstants.GENERIC_MESSAGE201
        ), ApiResponse(
            responseCode = "400",
            description = SwaggerConstants.GENERIC_MESSAGE400,
            content = [Content()]
        ), ApiResponse(
            responseCode = "404",
            description = SwaggerConstants.GENERIC_MESSAGE404,
            content = [Content()]
        )]
    )
    fun getAttachments(@RequestParam(required = true) objectId:String): List<GetAttachmentResponse>

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = SwaggerConstants.RETRIEVE_ATTACHMENT_DESC)
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = SwaggerConstants.GENERIC_MESSAGE201
        ), ApiResponse(
            responseCode = "400",
            description = SwaggerConstants.GENERIC_MESSAGE400,
            content = [Content()]
        ), ApiResponse(
            responseCode = "404",
            description = SwaggerConstants.GENERIC_MESSAGE404,
            content = [Content()]
        )]
    )
    fun getAttachmentById(@PathVariable id: String): GetAttachmentResponse
}