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
    fun `is credit card number1 valid`() {
        Assert.assertTrue(
            "valid credit card number1 should yield true",
            validateCreditCard("4556737586899855")
        )
    }

    //  @Test
//  fun `is credit card number2 valid`() {
//    Assert.assertTrue(
//      "valid credit card number2 should yield true",
//      validateCreditCard("4929187416634017")
//    )
//  }
    @Test
    fun `is credit card number invalid`() {
        Assert.assertFalse(
            "valid credit card number should yield true",
            validateCreditCard("4539976741512043")
        )
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
}
