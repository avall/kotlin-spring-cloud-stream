package attachments

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

contract {
    description = "When Get Attachments by id Then OK"
    request {
        method = GET
        url = url("/attachments/7d2c830c-5e62-11eb-ae93-0242ac130002")
        headers {
            accept = APPLICATION_JSON
        }
    }
    response {
        headers {
            contentType = APPLICATION_JSON
        }
        status = OK

        body = body(
            "id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
            "object_id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
            "object_name" to "objectName",
            "content_type" to "contentType",
            "url" to "path",
            "description" to "description",
            "is_private" to false
            )

        bodyMatchers {
            jsonPath("$.id", byRegex("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"))
            jsonPath("$.object_id", byRegex("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"))
        }

    }
}
