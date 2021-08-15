package attachments

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

contract {
    description = "When Post Attachments Then CREATED"
    request {
        method = POST
        url = url("/v1/attachments")
        headers {
            contentType = APPLICATION_JSON
        }
        body = body(mapOf(
            "documents" to listOf(
                mapOf(
                    "parent_id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
                    "parent_object_name" to "parentObjectName",
                    "content_type" to "contentType",
                    "path" to "path",
                    "file_name" to "fileName",
                    "description" to "description",
                    "type" to "type",
                    "doc_type" to "docType",
                    "country" to "de",
                    "language" to "de",
                    "is_document" to false,
                    "is_private" to false
                ),
                mapOf(
                    "parent_id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
                    "parent_object_name" to "parentObjectName",
                    "content_type" to "contentType",
                    "path" to "path",
                    "file_name" to "fileName",
                    "description" to "description",
                    "type" to "type",
                    "doc_type" to "docType",
                    "country" to "de",
                    "language" to "de",
                    "is_document" to false,
                    "is_private" to false
                )

            )
        ))

    }
    response {
        status = CREATED

        body = body(mapOf(
            "documents" to listOf(
                mapOf(
                    "id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
                    "parent_id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
                    "parent_object_name" to "parentObjectName",
                    "content_type" to "contentType",
                    "path" to "path",
                    "file_name" to "fileName",
                    "description" to "description",
                    "type" to "type",
                    "doc_type" to "docType",
                    "country" to "de",
                    "language" to "de",
                    "is_document" to false,
                    "is_private" to false
                ),
                mapOf(
                    "id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
                    "parent_id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
                    "parent_object_name" to "parentObjectName",
                    "content_type" to "contentType",
                    "path" to "path",
                    "file_name" to "fileName",
                    "description" to "description",
                    "type" to "type",
                    "doc_type" to "docType",
                    "country" to "de",
                    "language" to "de",
                    "is_document" to false,
                    "is_private" to false
                )

            )
        ))

        bodyMatchers {
            jsonPath("$.documents[0].id", byRegex("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"))
            jsonPath("$.documents[0].parent_id", byRegex("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"))
        }
    }
}
