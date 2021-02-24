package msk.rest.vio.emu.domain

enum class DocumentTypeClassifier(val id: Int) {
    MZMK(1), MZVD(2), MSRT(3), MZRK(4), MRPP(5), MZAZ(6), MZOB(7), MRVP(8), MSPR(9), MSRT_E(10),
    SZI_SPU(11), MUBZ(13), MZPEV(14), MZUR(15), ZPNP(16),
    MRUD(51), MRSS(54), MRRU(55), MRPO(56), MVZR(57), MRAR(58), MRSR(59),
    MPLD(60), MSPO(61), DUBL(62), MSVR(63), MSNI(64),
    Request(92), Response(93), FORWARD_REQUEST(94), NOTE(95),
    MZOP(97), MKAD(98), MROP(99), MZEV(100), MZZD(101), ES(102), MEBS(103),
    MIPR(104), VMSE(105), DOUBLE_MZMK(1001), DOUBLE_MZRK(1004), MRDO(106), PAY_COMPENSATION(107),
    MZOS(108), MSOS(109), MZMV1(200), MZMV2(201), MZMV3(202), MZMV4(203), MZZAG(204), MZOPE(205), MZRO1(206), MZRO2(207),
    MZRR1(208), MZRR2(209), MZRR3(210), MZOM1(211), MZOM2(212), MOMV1(213), MOMV2(214), MOMV3(215), MOMV4(216), MOZAG(217),
    MOOPE(218), MORO1(219), MORO2(220), MORR1(221), MORR2(222), MORR3(223), MOOM1(224), MOOM2(225), MZZG1(226), MZZG2(227),
    MZZG3(228), MZZG4(229), MZZG5(230), MOZG1(231), MOZG2(232), MOZG3(233), MOZG4(234), MOZG5(235), MZPM(236), MRPM(237),
    MSUR(238), MZZ12(239), MZZ22(240), MZZ32(241), MZZ42(242), MZZ52(243), MOZ12(244), MOZ22(245), MOZ32(246), MOZ42(247),
    MOZ52(248), MZMSZ(249), MOMSZ(250), IPRA(251), MZOV(254);
}