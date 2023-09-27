package de.workshops.bookshelf.config;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;

@Getter
@Setter
public class IsbnLookupProperties {

    private URI server;

    private String apiKey;
}
