package de.workshops.bookshelf.web;

import de.workshops.bookshelf.domain.Book;
import de.workshops.bookshelf.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookRestController.class)
@AutoConfigureMockMvc
class BookRestControllerWebMvcTest {

    @MockBean
    BookService bookService;

    @Autowired
    MockMvc mvc;

    List<Book> allBooks = List.of(
            new Book("First Book", "Our first book", "Someone", "12345"),
            new Book("Second Book", "Our second book", "Somebody", "98765"),
            new Book("Third Book", "Our third book", "Someone", "12321")
    );

    @Test
    void get_book_should_return_all_books() throws Exception {
        when(bookService.getAllBooks()).thenReturn(allBooks);

        mvc.perform(get("/book"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(allBooks.size())))
                .andExpect(jsonPath("$[1].title", is(allBooks.get(1).getTitle())))
        ;
    }
}