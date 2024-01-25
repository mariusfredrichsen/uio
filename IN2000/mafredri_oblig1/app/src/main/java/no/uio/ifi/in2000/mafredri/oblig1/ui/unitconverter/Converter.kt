package no.uio.ifi.in2000.mafredri.oblig1.ui.unitconverter

import kotlin.math.roundToInt

enum class ConverterUnits {
    OUNCE,
    CUP,
    GALLON,
    HOGSHEAD
}

fun converter(verdi: Int, enhet: ConverterUnits): Int {
    return (when (enhet) {
        ConverterUnits.OUNCE -> 0.02957
        ConverterUnits.CUP -> 0.23659
        ConverterUnits.GALLON -> 3.78541
        ConverterUnits.HOGSHEAD -> 238.481
    } * verdi).roundToInt()
}