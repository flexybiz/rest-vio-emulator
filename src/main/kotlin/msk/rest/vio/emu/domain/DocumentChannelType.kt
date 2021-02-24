package msk.rest.vio.emu.domain

enum class DocumentChannelType(val id: Int, val rusName: String, val engName: String) {
    MANUALLY(1, "Документы, введенные вручную", "MANUALLY"),
    ELECTRONIC(2, "Электронный документооброт со страхователем", "ELECTRONIC"),
    EMAIL(3, "Документы, полученные по почте", "EMAIL"),
    EDONPF(4, "Электронный документооброт с НПФ", "EDO-NPF"),
    LKZL(7, "Личный кабинет ЗЛ", "LKZL"),
    KS(8, "Клиентская служба", "KS"),
    LKP(9, "Кабинет страхователя", "LKP"),
    PFRIN(10, "Внутренний документооборот", "PFR-IN"),
    SMEV(11, "СМЭВ", "SMEV");

    companion object {
        private val CHANNEL_TYPES = DocumentChannelType.values().associateBy ( {it.id}, {it} )
        fun fromId(id: Int?): DocumentChannelType? = CHANNEL_TYPES[id]
    }
}