package msk.rest.vio.emu.domain

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

class PostDocResult {
    var errors = emptyArray<String>()
    var payload: PostDocResultPayload? = null

    class PostDocResultPayload {
        var document: Any? = null
        var content: PostDocContent? = null
        class PostDocContent {
            var documentId: String? = null
        }
    }

    class Deserializer : ResponseDeserializable<PostDocResult> {
        override fun deserialize(content: String): PostDocResult = Gson().fromJson(content, PostDocResult::class.java)
    }
}