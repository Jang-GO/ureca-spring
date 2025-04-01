package mycom.springbootmvc.bookmanager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String home(){
        return "bookmanager/home";
    }

    @GetMapping("/books")
    public String books(){
        return "bookmanager/books";
    }

    @GetMapping("/books/list")
    @ResponseBody
    public List<BookDto> bookList(){
        List<BookDto> bookList = bookService.findAll();
        return bookList;
    }

    @GetMapping("/books/detail")
    @ResponseBody
    public BookDto bookDetail(@RequestParam("bookId") int bookId){
        // TODO : DB 에서 bookId로 찾은 BookDto를 반환해야 함
        BookDto book = bookService.findById(bookId);
        return book;
    }

    @PostMapping("/books/insert")
    @ResponseBody
    public Map<String, String> insertBook(BookDto bookDto){
        int ret = bookService.save(bookDto);
        return Map.of("result", ret == 1 ? "success" : "fail");
    }
    @PostMapping("/books/update")
    @ResponseBody
    public Map<String, String> updateBook(BookDto bookDto){
        int ret = bookService.update(bookDto);
        return Map.of("result", ret == 1 ? "success" : "fail");
    }

    @GetMapping("/books/delete")
    @ResponseBody
    public Map<String, String> deleteBook(@RequestParam("bookId") int bookId){
        int ret = bookService.delete(bookId);
        return Map.of("result", ret == 1 ? "success" : "fail");
    }
}
