package com.avall.ms.attachments.application.service;


import com.avall.ms.attachments.arch.extensions.loggerFor
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import java.io.Serializable

@Service
class PublisherService(private val streamBridge: StreamBridge) {
  private val log = loggerFor(javaClass)

  /**
   * send event.
   * @param event Serializable  Message / Event
   * @param binding String?     Topic
   */
  fun send(event: Serializable, binding: String?) {
    log.debug("sending {}", event)
    val msg = MessageBuilder.withPayload(event).build()
    streamBridge.send(binding, msg)
  }
}