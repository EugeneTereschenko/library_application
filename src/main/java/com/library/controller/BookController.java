package com.library.controller;

import com.library.dto.AuthorDTO;
import com.library.dto.BookDTO;
import com.library.dto.BookRequestDTO;
import com.library.dto.CategoryDTO;
import com.library.model.Author;
import com.library.model.Book;
import com.library.model.Category;
import com.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PreAuthorize("userService.isAuthenticated()")
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PreAuthorize("userService.isAuthenticated()")
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO) {
        Book book = bookService.addBook(bookDTO);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PreAuthorize("userService.isAuthenticated()")
    @PostMapping("/authors")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorDTO authorDTO) {
        Author savedAuthor = bookService.addAuthor(authorDTO);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @PreAuthorize("userService.isAuthenticated()")
    @PostMapping("/categories")
    public ResponseEntity<Category> addCategory(@RequestBody CategoryDTO categoryDTO) {
        Category savedCategory = bookService.addCategory(categoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @PreAuthorize("userService.isAuthenticated()")
    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        Book book = bookService.addBook(bookRequestDTO);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PreAuthorize("userService.isAuthenticated()")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Book deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("userService.isAuthenticated()")
    @PostMapping("/info")
    public ResponseEntity<String> info() {
        return ResponseEntity.ok("Hello World");
    }
}

