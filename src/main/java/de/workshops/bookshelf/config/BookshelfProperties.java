package de.workshops.bookshelf.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "bookshelf")
@Getter
@Setter
public class BookshelfProperties {

    private String owner;

    @NestedConfigurationProperty
    private IsbnLookupProperties isbnLookup = new IsbnLookupProperties();
}
