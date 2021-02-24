package msk.rest.vio.emu.domain

enum class DocumentKind(val id: Int, val rusName: String) {
    APPLICATION(1, "Заявления"),
    REQUEST(2, "Запросы")
}