package com.example.services;

import com.example.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity createBook(BookEntity book);

    List<BookEntity> findAll();

    Optional<BookEntity> findOne(Long isbn);
}
