package mycom.springbootmvc.controller;

import mycom.springbootmvc.dto.BookDto;
import mycom.springbootmvc.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//
@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    // 목록 : /books/list, Get, X, list.jsp
    @GetMapping("/list")
    public String list(Model model){
        List<BookDto> bookDtos = bookService.listBook();
        model.addAttribute("bookList", bookDtos);
        return "list";
    }

    // 상세 : /books/detail, Get, bookId, detailForm.jsp
    @GetMapping("/detail/{bookId}")
    public String detail(@PathVariable("bookId") int bookId, Model model){
        BookDto bookDto = bookService.detailBook(bookId);
        model.addAttribute("bookDto", bookDto);
        return "detailForm";
    }

    // 등록 : /books/insert, Post, bookDto, insertResult.jsp
    @PostMapping("/insert")
    public String insertBook(BookDto bookDto){
        int ret = bookService.insertBook(bookDto);
        return "insertResult";
    }
    // 수정 : /books/update, Post, bookDto, updateResult.jsp
    @PostMapping("/update")
    public String updateBook(BookDto bookDto){
        int ret = bookService.updateBook(bookDto);
        return "updateResult";
    }
    // 삭제 : /books/delete, Get, bookId, deleteResult.jsp
    @GetMapping("/delete/{bookId}")
    public String delete(@PathVariable("bookId") int bookId){
        int ret = bookService.deleteBook(bookId);
        return "deleteResult";
    }
    // insertForm.jsp 에 대한 요청도 컨트롤러를 통해서 이동
    // 등록화면 : /books/insertForm, Get, X, insertForm.jsp
    @GetMapping("/insertForm")
    public String insertForm(){
        return "insertForm";
    }
}
