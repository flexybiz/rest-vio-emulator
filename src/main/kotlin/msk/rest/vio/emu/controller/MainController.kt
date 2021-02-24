package msk.rest.vio.emu.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.kittinunf.result.Result
import io.javalin.http.Context
import msk.rest.vio.emu.client.EHDClient
import msk.rest.vio.emu.client.MQClient
import msk.rest.vio.emu.domain.Notification
import msk.rest.vio.emu.domain.SendFileModel
import msk.rest.vio.emu.domain.Setting
import java.io.File
import java.util.*

object MainController {

  private data class Claim(val claim_type: String, val claim_body: String)

  fun info(ctx: Context) {
    ctx.json("rest-vio-emulator v1.0")
  }

  fun send(ctx: Context) {
    val claim = Claim(ctx.formParam("claim_type").toString(), ctx.formParam("claim_body").toString())
    val filename = saveXmlFile(claim.claim_type, claim.claim_body)
    val ehd = EHDClient()
    val toSend = SendFileModel(claim.claim_type, "MANUALLY", "PERSON", filename).toContentRequest()
    val res = ehd.post(toSend)
    val documentId = res.get()
    sendMQ(SendFileModel(claim.claim_type, "MANUALLY", "PERSON", filename), documentId)
    ctx.json(res)
  }

  private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

  private fun saveXmlFile(ftype: String, fbody: String): String {
    val randomString = (1..12)
      .map { kotlin.random.Random.nextInt(0, charPool.size) }
      .map(charPool::get)
      .joinToString("")
    val fname = Setting.tmpDir + "/" + ftype + "_" + randomString + ".xml"
    File(fname).writeText(fbody)
    return fname
  }

  private fun sendMQ(model: SendFileModel, documentId: String) {
    var sendText = model.note.trim()
    val mapper = jacksonObjectMapper()
    if (sendText.isEmpty()) {
      val note = Notification(documentId, model.docType.id)
      note.from = "urn:region:019000:KS"
      note.to = "urn:region:777000:MSK"
//      note.requestId = if (model.requestId.isNullOrBlank())
//        UUID.randomUUID().toString().replace("-", "") else model.requestId.trim()
      note.requestId = UUID.randomUUID().toString().replace("-", "")
      sendText = mapper.writeValueAsString(note)
    } else {
      val note = mapper.readValue(sendText, Notification::class.java)
      note.documentId = documentId
      note.type = model.docType.id
      sendText = mapper.writeValueAsString(note)
    }
    val mqClient = MQClient()
    mqClient.sendText(sendText)
  }
}