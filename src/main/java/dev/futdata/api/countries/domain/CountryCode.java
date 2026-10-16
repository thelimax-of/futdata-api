package dev.futdata.api.countries.domain;

import dev.futdata.api.countries.exceptions.InvalidCountryException;

import java.util.Locale;

public final class CountryCode {
    private CountryCode() {
    }

    public static String normalize(String code){
        if(code == null) {
            throw new InvalidCountryException("Country code cannot be null");
        }

        String normalizedCode = code.strip().toUpperCase(Locale.ROOT);

        if(!normalizedCode.matches("^[A-Z]{2}$")) {
            throw new InvalidCountryException("Country code is not valid");
        }

        return normalizedCode;
    }
}
