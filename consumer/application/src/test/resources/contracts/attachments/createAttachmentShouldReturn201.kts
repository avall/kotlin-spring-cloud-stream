package attachments

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

contract {
    description = "When Post Attachments Then CREATED"
    request {
        method = POST
        url = url("/attachments")
        headers {
            contentType = APPLICATION_JSON
        }
        body = body(mapOf(
            "documents" to listOf(
                mapOf(
                    "object_id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
                    "content_type" to "contentType",
                    "url" to "path",
                    "description" to "description",
                    "is_private" to false
                ),
                mapOf(
                    "object_id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
                    "content_type" to "contentType",
                    "url" to "path",
                    "description" to "description",
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
                    "object_id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
                    "content_type" to "contentType",
                    "url" to "path",
                    "description" to "description",
                    "is_private" to false
                ),
                mapOf(
                    "id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
                    "object_id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
                    "content_type" to "contentType",
                    "url" to "path",
                    "description" to "description",
                    "is_private" to false
                )

            )
        ))

        bodyMatchers {
            jsonPath("$.documents[0].id", byRegex("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"))
            jsonPath("$.documents[0].object_id", byRegex("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"))
        }
    }
}
