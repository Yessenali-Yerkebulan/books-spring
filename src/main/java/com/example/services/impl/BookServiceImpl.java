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
    public BookEntity createBook(BookEntity book) {
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


}
