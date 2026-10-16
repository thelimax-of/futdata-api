package dev.futdata.api.countries.service;

import dev.futdata.api.countries.domain.Country;
import dev.futdata.api.countries.domain.CountryCode;
import dev.futdata.api.countries.dto.CountryResponse;
import dev.futdata.api.countries.dto.CreateCountryRequest;
import dev.futdata.api.countries.exceptions.CountryNotFoundException;
import dev.futdata.api.countries.mapper.CountryMapper;
import dev.futdata.api.countries.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<CountryResponse> findAll(){
        return repository.findAll().stream().map(CountryMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CountryResponse findByCode(String code){
        return CountryMapper.toResponse(getCountryByCode(code));
    }

    @Transactional
    public CountryResponse create(CreateCountryRequest request){
        Country savedCountry = repository.save(CountryMapper.toEntity(request));
        return CountryMapper.toResponse(savedCountry);
    }

    @Transactional
    public void deleteByCode(String code){
        repository.delete(getCountryByCode(code));
    }

    private Country getCountryByCode(String code){
        String normalizedCode = CountryCode.normalize(code);

        return repository.findByCode(normalizedCode).orElseThrow(() -> new CountryNotFoundException("Country with code '" + normalizedCode + "' not found"));
    }
}
