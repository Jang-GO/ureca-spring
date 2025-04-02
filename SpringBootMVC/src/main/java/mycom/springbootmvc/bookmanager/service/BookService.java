package mycom.springbootmvc.bookmanager.service;

import mycom.springbootmvc.bookmanager.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> findAll();
    BookDto findById(int id);
    int save(BookDto bookDto);
    int update(BookDto bookDto);
    int delete(int bookId);
}
