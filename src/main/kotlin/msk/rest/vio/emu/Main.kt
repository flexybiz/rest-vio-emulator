package msk.rest.vio.emu

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import msk.rest.vio.emu.controller.MainController

fun main() {
  val ipAddress = "0.0.0.0"
  val app = Javalin.create().apply {
    exception(Exception::class.java) { e, _ -> e.printStackTrace() }
  }.start(ipAddress, 8080)

  app.routes {
    path("/") {
      path("info") {
        get(MainController::info)
      }
    }
  }
}