package mycom.springbootmvcmybatis.book.service;

import mycom.springbootmvcmybatis.book.dao.BookDao;
import mycom.springbootmvcmybatis.book.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MySQLBookService implements BookService{

    private final BookDao bookDao;

    public MySQLBookService(BookDao bookDao){
        this.bookDao = bookDao;
    }

    @Override
    public List<BookDto> findAll() {
        return bookDao.listBook();
    }

    @Override
    public BookDto findById(int bookId) {
        return bookDao.detailBook(bookId);
    }

    @Override
    public int save(BookDto bookDto) {
        return bookDao.insertBook(bookDto);
    }

    @Override
    public int update(BookDto bookDto) {
        return bookDao.updateBook(bookDto);
    }

    @Override
    public int delete(int bookId) {
        return bookDao.deleteBook(bookId);
    }
}
