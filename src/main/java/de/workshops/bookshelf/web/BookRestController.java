package de.workshops.bookshelf.web;

import de.workshops.bookshelf.config.BookshelfProperties;
import de.workshops.bookshelf.domain.Book;
import de.workshops.bookshelf.domain.BookNotFoundException;
import de.workshops.bookshelf.domain.BookSearchRequest;
import de.workshops.bookshelf.service.BookService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@Validated
public class BookRestController {

    private final BookService service;

    private final BookshelfProperties bookshelfProperties;

    public BookRestController(BookService service, BookshelfProperties bookshelfProperties) {
        this.service = service;
        this.bookshelfProperties = bookshelfProperties;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/{isbn}")
    public Book getSingleBookByIsbn(@PathVariable String isbn) {
        return service.getSingleBookByIsbn(isbn);
    }

    @GetMapping(params = "author")
    public Book searchBookByAuthor(@RequestParam @NotBlank @Size(min = 3) String author) {
        return service.getSingleBookByAuthor(author);
    }

    @PostMapping("/search")
    public List<Book> searchBooks(@RequestBody BookSearchRequest searchRequest) {
        return service.searchBooks(searchRequest);
    }

    @GetMapping("/{isbn}/lookup")
    public String lookupIsbn(@PathVariable String isbn) {
        return String.format("%s does a lookup for ISBN %s via %s",
                bookshelfProperties.getOwner(), isbn, bookshelfProperties.getIsbnLookup().getServer());
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found - " + e.getMessage());
    }
}
