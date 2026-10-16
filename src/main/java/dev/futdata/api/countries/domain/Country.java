package dev.futdata.api.countries.domain;

import dev.futdata.api.countries.exceptions.InvalidCountryException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Locale;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "countries")
public class Country {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.VERSION_7)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Size(max = 100)
    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    @Pattern(regexp = "^[A-Z]{2}$")
    @NotBlank
    @Column(nullable = false, unique = true, length = 2)
    private String code;

    public Country(String name, String code) {
        if(name == null) {
            throw new InvalidCountryException("Country name cannot be null");
        }

        if(code == null) {
            throw new InvalidCountryException("Country code cannot be null");
        }

        name = name.strip();
        code = code.strip().toUpperCase(Locale.ROOT);

        if(name.isBlank()) {
            throw new InvalidCountryException("Country name cannot be blank");
        }

        if(name.length() > 100) {
            throw new InvalidCountryException("Country name cannot be longer than 100 characters");
        }

        if(!code.matches("^[A-Z]{2}$")) {
            throw new InvalidCountryException("Country code is not valid");
        }


        this.name = name;
        this.code = code;
    }
}