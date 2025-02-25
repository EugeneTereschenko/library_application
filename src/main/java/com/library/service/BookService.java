package com.library.service;
import com.library.dto.AuthorDTO;
import com.library.dto.BookDTO;
import com.library.dto.BookRequestDTO;
import com.library.dto.CategoryDTO;
import com.library.model.Author;
import com.library.model.Book;
import com.library.model.Category;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import com.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public Book addBook(BookRequestDTO bookRequestDTO) {
        Author author = new Author();
        author.setName(bookRequestDTO.getAuthorName());
        author = authorRepository.save(author);

        Category category = new Category();
        category.setName(bookRequestDTO.getCategoryName());
        category = categoryRepository.save(category);

        Book book = new Book();
        book.setTitle(bookRequestDTO.getTitle());
        book.setAuthor(author);
        book.setCategory(category);
        book.setPrice(bookRequestDTO.getPrice());
        book.setNumOfBooks(bookRequestDTO.getNumOfBooks()); // Add this line
        return bookRepository.save(book);
    }

    public Book addBook(BookDTO bookDTO) {
        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        Category category = categoryRepository.findById(bookDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(author);
        book.setCategory(category);
        book.setPrice(bookDTO.getPrice());
        book.setNumOfBooks(bookDTO.getNumOfBooks()); // Add this line

        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Author addAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        return authorRepository.save(author);
    }

    public Category addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return categoryRepository.save(category);
    }
}
