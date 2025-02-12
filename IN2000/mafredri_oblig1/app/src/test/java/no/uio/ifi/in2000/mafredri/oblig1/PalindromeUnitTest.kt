package no.uio.ifi.in2000.mafredri.oblig1

import org.junit.Test
import org.junit.Assert.*

class PalindromeUnitTest {

    /**
     * Et palindrom er et stykke tekst som er
     * identisk sett både forfra og bakfra.
     */

    @Test
    fun isPalindrome_inputIsPureText_expectTrue() {

        // Arrange
        val input = "Racecar"

        // Act
        val result = isPalindrome(input)

        // Assert
        assertTrue(result)

    }

    @Test
    fun isPalindrome_inputIsPureText_expectFalse() {

        // Arrange
        val input = "Hello"

        // Act
        val result = isPalindrome(input)

        // Assert
        assertFalse(result)

    }

    @Test
    fun isPalindrome_inputContainsNumbers_expectTrue() {

        // Arrange
        val input = "IN200002NI"

        // Act
        val result = isPalindrome(input)

        // Assert
        assertTrue(result)

    }



    @Test
    fun isPalindrome_inputContainsNumbers_expectFalse() {

        // Arrange
        val input = "IN2020002NI"

        // Act
        val result = isPalindrome(input)

        // Assert
        assertFalse(result)

    }

    @Test
    fun isPalindrome_inputContainsSymbols_expectTrue() {

        // Arrange
        val input = "~ § ` 2 ^ x ^ 2 ` § ~"

        // Act
        val result = isPalindrome(input)

        // Assert
        assertTrue(result)

    }



    @Test
    fun isPalindrome_inputContainsSymbols_expectFalse() {

        // Arrange
        val input = "~< >2^2< >~"

        // Act
        val result = isPalindrome(input)

        // Assert
        assertFalse(result)

    }

}
