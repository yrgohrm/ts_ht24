package se.yrgo.isbnvalidator;

public class ValidateISBN {
    private static final int LONG_ISBN_MULTIPLIER = 10;
    private static final int SHORT_ISBN_MULTIPLIER = 11;
    private static final int SHORT_ISBN_LENGTH = 10;
    private static final int LONG_ISBN_LENGTH = 13;

    public boolean checkInStock(String isbn) {
        if (checkISBN(isbn)) {
            // should do some accutal stuff here
            return true;
        }

        return false;
    }

    public boolean checkISBN(String isbn) {
        if (isbn.length() == LONG_ISBN_LENGTH) {
            return checkISBN13(isbn);
        }
        if (isbn.length() == SHORT_ISBN_LENGTH) {
            return checkISBN10(isbn);
        }
        throw new NumberFormatException("ISBN numbers must be 10 or 13 digits long");
    }

    private boolean checkISBN10(String isbn) {
        int total = 0;
        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
            char currentChar = isbn.charAt(i);
            if (!Character.isDigit(currentChar)) {
                if (i == SHORT_ISBN_LENGTH - 1 && currentChar == 'X') {
                    total += 10;
                }
                else {
                    throw new NumberFormatException("ISBN numbers can only contain numeric digits");
                }
            }
            else {
                total += Character.getNumericValue(currentChar) * (SHORT_ISBN_LENGTH - i);
            }
        }
        return total % SHORT_ISBN_MULTIPLIER == 0;
    }

    private boolean checkISBN13(String isbn) {
        int total = 0;
        for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
            if (i % 2 == 0) {
                total += Character.getNumericValue(isbn.charAt(i));
            }
            else {
                total += Character.getNumericValue(isbn.charAt(i)) * 3;
            }
        }
        return total % LONG_ISBN_MULTIPLIER == 0;
    }
}
