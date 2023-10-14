package com.example.services.impl;

import com.example.domain.entities.BookEntity;
import com.example.repositories.BookRepository;
import com.example.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity save(BookEntity book) {
        return bookRepository.save(book);
    }

    @Override
    public List<BookEntity> findAll() {
        return StreamSupport.stream(bookRepository
                .findAll()
                .spliterator(),
                false).collect(Collectors.toList());
    }

    @Override
    public Optional<BookEntity> findOne(Long isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public boolean isExists(Long isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public BookEntity partialUpdate(Long id, BookEntity bookEntity) {
        bookEntity.setIsbn(id);

        return bookRepository.findById(id).map(existingBook->{
            Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingBook::setTitle);
            Optional.ofNullable(bookEntity.getAuthor()).ifPresent(existingBook::setAuthor);
            return bookRepository.save(existingBook);
        }).orElseThrow(()->new RuntimeException("Book does not exist"));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }


}
