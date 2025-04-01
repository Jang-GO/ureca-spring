package mycom.springbootmvc.bookmanager;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MockBookService implements BookService{

    private final Map<Integer, BookDto> db = new HashMap<>() {{
        put(1, new BookDto(1, "자바의 정석", "도우출판", 30000));
        put(2, new BookDto(2, "이펙티브 자바", "인사이트", 45000));
        put(3, new BookDto(3, "클린 코드", "한빛미디어", 38000));
        put(4, new BookDto(4, "코딩 인터뷰 완전 분석", "길벗", 55000));
        put(5, new BookDto(5, "스프링 부트와 AWS", "프리렉", 32000));
    }};

    // TODO : DB 연결 후 bookDao 에서 가져와야함
    public List<BookDto> findAll() {
        List<BookDto> list = new ArrayList<>();
        for(int bookId: db.keySet()) list.add(db.get(bookId));
        return list;
    }

    public BookDto findById(int bookId) {
        return db.get(bookId);
    }

    public int save(BookDto bookDto) {
        db.put(bookDto.getBookId(), bookDto);
        return 1;
    }

    @Override
    public int update(BookDto bookDto) {
        int bookId = bookDto.getBookId();
        db.put(bookId, bookDto);
        return 1;
    }

    @Override
    public int delete(int bookId) {
        db.remove(bookId);
        System.out.println(bookId);
        return 1;
    }
}
