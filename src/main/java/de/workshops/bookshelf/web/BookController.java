package de.workshops.bookshelf.web;

import de.workshops.bookshelf.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
class BookController {

    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = List.of(
                new Book("Design Patterns", "Erich Gamma", "978-0201633610", "Mit Design Patterns lassen sich wiederkehrende Aufgaben in der objektorientierten Softwareentwicklung effektiv lösen.")
        );
        model.addAttribute("books", books);

        return "overview";
    }
}
