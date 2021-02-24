package msk.rest.vio.emu.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

class Notification {
    companion object {
        const val DEFAULT_EVENT_TYPE = "notification"
    }
    @JsonProperty("document-id")
    var documentId: String = ""
    var type: Int? = null
    @JsonProperty("event-type")
    var eventType = DEFAULT_EVENT_TYPE
    @JsonProperty("request-id")
    var requestId: String? = null
    @JsonProperty("message-id")
    var messageId = UUID.randomUUID().toString().replace("-", "")
    @JsonProperty("correlation-id")
    var correlationId: String? = null
    @JsonProperty("parent-document-id")
    var parentDocumentId: String? = null
    var process = "urn:process:5:1.0"
    var from = "urn:region:777000:MSK"
    var to: String? = null
    @JsonProperty("peer-block")
    var peerBlock: Any? = null

    @JsonIgnore
    fun isNotification(): Boolean {
        return (eventType == DEFAULT_EVENT_TYPE)
    }

    constructor(documentId: String, type: Int) {
        this.documentId = documentId
        this.type = type
    }
}