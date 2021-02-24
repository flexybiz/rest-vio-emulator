package msk.rest.vio.emu.domain

import msk.rest.vio.emu.utils.FormatUtils
import msk.rest.vio.emu.utils.MimeTypeHelper
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*

class ContentRequest(filepath: String, val documentType: Int) {
    companion object {
        val BOUNDARY = "--NextPart_" + UUID.randomUUID().toString()
        private const val CRLF = "\n"
        private const val TWO_HYPHENS = "--"
        private val START = TWO_HYPHENS + BOUNDARY + CRLF
        private val END = CRLF + TWO_HYPHENS + BOUNDARY + TWO_HYPHENS
        private val SEP = CRLF + START
        private val NN = CRLF + CRLF
    }
    private val contentBody: ByteArray = File(filepath).readBytes()
    private val fileName: String = filepath.substringAfterLast("\\")

    internal var documentKind: Int = 2
    internal var documentChannelType: Int = 2
    internal var documentSenderType: Int = 4
    internal var documentSender = "101-000"
    internal var documentReceiverType: Int? = null
    internal var documentReceiver = ""
    internal var documentNumber: String? = null
    internal var documentDate: Date? = null
    internal var documentDescription = ""
    internal var documentStatus: Int = 1
    internal var documentFrom = "urn:region:777000:MSK"
    internal var documentTo = ""
    //internal var mimeType = ""
    internal var contentDescription = ""
    internal var couCode: Int? = null


    fun formRequest(): ByteArray {
        ByteArrayOutputStream().use {
            val writer = it.writer()
            writer.write(START)

            writer.write("Content-Disposition: form-data; name=\"contentBody\"; filename=\"$fileName\"$NN")
            writer.flush()
            it.write(contentBody)

            writer.write(SEP)
            writer.write("Content-Disposition: form-data; name=\"documentType\"$NN")
            writer.write(documentType.toString())

            writer.write(SEP)
            writer.write("Content-Disposition: form-data; name=\"documentKind\"$NN")
            writer.write(documentKind.toString())

            writer.write(SEP)
            writer.write("Content-Disposition: form-data; name=\"documentChannelType\"$NN")
            writer.write(documentChannelType.toString())

            writer.write(SEP)
            writer.write("Content-Disposition: form-data; name=\"documentSenderType\"$NN")
            writer.write(documentSenderType.toString())

            writer.write(SEP)
            writer.write("Content-Disposition: form-data; name=\"documentSender\"$NN")
            writer.write(documentSender)

            if (documentReceiverType != null) {
                writer.write(SEP)
                writer.write("Content-Disposition: form-data; name=\"documentReceiverType\"$NN")
                writer.write(documentReceiverType.toString())
            }

            writer.write(SEP)
            writer.write("Content-Disposition: form-data; name=\"documentReceiver\"$NN")
            writer.write(documentReceiver)

            if (documentNumber != null) {
                writer.write(SEP)
                writer.write("Content-Disposition: form-data; name=\"documentNumber\"$NN")
                writer.write(documentNumber.toString())
            }

            if (documentDate != null) {
                writer.write(SEP)
                writer.write("Content-Disposition: form-data; name=\"documentDate\"$NN")
                writer.write(FormatUtils.formatVio(documentDate).toString())
            }

            writer.write(SEP)
            writer.write("Content-Disposition: form-data; name=\"documentDescription\"$NN")
            writer.write(documentDescription)

            writer.write(SEP)
            writer.write("Content-Disposition: form-data; name=\"documentStatus\"$NN")
            writer.write(documentStatus.toString())

            writer.write(SEP)
            writer.write("Content-Disposition: form-data; name=\"documentFrom\"$NN")
            writer.write(documentFrom)

            writer.write(SEP)
            writer.write("Content-Disposition: form-data; name=\"documentTo\"$NN")
            writer.write(documentTo)

            writer.write(SEP)
            writer.write("Content-Disposition: form-data; name=\"mimeType\"$NN")
            writer.write(getMimeType())

            writer.write(SEP)
            writer.write("Content-Disposition: form-data; name=\"contentDescription\"$NN")
            writer.write(contentDescription)
            writer.write(END)

            writer.flush()
            return it.toByteArray()
        }
    }

    private fun getMimeType(): String {
        val ext = fileName.substringAfterLast(".", "")
        return MimeTypeHelper.getTypeByExt(ext)
    }

}