package mycom.springbootmvcmybatis.book.dao;

import mycom.springbootmvcmybatis.book.dto.BookDto;

import java.util.List;

public interface BookDao {
    List<BookDto> listBook();
    int deleteBook(int bookId);
    int insertBook(BookDto book);
    int updateBook(BookDto book);
    BookDto detailBook(int bookId);
}
