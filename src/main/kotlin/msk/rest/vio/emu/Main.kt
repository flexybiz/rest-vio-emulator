package msk.rest.vio.emu

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import msk.rest.vio.emu.controller.MainController
import msk.rest.vio.emu.domain.Setting
import java.io.FileInputStream
import java.util.*

fun loadProperties() {
  val properties = Properties()
  val propertiesFile = System.getProperty("user.dir") + "/rest-vio-emulator.properties"
  val inputStream = FileInputStream(propertiesFile)
  properties.load(inputStream)
  println("Config:")
  properties.forEach { (k, v) -> println("$k = $v") }
  Setting.mqHost = properties.getProperty("mqHost")
  Setting.mqPort = properties.getProperty("mqPort")
  Setting.mqChannel = properties.getProperty("mqChannel")
  Setting.mqManager = properties.getProperty("mqManager")
  Setting.mqIn = properties.getProperty("mqIn")
  Setting.mqOut = properties.getProperty("mqOut")
  Setting.mqMca = properties.getProperty("mqMca")

  Setting.ehdGetDocUrl = properties.getProperty("ehdGetDocUrl")
  Setting.ehdGetDescriptionUrl = properties.getProperty("ehdGetDescriptionUrl")
  Setting.ehdGetContentInfoUrl = properties.getProperty("ehdGetContentInfoUrl")
  Setting.ehdPostDocUrl = properties.getProperty("ehdPostDocUrl")

  Setting.ipAddress = properties.getProperty("ipAddress")
  Setting.appPort = properties.getProperty("appPort")

  Setting.tmpDir = properties.getProperty("tmpDir")

}

fun main() {
  loadProperties()
  val ipAddress = Setting.ipAddress
  val app = Javalin.create().apply {
    exception(Exception::class.java) { e, _ -> e.printStackTrace() }
  }.start(ipAddress, Setting.appPort.toInt())

  app.routes {
    path("/") {
      path("info") {
        get(MainController::info)
      }
      path("send") {
        post(MainController::send)
      }
    }
  }
}