package dev.futdata.api.countries.mapper;

import dev.futdata.api.countries.domain.Country;
import dev.futdata.api.countries.dto.CountryResponse;
import dev.futdata.api.countries.dto.CreateCountryRequest;

public final class CountryMapper {

    private CountryMapper() {}

    public static CountryResponse toResponse(Country country) {
        return new CountryResponse(country.getId(), country.getName(), country.getCode());
    }

    public static Country toEntity(CreateCountryRequest countryRequest){
        return new Country(countryRequest.name(), countryRequest.code());
    }
}
