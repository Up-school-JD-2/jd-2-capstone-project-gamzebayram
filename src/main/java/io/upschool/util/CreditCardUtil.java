package io.upschool.util;

public class CreditCardUtil {

    public static String maskCardNumber(String cardNumber) {
        String cleanedCardNumber = cardNumber.replaceAll("\\D", "");

        if (cleanedCardNumber.length() != 16) {
            throw new IllegalArgumentException("Invalid credit card number.");
        }

        String maskedCardNumber = cleanedCardNumber.replaceAll("\\b(\\d{6})(\\d{6})(\\d{4})", "$1******$3");
        return maskedCardNumber;
    }


}
