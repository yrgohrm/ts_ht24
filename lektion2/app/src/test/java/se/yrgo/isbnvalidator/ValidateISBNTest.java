package se.yrgo.isbnvalidator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.condition.JRE.JAVA_11;
import static org.junit.jupiter.api.condition.OS.WINDOWS;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class ValidateISBNTest {
    @Test
    void junit_checkValidISBN() {
        String validIsbnNumber = "1484274369";
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN(validIsbnNumber);
        assertTrue(result);
    }

    @Test
    void assertj_checkValidISBN() {
        String validIsbnNumber = "1484274369";
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN(validIsbnNumber);
        assertThat(result).isTrue();
    }

    @Test
    void junit_elevenDigitsNotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        assertThrows(NumberFormatException.class, 
            () -> validator.checkISBN("06723360731"));
    }

    @Test
    void assertj_elevenDigitsNotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        assertThatExceptionOfType(NumberFormatException.class)
                .isThrownBy(() -> validator.checkISBN("06723360731"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1484274369", "0672336073", "043942089X"})
    void checkValidISBN(String validIsbnNumber) {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN(validIsbnNumber);
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234", "14842743691"})
    @EmptySource
    void checkBadLengthISBN(String validIsbnNumber) {
        ValidateISBN validator = new ValidateISBN();
        assertThatExceptionOfType(NumberFormatException.class)
                .isThrownBy(() -> validator.checkISBN(validIsbnNumber));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/string-data.csv", numLinesToSkip = 1)
    void checkEqualsIgnore(String str1, String str2, boolean expected) {
        assertThat(str1.equalsIgnoreCase(str2)).isEqualTo(expected);
    }

    @Test
    void testIfAbleToBuy() {
        // only run if the environment variable ENV is set to CI and some
        // user has an ADMIN role
        assumeTrue("CI".equals(System.getenv("ENV"))); // && user.getRoles().contains(Roles.ADMIN));

        String validIsbnNumber = "1484274369";
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkInStock(validIsbnNumber);
        assertThat(result).isTrue();
    }

    @Test
    @EnabledOnOs(WINDOWS)
    @EnabledOnJre(JAVA_11)
    @EnabledIfEnvironmentVariable(named = "ENV", matches="CI")
    void testOnWindowsJava11Ci() {
        // ...
    }
}
