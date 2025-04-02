package mycom.springbootmvc.bookmanager.dao;

import mycom.springbootmvc.bookmanager.common.DBManager;
import mycom.springbootmvc.bookmanager.dto.BookDto;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// jdbc 이용
@Repository // db access 클래스
// jpa 같은 persistence library 들의 db 관련 예외를 스프링의 예외로 바꿔줌!!(스프링 예외 추상화)
// 라이브러리가 달라도 동일한 예외처리 방식을 적용할 수 있음
public class BookDaoImpl implements BookDao {
    private final DataSource dataSource;

    public BookDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<BookDto> listBook() {
        List<BookDto> list = new ArrayList<>();
        String sql = "select * from book; ";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = dataSource.getConnection();

            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                BookDto book = new BookDto();
                book.setBookId(rs.getInt("bookid"));
                book.setBookName(rs.getString("bookname"));
                book.setPublisher(rs.getString("publisher"));
                book.setPrice(rs.getInt("price"));
                list.add(book);
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.releaseConnection(rs, pstmt, con);
        }

        return list;
    }

    @Override
    public BookDto detailBook(int bookId) {
        BookDto book = null;
        String sql = "select * from book where bookid = ?; ";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = dataSource.getConnection();

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bookId);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                book = new BookDto();
                book.setBookId(rs.getInt("bookid"));
                book.setBookName(rs.getString("bookname"));
                book.setPublisher(rs.getString("publisher"));
                book.setPrice(rs.getInt("price"));
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.releaseConnection(rs, pstmt, con);
        }

        return book;
    }

    @Override
    public int insertBook(BookDto book) {
        int ret = -1;
        String sql = "insert into book values ( ?, ?, ?, ? ); ";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = dataSource.getConnection();

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, book.getBookId());
            pstmt.setString(2, book.getBookName());
            pstmt.setString(3, book.getPublisher());
            pstmt.setInt(4, book.getPrice());

            ret = pstmt.executeUpdate();

        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.releaseConnection(pstmt, con);
        }

        return ret;
    }

    @Override
    public int updateBook(BookDto book) {
        int ret = -1;
        String sql = "update book set bookname = ?, publisher = ?, price = ? where bookid = ?; ";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = dataSource.getConnection();

            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, book.getBookName());
            pstmt.setString(2, book.getPublisher());
            pstmt.setInt(3, book.getPrice());
            pstmt.setInt(4, book.getBookId());

            ret = pstmt.executeUpdate();

        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.releaseConnection(pstmt, con);
        }

        return ret;
    }

    @Override
    public int deleteBook(int bookId) {
        int ret = -1;
        String sql = "delete from book where bookid = ?; ";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = dataSource.getConnection();

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bookId);

            ret = pstmt.executeUpdate();

        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            DBManager.releaseConnection(pstmt, con);
        }

        return ret;
    }
}