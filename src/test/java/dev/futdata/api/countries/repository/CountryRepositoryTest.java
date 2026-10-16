package dev.futdata.api.countries.repository;

import dev.futdata.api.countries.domain.Country;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CountryRepositoryTest {

    private final CountryRepository repository;
    private final EntityManager entityManager;

    public CountryRepositoryTest(CountryRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Test
    void shouldSaveCountrySuccessfully(){
        Country country = new Country("Brasil", "BR");
        Country savedCountry = repository.save(country);

        assertNotNull(savedCountry.getId());
        assertEquals(7, savedCountry.getId().version());

        entityManager.flush();
        entityManager.clear();

        Optional<Country> foundCountry = repository.findById(savedCountry.getId());
        assertTrue(foundCountry.isPresent());

        Country found = foundCountry.get();

        assertEquals("Brasil", found.getName());
        assertEquals("BR", found.getCode());
    }
}
