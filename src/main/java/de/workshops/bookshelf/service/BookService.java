package de.workshops.bookshelf.service;

import de.workshops.bookshelf.domain.Book;
import de.workshops.bookshelf.domain.BookNotFoundException;
import de.workshops.bookshelf.domain.BookSearchRequest;
import de.workshops.bookshelf.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.findAllBooks();
    }

    public Book getSingleBookByIsbn(String isbn) throws BookNotFoundException {
        return repository.findAllBooks().stream()
                .filter(book -> hasIsbn(book, isbn))
                .findFirst().orElseThrow(() -> new BookNotFoundException("ISBN: " + isbn));
    }

    public Book getSingleBookByAuthor(String author) throws BookNotFoundException {
        return repository.findAllBooks().stream()
                .filter(book -> hasAuthor(book, author))
                .findFirst().orElseThrow(() -> new BookNotFoundException("Author: " + author));
    }

    public List<Book> searchBooks(BookSearchRequest searchRequest) {
        return repository.findAllBooks().stream()
                .filter(book -> searchRequest.getIsbn() == null || hasIsbn(book, searchRequest.getIsbn()))
                .filter(book -> searchRequest.getAuthor() == null || hasAuthor(book, searchRequest.getAuthor()))
                .toList();
    }

    private boolean hasIsbn(Book book, String isbn) {
        return book.getIsbn().equals(isbn);
    }

    private boolean hasAuthor(Book book, String author) {
        return book.getAuthor().contains(author);
    }
}
