package msk.rest.vio.emu.controller

import io.javalin.http.Context

object MainController {

  fun info(ctx: Context) {
    ctx.json("rest-vio-emulator v1.0")
  }

  fun send(ctx: Context) {
    println(ctx.body())
  }
}