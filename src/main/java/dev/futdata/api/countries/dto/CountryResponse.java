package dev.futdata.api.countries.dto;

import java.util.UUID;

public record CountryResponse(UUID id, String name, String code) {
}
