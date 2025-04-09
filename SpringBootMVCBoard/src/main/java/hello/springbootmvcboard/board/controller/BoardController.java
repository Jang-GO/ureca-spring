package hello.springbootmvcboard.board.controller;

import hello.springbootmvcboard.board.dto.BoardDto;
import hello.springbootmvcboard.board.dto.BoardParamDto;
import hello.springbootmvcboard.board.dto.BoardResultDto;
import hello.springbootmvcboard.board.service.BoardService;
import hello.springbootmvcboard.user.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list") // limit, offset, searchWord 모두 한꺼번에 처리 ( service, dao layer 는 분리되어 있다.)
    @ResponseBody
    public BoardResultDto listBoard(BoardParamDto boardParamDto){
        BoardResultDto result = null;

        if(Strings.isEmpty(boardParamDto.getSearchWord()) ) {
            result = boardService.listBoard(boardParamDto);
        }else{
            result = boardService.listBoardSearchWord(boardParamDto);
        }

        return result;
    }

    @GetMapping("/detail/{boardId}")
    @ResponseBody
    public BoardResultDto detailBoard(@PathVariable Integer boardId, HttpSession session){
        BoardParamDto boardParamDto = new BoardParamDto();
        boardParamDto.setBoardId(boardId);

        UserDto userDto = (UserDto) session.getAttribute("userDto");
        if (userDto == null) {
            // 로그인 안 되어 있으면 비동기 요청 응답
            BoardResultDto resultDto = new BoardResultDto();
            resultDto.setResult("login");
            return resultDto;
        }

        boardParamDto.setUserSeq(userDto.getUserSeq());
        return boardService.detailBoard(boardParamDto);
    }

    @PostMapping("/insert")
    @ResponseBody
    public BoardResultDto insertBoard(BoardDto boardDto, HttpSession session){ // client 에서 boardId, userSeq 전송 X
        int userSeq = ((UserDto) session.getAttribute("userDto")).getUserSeq();
        boardDto.setUserSeq(userSeq);
        return boardService.insertBoard(boardDto);
    }


    @PostMapping("/update")
    @ResponseBody
    public BoardResultDto updateBoard(BoardDto boardDto){
        return boardService.updateBoard(boardDto);
    }

    @GetMapping("/delete/{boardId}")
    @ResponseBody
    // client에서 boardId 전송 O, boardId 자동으로 mapping 안되면 null 처리 시도, primitive type 으로 오류 발생
    public BoardResultDto deleteBoard(@PathVariable int boardId){
        return boardService.deleteBoard(boardId);
    }
}
