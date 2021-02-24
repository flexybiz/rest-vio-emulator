package msk.rest.vio.emu.domain

class Setting {
    companion object {
        //        private val urlSlash: String = "://"
        lateinit var mqHost: String
        lateinit var mqPort: String
        var mqChannel = ""
        var mqManager = ""
        var mqIn = ""
        var mqOut = ""
        var mqMca = ""
        var mqPass = ""

        var ehdGetDocUrl = ""
        var ehdGetDescriptionUrl = ""
        var ehdGetContentInfoUrl = ""
        var ehdPostDocUrl = ""

        var sendFilePath = ""
        var receiveDirPath = ""

        var lastNote = ""

        var ipAddress = "0.0.0.0"
        var appPort = "8080"

        var tmpDir = ""
    }

}

//    private val ehdUrls: List<SimpleStringProperty> = listOf<SimpleStringProperty>(ehdGetDocUrlProperty,
//            ehdGetDescriptionUrlProperty, ehdPostDocUrlProperty)
//
//    val mqStrProperty = SimpleStringProperty(this, "mqStr", "")
//            .stringBinding(mqHostProperty, mqPortProperty, mqManagerProperty) {"$mqHost:$mqPort $mqManager"}
//    val ehdServersProperty = SimpleStringProperty(this, "ehdServers", "")
//            .stringBinding(ehdPostDocUrlProperty, ehdGetDescriptionUrlProperty, ehdGetDocUrlProperty) {getEhdServers()}

//    private fun getEhdServers(): String = ehdUrls.map { e ->
//        var startPos = e.get().indexOf(urlSlash)
//        if (startPos < 0) return@map ""
//        startPos += urlSlash.length
//        val endPos = e.get().indexOf("/", startPos)
//        return@map e.get().substring(startPos, endPos)
//    }.toSet().joinToString()
