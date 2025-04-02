package mycom.springbootmvc.service;

import mycom.springbootmvc.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> listBook();
    int deleteBook(int bookId);
    int insertBook(BookDto book);
    int updateBook(BookDto book);
    BookDto detailBook(int bookOd);
}
