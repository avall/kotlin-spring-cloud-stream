package com.avall.kotlin.ms.cousine.consumer.application.service;


import com.avall.kotlin.ms.cousine.consumer.CommandPayload
import com.avall.kotlin.ms.cousine.consumer.arch.extensions.loggerFor
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.Message
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
  fun send(event: Message<CommandPayload>, binding: String?) {
    log.debug("sending {}", event)
    val msg = MessageBuilder.withPayload(event).build()
    streamBridge.send(binding, msg)
  }
}