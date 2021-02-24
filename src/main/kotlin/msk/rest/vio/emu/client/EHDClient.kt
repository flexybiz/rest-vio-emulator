package msk.rest.vio.emu.client

import msk.rest.vio.emu.domain.ContentRequest
import msk.rest.vio.emu.domain.PostDocResult
import msk.rest.vio.emu.domain.Setting
import msk.rest.vio.emu.utils.ExternalServiceException
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.core.isSuccessful
import com.github.kittinunf.result.Result

class EHDClient() {
//    private val log = KotlinLogging.logger {}

    companion object {
        const val SERVICE_NAME = "EHD"
    }

    fun post(request: ContentRequest): Result<String, ExternalServiceException> {
      val(_, response, result) = Fuel.upload(Setting.ehdPostDocUrl)
          .header(Headers.CONTENT_TYPE, "multipart/form-data; boundary=${ContentRequest.BOUNDARY}")
          .body(request.formRequest())
          .responseObject(PostDocResult.Deserializer())
      val (document, error) = result
      if(response.isSuccessful) {
        return Result.success(document?.payload?.content?.documentId.toString())
      }
      return Result.error(ExternalServiceException(error.toString(), SERVICE_NAME))
    }
//
//    fun get(documentId: String, mimeType: String): String {
//        val resp = api.get("${setting.ehdGetDocUrl}?documentId=$documentId&mimeType=$mimeType", processor = {
//            val con = (it as HttpURLRequest).connection
//            con.readTimeout = 10000
//            con.useCaches = false
//            //it.addHeader("Content-Type", "application/json;charset=utf-8");
//            it.addHeader("Accept", "application/octet-stream");
//        })
//        if (resp.ok()) {
//            return resp.text()!!
//        } else {
//            log.error { "Error in request sending: ${resp.text()}" }
//            throw RuntimeException("Ошибка при получении документа '$documentId'")
//        }
//    }
//
//    fun get(documentId: String): Pair<String, String> {
//        val resp = api.get("${setting.ehdGetContentInfoUrl}?documentId=$documentId&page=1&pageSize=10", processor = {
//            val con = (it as HttpURLRequest).connection
//            con.readTimeout = 10000
//            con.useCaches = false
//            it.addHeader("Content-Type", "application/json;charset=utf-8");
//        })
//        if (resp.ok()) {
//            val mapper = jacksonObjectMapper()
//            val res: GetContentInfoResult = mapper.readValue(resp.text(), GetContentInfoResult::class.java)
//            val mimeType = res.payload[0]!!.mimeType
//            return Pair(MimeTypeHelper.getExtByType(mimeType), get(documentId, mimeType))
//        } else {
//            log.error { "Error in request sending: ${resp.text()}" }
//            throw RuntimeException("Ошибка при получении документа '$documentId'")
//        }
//    }
}