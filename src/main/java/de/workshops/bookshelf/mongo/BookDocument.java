package de.workshops.bookshelf.mongo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookDocument {

    private String isbn;

    private String author;
}
