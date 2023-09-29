package de.workshops.bookshelf.repository;

import de.workshops.bookshelf.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@DataJpaTest(properties = "logging.level.org.hibernate.SQL=debug")
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    @Sql(statements = "INSERT INTO book (isbn) VALUES ('123456')", executionPhase = BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM book WHERE isbn = '123456'", executionPhase = AFTER_TEST_METHOD)
    void findByIsbn_with_known_isbn_should_return_the_book() {
        String knownIsbn = "123456";
        Optional<Book> book = bookRepository.findByIsbn(knownIsbn);
        assertThat(book).isPresent();
        assertThat(book.get().getIsbn()).isEqualTo(knownIsbn);
    }

    @Test
    void findByIsbn_with_unknown_isbn_should_empty() {
        String unknownIsbn = "123456789";
        Optional<Book> book = bookRepository.findByIsbn(unknownIsbn);
        assertThat(book).isEmpty();
    }
}
