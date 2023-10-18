package com.example.repositories;

import com.example.domain.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long>,
        PagingAndSortingRepository<BookEntity, Long> {
}
