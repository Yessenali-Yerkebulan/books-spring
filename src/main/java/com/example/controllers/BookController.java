package com.example.controllers;

import com.example.domain.dto.BookDto;
import com.example.domain.dto.BookDto;
import com.example.domain.entities.AuthorEntity;
import com.example.domain.entities.BookEntity;
import com.example.domain.entities.BookEntity;
import com.example.domain.entities.BookEntity;
import com.example.mappers.Mapper;
import com.example.services.BookService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class BookController {
    private Mapper<BookEntity, BookDto> bookMapper;

    private BookService bookService;

    public BookController(Mapper<BookEntity, BookDto> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PostMapping(path="/books")
    public BookDto createBook(@RequestBody BookDto book){
        BookEntity bookEntity = bookMapper.mapFrom(book);
        BookEntity savedBookEntity = bookService.save(bookEntity);
        return bookMapper.mapTo(savedBookEntity);
    }

    @GetMapping(path="/books")
    public List<BookDto> listBooks(){
        List<BookEntity> books = bookService.findAll();
        return books.stream()
                .map(bookMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping(path="/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") Long isbn){
        Optional<BookEntity> foundBook = bookService.findOne(isbn);
        return foundBook.map(bookEntity -> {
            BookDto bookDto = bookMapper.mapTo(bookEntity);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PutMapping(path="/books/{isbn}")
    public ResponseEntity<BookDto> fullUpdateBook(
            @PathVariable("isbn") Long isbn,
            @RequestBody BookDto bookDto
    ){
        if(!bookService.isExists(isbn)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        bookDto.setIsbn(isbn);
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity savedBookEntity = bookService.save(bookEntity);
        return new ResponseEntity<>(
                bookMapper.mapTo(savedBookEntity),
                HttpStatus.OK
        );
    }

    @PatchMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> partialUpdate(
            @PathVariable("isbn") Long id,
            @RequestBody BookDto bookDto
    ){
        if(!bookService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity updatedBook = bookService.partialUpdate(id, bookEntity);
        return new ResponseEntity<>(
                bookMapper.mapTo(updatedBook),
                HttpStatus.OK
        );
    }
    @DeleteMapping(path="/books/{id}")
    public ResponseEntity deleteBook(@PathVariable("id") Long id){
        bookService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
