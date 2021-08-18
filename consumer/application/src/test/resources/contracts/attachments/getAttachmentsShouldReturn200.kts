package attachments

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import org.springframework.cloud.contract.spec.withQueryParameters

contract {
    description = "When Get Attachments by objectId Then OK"
    request {
        method = GET
        url = url("/attachments") withQueryParameters {
            parameter("objectId", "7d2c830c-5e62-11eb-ae93-0242ac130002")
        }
        headers {
            accept = APPLICATION_JSON
        }
    }
    response {
        headers {
            contentType = APPLICATION_JSON
        }
        status = OK

        body = body(listOf(
            mapOf(
                "id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
                "object_id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
                "content_type" to "contentType",
                "url" to "path",
                "description" to "description",
                "is_private" to false
            )
            ,
            mapOf(
                "id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
                "object_id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
                "content_type" to "contentType",
                "url" to "path",
                "description" to "description",
                "is_private" to false
            )

        ))

        bodyMatchers {

            jsonPath("$[0].id", byEquality)
            jsonPath("$[0].object_id", byEquality)
            jsonPath("$[1].id", byRegex("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"))
            jsonPath("$[1].object_id", byRegex("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"))
        }

    }
}
