package com.example.mappers.impl;

import com.example.domain.dto.BookDto;
import com.example.domain.entities.BookEntity;
import com.example.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class BookMapper implements Mapper<BookEntity, BookDto> {
    private ModelMapper modelMapper;

    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public BookDto mapTo(BookEntity book) {
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public BookEntity mapFrom(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }
}
