package com.target.targetcasestudy

import com.target.targetcasestudy.data.validateCreditCard
import org.junit.Assert
import org.junit.Test

/**
 * Feel free to make modifications to these unit tests! Remember, you have full technical control
 * over the project, so you can use any libraries and testing strategies that see fit.
 */
class CreditCardValidatorTest {
    @Test
    fun `all these numbers should be valid`() {
        val cards = listOf(
            "4556737586899855",
            "4929187416634017",
            "4539976741512043",
            "4485063472312879",
            "4485698948487878",
            "4916866359026399374",
            "5563541901990391",
            "5556008890046550",
            "5218482278072415",
            "372677809558781",
            "347407790002546",
            "345344823341694",
            "6011053467758242",
            "6011881924166074",
            "6011463027463020648",
            "3533771163751106",
            "3535949052089576",
            "3528178992154307160",
            "5469200549067774",
            "5426335855810425",
            "5523477092025052",
            "30261805051267",
            "30052378154087",
            "30316425540113",
            "36082553222437",
            "36973018251914",
            "36863398172568",
            "5020155693591346",
            "6762452459982987",
            "6304543719754037",
            "4917381825968090",
            "4026252537947516",
            "4913223185096118",
            "6396618915344996",
            "6373061457470340",
            "6390915862670258"
        )
        cards.forEach { ccNo ->
            testValidNumber(ccNo)
        }
    }

    @Test
    fun `short credit card number should return false`() {
        Assert.assertFalse(
            "short credit card number should return false",
            validateCreditCard("453997674151")
        )
    }

    @Test
    fun `long credit card number should return false`() {
        Assert.assertFalse(
            "long credit card number should return false",
            validateCreditCard("45399767415120432043")
        )
    }

    @Test
    fun `empty credit card number should return false`() {
        Assert.assertFalse(
            "empty credit card number should return false",
            validateCreditCard("")
        )
    }

    @Test
    fun `credit card number containing non-digits should return false`() {
        Assert.assertFalse(
            "credit card number containing non-digits should return false",
            validateCreditCard("45399X6741P18")
        )
    }

    private fun testValidNumber(ccNo: String) {
        val message = "valid credit card $ccNo should yield true"
        Assert.assertTrue(
            message,
            validateCreditCard(ccNo)
        )
    }
}
