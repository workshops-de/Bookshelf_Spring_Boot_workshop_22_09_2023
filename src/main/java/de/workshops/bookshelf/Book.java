package de.workshops.bookshelf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private String title;
    private String description;
    private String author;
    private String isbn;
}
