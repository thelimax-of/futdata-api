package dev.futdata.api.countries.domain;

import dev.futdata.api.countries.exceptions.InvalidCountryException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CountryTest {
    @Test
    void shouldCreateCountryWithValidData(){
        Country country = new Country("Brasil", "BR");

        assertEquals("Brasil", country.getName());
        assertEquals("BR", country.getCode());
    }

    @Test
    void shouldNormalizeCountryCode(){
        Country country = new Country("Brasil", "br");

        assertEquals("Brasil", country.getName());
        assertEquals("BR", country.getCode());
    }

    @Test
    void shouldTrimCountryName(){
        Country country = new Country("   Brasil   ", "br");

        assertEquals("Brasil", country.getName());
    }

    @Test
    void shouldRejectNullName(){
        InvalidCountryException exception = assertThrows(InvalidCountryException.class, () -> new Country(null, "BR"));

        assertEquals("Country name cannot be null",  exception.getMessage());
    }

    @Test
    void shouldRejectBlankName(){
        InvalidCountryException exception = assertThrows(InvalidCountryException.class, () -> new Country("   ", "BR"));

        assertEquals("Country name cannot be blank",  exception.getMessage());
    }

    @Test
    void shouldRejectNameLongerThan100Characters(){
        InvalidCountryException exception = assertThrows(InvalidCountryException.class, () -> new Country("A".repeat(101), "BR"));

        assertEquals("Country name cannot be longer than 100 characters",  exception.getMessage());
    }

    @Test
    void shouldRejectNullCode(){
        InvalidCountryException exception = assertThrows(InvalidCountryException.class, () -> new Country("Brasil", null));

        assertEquals("Country code cannot be null",  exception.getMessage());
    }

    @Test
    void shouldRejectInvalidCountryCode(){
        InvalidCountryException exception = assertThrows(InvalidCountryException.class, () -> new Country("Brasil", "B1"));

        assertEquals("Country code is not valid",  exception.getMessage());
    }

    @Test
    void shouldRejectCountryCodeLongerThanTwoCharacters(){
        InvalidCountryException exception = assertThrows(InvalidCountryException.class, () -> new Country("Brasil", "BRA"));

        assertEquals("Country code is not valid",  exception.getMessage());
    }

    @Test
    void shouldRejectBlankCode(){
        InvalidCountryException exception = assertThrows(InvalidCountryException.class, () -> new Country("Brasil", " "));

        assertEquals("Country code is not valid",  exception.getMessage());
    }
}
