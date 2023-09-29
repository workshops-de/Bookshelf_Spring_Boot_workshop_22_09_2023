package de.workshops.bookshelf.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Book {

    private String title;
    private String author;
    private String isbn;
    private String description;
}
