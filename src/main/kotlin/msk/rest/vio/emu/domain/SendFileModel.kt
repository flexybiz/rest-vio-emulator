package msk.rest.vio.emu.domain

import java.util.*

class SendFileModel(dtype: String, channelType: String, senderType: String, fname: String) {
    var docType: VioDocType = VioDocType.valueOf(dtype)
    var docChannelType: DocumentChannelType = DocumentChannelType.valueOf(channelType)
    var docSenderType: DocumentSenderType = DocumentSenderType.valueOf(senderType)
    var note = ""
    var fileName = fname
//    var requestId = ???

    fun toContentRequest() : ContentRequest {
        val req = ContentRequest(fileName, this.docType.id)
        req.documentReceiverType = 1
        req.documentReceiver = "receiver"
        req.documentNumber = "1"
        req.documentDate = Date()
        req.documentDescription = this.docType.toString()
        req.documentTo = "urn:region:777000:MSK"
        req.contentDescription = ""
        req.documentChannelType = this.docChannelType.id
        req.documentSenderType = this.docSenderType.id
        return req
    }

}