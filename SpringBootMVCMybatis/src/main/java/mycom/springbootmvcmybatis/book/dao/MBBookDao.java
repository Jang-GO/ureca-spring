package mycom.springbootmvcmybatis.book.dao;

import mycom.springbootmvcmybatis.book.dto.BookDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MBBookDao {
    List<BookDto> listBook();
    int deleteBook(int bookId);
    int insertBook(BookDto book);
    int updateBook(BookDto book);
    BookDto detailBook(int bookId);
}
