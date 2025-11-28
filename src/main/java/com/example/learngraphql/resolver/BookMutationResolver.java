package com.example.learngraphql.resolver;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.example.learngraphql.domain.Book;
import com.example.learngraphql.dto.BookInput;
import com.example.learngraphql.repository.BookRepository;

@Controller
@Transactional
public class BookMutationResolver {
    
    private final BookRepository bookRepository;

    public BookMutationResolver(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @MutationMapping
    public Book createBook(@Argument BookInput input) {
        Book book = new Book();
        book.setTitle(input.getTitle());
        book.setAuthor(input.getAuthor());
        book.setPublishedYear(input.getPublishedYear());
        
        return bookRepository.save(book);
    }
    
    @MutationMapping
    public Book updateBook(@Argument Long id, @Argument BookInput input) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        
        book.setTitle(input.getTitle());
        book.setAuthor(input.getAuthor());
        book.setPublishedYear(input.getPublishedYear());
        
        return bookRepository.save(book);
    }
    
    @MutationMapping
    public Boolean deleteBook(@Argument Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        
        bookRepository.deleteById(id);
        return true;
    }
}
