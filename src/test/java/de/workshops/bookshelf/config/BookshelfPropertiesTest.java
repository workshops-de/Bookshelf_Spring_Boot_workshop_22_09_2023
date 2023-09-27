package de.workshops.bookshelf.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookshelfPropertiesTest {

    @Autowired
    BookshelfProperties properties;

    @Test
    void owner_should_be_defined() {
        assertThat(properties.getOwner()).isNotBlank();
    }
}
