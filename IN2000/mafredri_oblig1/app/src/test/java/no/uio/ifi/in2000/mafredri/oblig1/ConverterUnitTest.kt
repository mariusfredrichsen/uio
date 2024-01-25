package no.uio.ifi.in2000.mafredri.oblig_1

import no.uio.ifi.in2000.mafredri.oblig1.ui.unitconverter.ConverterUnits
import no.uio.ifi.in2000.mafredri.oblig1.ui.unitconverter.converter
import org.junit.Assert.*
import org.junit.Test

class ConverterUnitTest {

    /**
     * Konverteringstabell:
     * 1 fluid ounce = 0,02957 liter
     * 1 cup         = 0,23659 liter
     * 1 gallon      = 3,78541 liter
     * 1 hogshead    = 238,481 liter
     */

    @Test
    fun converter_OunceToLitersFirstCheck_isCorrect() {

        // Arrange and act
        val result = converter(115, ConverterUnits.OUNCE)

        // Assert
        assertEquals(3, result)

    }

    @Test
    fun converter_OunceToLitersSecondCheck_isCorrect() {

        // Arrange and act
        val result = converter(15, ConverterUnits.OUNCE)

        // Assert
        assertEquals(0, result)

    }

    @Test
    fun converter_CupToLitersFirstCheck_isCorrect() {

        // Arrange and act
        val result = converter(120, ConverterUnits.CUP)

        // Assert
        assertEquals(28, result)

    }

    @Test
    fun converter_CupToLitersSecondCheck_isCorrect() {

        // Arrange and act
        val result = converter(20, ConverterUnits.CUP)

        // Assert
        assertEquals(5, result)

    }

    @Test
    fun converter_GallonToLitersFirstCheck_isCorrect() {

        // Arrange and act
        val result = converter(15, ConverterUnits.GALLON)

        // Assert
        assertEquals(57, result)

    }

    @Test
    fun converter_GallonToLitersSecondCheck_isCorrect() {

        // Arrange and act
        val result = converter(3, ConverterUnits.GALLON)

        // Assert
        assertEquals(11, result)

    }

    @Test
    fun converter_HogsheadToLitersFirstCheck_isCorrect() {

        // Arrange and act
        val result = converter(2, ConverterUnits.HOGSHEAD)

        // Assert
        assertEquals(477, result)

    }

    @Test
    fun converter_HogsheadToLitersSecondCheck_isCorrect() {

        // Arrange and act
        val result = converter(238, ConverterUnits.HOGSHEAD)

        // Assert
        assertEquals(56758, result)

    }
}
