package com.avall.kotlin.ms.cousine.consumer.application.config

import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.zalando.logbook.*
import java.io.IOException

@Component
class IndexableFieldsSink(
      val  formatter:HttpLogFormatter,
      val  writer: HttpLogWriter
):Sink {
    val URI = "uri"
    val PATH = "path"
    val HOST = "host"
    val QUERY = "query"
    val REMOTE = "remote"
    val STATUS = "status"
    val DURATION = "duration"
    val METHOD = "method"
    val REQUEST_HEADER = "request.headers"
    val RESPONSE_HEADER = "response.headers"

    override fun isActive(): Boolean {
        return writer.isActive
    }

    @Throws(IOException::class)
    override fun write(precorrelation: Precorrelation, request: HttpRequest) {
        writer.write(precorrelation, formatter.format(precorrelation, request))
    }

    @Throws(IOException::class)
    override fun write(correlation: Correlation, request: HttpRequest, response: HttpResponse) {
        MDC.put(URI, request.requestUri)
        MDC.put(PATH, request.path)
        MDC.put(HOST, request.host)
        MDC.put(QUERY, request.query)
        MDC.put(REMOTE, request.remote)
        MDC.put(STATUS, response.status.toString())
        MDC.put(DURATION, correlation.duration.toMillis().toString())
        MDC.put(METHOD, request.method)
        request.headers.entries.stream()
            .forEach { (key, value) ->
                MDC.put(
                    String.format("%s.%s", REQUEST_HEADER, key),
                    value.toString()
                )
            }
        response.headers.entries.stream()
            .forEach { (key, value) ->
                MDC.put(
                    String.format("%s.%s", RESPONSE_HEADER, key),
                    value.toString()
                )
            }
        writer.write(correlation, formatter.format(correlation, response))
    }
}