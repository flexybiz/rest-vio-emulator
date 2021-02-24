package msk.rest.vio.emu.utils

class ExternalServiceException(message: String?, val serviceName: String) : RuntimeException(message) {
    fun getFullMessage() = "Error on service $serviceName: $message"
}