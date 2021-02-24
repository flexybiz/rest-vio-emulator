package msk.rest.vio.emu.utils
import java.text.SimpleDateFormat
import java.util.*

object FormatUtils {
    private const val DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    private val DATE_FORMAT: ThreadLocal<SimpleDateFormat> = ThreadLocal.withInitial {
        return@withInitial SimpleDateFormat(DATE_FORMAT_PATTERN)
    }
    private val fileNameRegex = Regex("[ .,-\\/\\u002B]")
    fun mimeTypeToFileName(mimeType: String): String = mimeType.toLowerCase().replace(fileNameRegex, "")
    fun formatVio(dt: Date?) : String? = if (dt == null) null else DATE_FORMAT.get().format(dt)
}