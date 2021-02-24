package msk.rest.vio.emu.utils

object MimeTypeHelper {
    private const val DEFAULT = "application/octet-stream"
    private val MIME_TYPES = mapOf("pdf" to "application/pdf", "json" to "application/json", "js" to "application/javascript",
            "zip" to "application/zip", "gzip" to "application/gzip", "xml" to "application/xml",
            "gif" to "image/gif", "jpeg" to "image/jpeg", "jpg" to "image/jpeg", "png" to "image/png", "tiff" to "image/tiff",
            "tif" to "image/tiff", "jpe" to "image/jpeg")

    fun getTypeByExt(ext: String, defaultValue: String = DEFAULT): String {
      return MIME_TYPES[ext.toLowerCase()] ?: defaultValue
    }

    fun getExtByType(type: String, defaultValue: String = DEFAULT): String {
      return MIME_TYPES.filterValues { value -> value == type.toLowerCase() }.keys.firstOrNull() ?: defaultValue
    }
}