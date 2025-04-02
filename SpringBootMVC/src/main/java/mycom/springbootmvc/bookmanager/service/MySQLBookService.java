package mycom.springbootmvc.bookmanager.service;

import mycom.springbootmvc.bookmanager.dao.BookDao;
import mycom.springbootmvc.bookmanager.dto.BookDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
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
