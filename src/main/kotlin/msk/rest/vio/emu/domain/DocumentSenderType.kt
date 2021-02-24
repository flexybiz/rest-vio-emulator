package msk.rest.vio.emu.domain

enum class DocumentSenderType(val id: Int, val rusName: String, val shortName: String, val engName: String) {
    PERSON(1, "Застрахованные лица", "ЗЛ", "PERSON"),
    PAYER(2, "Плательщики страховых взносов", "ПСВ", "PAYER"),
    NPF(3, "Негосударственные пенсионные фонды", "НПФ", "NPF"),
    PFR(4, "Пенсионный фонд Российской Федерации", "ПФР", "PFR"),
    MFC(5, "Многофункциональный центр организации предоставления государственных и муниципальных услуг", "МФЦ", "MFC"),
    UPFR(6, "Управление Пенсионного фонда Российской Федерации", "УПФР", "UPFR"),
    OPFR(7, "Отделение Пенсионного фонда Российской Федерации", "ОПФР", "OPFR"),
    DECLARANT(8, "Заявитель (Анкета АДВ-1)", "ЗЛАДВ-1", "DECLARANT"),
    SOBES(9, "Орган, осуществляющий пенсионное обеспечение", "СОБЕС", "SOBES"),
    ZAGS(10, "Орган ЗАГС", "ЗАГС", "ZAGS"),
    FSSP(11, "Федеральная служба судебных приставов", "ФССП", "FSSP"),
    FNS(13, "Федеральная налоговая служба", "ФНС", "FNS"),
    FSS(14, "Фонд социального страхования", "ФСС", "FSS"),
    UNKNOWN(100, "Неопределенный контрагент", "Н/О", "UNKNOWN");

    companion object {
        private val SENDER_TYPES = DocumentSenderType.values().associateBy({ it.id }, { it })
        fun fromId(id: Int?): DocumentSenderType? = SENDER_TYPES[id]
    }
}