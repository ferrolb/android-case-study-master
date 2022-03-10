package com.target.targetcasestudy.data

private val regEx = Regex("[0-9]+")
/**
 * For an explanation of how to validate credit card numbers read:
 *
 * https://www.freeformatter.com/credit-card-number-generator-validator.html#fakeNumbers
 *
 * This contains a breakdown of how this algorithm should work as
 * well as a way to generate fake credit card numbers for testing
 *
 * The structure and signature of this is open to modification, however
 * it *must* include a method, field, etc that returns a [Boolean]
 * indicating if the input is valid or not
 *
 * Additional notes:
 *  * This method does not need to validate the credit card issuer
 *  * This method must validate card number length (13 - 19 digits), but does not
 *    need to validate the length based on the issuer.
 *
 * @param creditCardNumber - credit card number of (13, 19) digits
 * @return true if a credit card number is believed to be valid,
 * otherwise false
 */
fun validateCreditCard(creditCardNumber: String): Boolean {

    // is card number correct length
    if (creditCardNumber.isEmpty() || creditCardNumber.length < 13 || creditCardNumber.length > 19) {
        return false
    }

    // does card number contain only digits
    if (!creditCardNumber.matches(regEx)) {
        return false
    }

    return checkLuhn(creditCardNumber) 
}

/**
The Luhn Formula:
- Drop the last digit from the number. The last digit is what we want to check against
- Reverse the numbers
- Multiply the digits in odd positions (1, 3, 5, etc.) by 2 and subtract 9 to all any result higher than 9
- Add all the numbers together
- The check digit (the last number of the card) is the amount that you would need to add to get a multiple of 10 (Modulo 10)
 */
private fun checkLuhn(purportedCC: String): Boolean {
    val zeroAscii = '0'.toInt()
    val nDigits = purportedCC.length
    var sum = purportedCC.last().toInt() - zeroAscii
    val parity = (nDigits-2) % 2
    for (i in 0..(nDigits - 2)) {
        var digit = purportedCC[i].toInt() - zeroAscii
        if (i % 2 == parity) {
            digit *= 2
        }
        if (digit > 9) {
            digit -= 9
        }
        sum += digit
    }
    return (sum % 10) == 0
}

/**
 * One referenced on website no worky
*/
//private fun passesLuhnFormula(cCNumber: String): Boolean {
//    val zeroAscii = '0'.toInt()
//    val lastDigit = cCNumber.last().toInt() - zeroAscii
//    val cCNumberMinusLast = cCNumber.substring(0, cCNumber.length - 1)
//    val cCNumberMinusLastReversed = cCNumberMinusLast.reversed()
////    println("$cCNumberMinusLastReversed")
//    var total = 0
//    for (i in cCNumberMinusLastReversed.indices) {
//        var result = cCNumberMinusLastReversed[i].toInt() - zeroAscii
//        // since we are 0-based indices, we check even instead of odd.
//        if (i % 2 == 0) {
//            val digit = cCNumberMinusLastReversed[i].toInt() - zeroAscii
//            result = digit * 2
//            if (result > 9) {
//                result -= 9
//            }
//        }
////        print("$result ")
//        total += result
//    }
////    println("")
////    println("Total:$total, lastDigit:$lastDigit, ccNumber:$cCNumber")
//    return total % 10 == lastDigit
//}