package mycom.springbootmvcmybatis.book.service;


import mycom.springbootmvcmybatis.book.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> findAll();
    BookDto findById(int id);
    int save(BookDto bookDto);
    int update(BookDto bookDto);
    int delete(int bookId);
}
