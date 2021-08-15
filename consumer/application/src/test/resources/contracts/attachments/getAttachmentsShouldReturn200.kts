package attachments

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import org.springframework.cloud.contract.spec.withQueryParameters

contract {
    description = "When Get Attachments by parentId Then OK"
    request {
        method = GET
        url = url("/v1/attachments") withQueryParameters {
            parameter("parentId", "7d2c830c-5e62-11eb-ae93-0242ac130002")
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
                "parent_id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
                "parent_object_name" to "parentObjectName",
                "content_type" to "contentType",
                "type" to "type",
                "doc_type" to "docType",
                "path" to "path",
                "file_name" to "fileName",
                "description" to "description",
                "country" to "de",
                "language" to "de",
                "is_document" to false,
                "is_private" to false
            )
            ,
            mapOf(
                "id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
                "parent_id" to "7d2c830c-5e62-11eb-ae93-0242ac130002",
                "parent_object_name" to "parentObjectName",
                "content_type" to "contentType",
                "type" to "type",
                "doc_type" to "docType",
                "path" to "path",
                "file_name" to "fileName",
                "description" to "description",
                "country" to "de",
                "language" to "de",
                "is_document" to false,
                "is_private" to false
            )

        ))

        bodyMatchers {

            jsonPath("$[0].id", byEquality)
            jsonPath("$[0].parent_id", byEquality)
            jsonPath("$[1].id", byRegex("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"))
            jsonPath("$[1].parent_id", byRegex("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"))
        }

    }
}
