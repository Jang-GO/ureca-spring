package mycom.springbootmvc.service;

import mycom.springbootmvc.dao.BookDao;
import mycom.springbootmvc.dto.BookDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<BookDto> listBook() {
        return bookDao.listBook();
    }

    @Override
    public BookDto detailBook(int bookId) {
        return bookDao.detailBook(bookId);
    }

    @Override
    public int insertBook(BookDto book) {
        return bookDao.insertBook(book);
    }

    @Override
    public int updateBook(BookDto book) {
        return bookDao.updateBook(book);
    }

    @Override
    public int deleteBook(int bookId) {
        return bookDao.deleteBook(bookId);
    }
}
