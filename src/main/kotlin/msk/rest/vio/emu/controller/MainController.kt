package msk.rest.vio.emu.controller

import io.javalin.http.Context
import msk.rest.vio.emu.client.EHDClient
import msk.rest.vio.emu.client.MQClient
import msk.rest.vio.emu.domain.SendFileModel
import msk.rest.vio.emu.domain.Setting
import java.io.File

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
    ctx.json(ehd.post(toSend))
    MQClient()
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
}