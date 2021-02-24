package msk.rest.vio.emu.domain

class GetContentInfoResult {
    var errors = emptyArray<String>()
    var payload = emptyArray<GetContentInfoPayload>()

    class GetContentInfoPayload {
        var documentId: String = ""
        var mimeType: String = ""
        var name: String = ""
        var description: String = ""
        var contentNumber: Int? = null
        var id: Long? = null
    }
}