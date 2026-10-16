package dev.futdata.api.countries.domain;

import dev.futdata.api.countries.exceptions.InvalidCountryException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CountryCodeTest {

    @Test
    void shouldNormalizeLowercaseCode(){
        String normalizedCode = CountryCode.normalize("br");

        assertEquals("BR", normalizedCode);
    }

    @Test
    void shouldNormalizeCodeWithSpaces(){
        String normalizedCode = CountryCode.normalize(" br ");
        assertEquals("BR", normalizedCode);
    }

    @Test
    void shouldRejectInvalidCode(){
        InvalidCountryException exception = assertThrows(InvalidCountryException.class, () -> CountryCode.normalize("BRA"));

        assertEquals("Country code is not valid",  exception.getMessage());
    }
}
