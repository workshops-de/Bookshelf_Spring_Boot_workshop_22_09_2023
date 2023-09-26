package de.workshops.bookshelf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookRestControllerInjectionTest {

    @Autowired
    BookRestController restController;

    @Test
    void getAllBooks_should_return_all_books() {
        List<Book> books = restController.getAllBooks();
        assertThat(books).hasSize(3);
    }
}
