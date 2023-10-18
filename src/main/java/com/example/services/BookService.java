package com.example.services;

import com.example.domain.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity save(BookEntity book);

    List<BookEntity> findAll();

    Page<BookEntity> findAll(Pageable pageable);

    Optional<BookEntity> findOne(Long isbn);

    boolean isExists(Long isbn);

    BookEntity partialUpdate(Long id, BookEntity bookEntity);

    void delete(Long id);
}
