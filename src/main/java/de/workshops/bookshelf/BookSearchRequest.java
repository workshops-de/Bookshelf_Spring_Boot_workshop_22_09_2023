package de.workshops.bookshelf;

import lombok.Data;

@Data
public class BookSearchRequest {

    private String author;
    private String isbn;
}
