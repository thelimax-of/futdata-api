CREATE TABLE countries(
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(2) NOT NULL UNIQUE,
    CONSTRAINT chk_countries_code_format
        CHECK(code ~'^[A-Z]{2}$')
);