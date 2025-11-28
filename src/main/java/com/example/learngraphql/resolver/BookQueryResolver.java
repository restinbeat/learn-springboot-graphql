package com.example.learngraphql.resolver;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.learngraphql.domain.Book;
import com.example.learngraphql.repository.BookRepository;

@Controller
public class BookQueryResolver {
    
    private final BookRepository bookRepository;
    
    public BookQueryResolver(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @QueryMapping
    public List<Book> books() {
        return bookRepository.findAll();
    }
    
    @QueryMapping
    public Book book(@Argument Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    
    @QueryMapping
    public List<Book> booksByAuthor(@Argument String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
    
    @QueryMapping
    public List<Book> booksByTitle(@Argument String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
}
