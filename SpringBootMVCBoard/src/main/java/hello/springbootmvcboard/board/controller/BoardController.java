package hello.springbootmvcboard.board.controller;

import hello.springbootmvcboard.board.dto.BoardParamDto;
import hello.springbootmvcboard.board.dto.BoardResultDto;
import hello.springbootmvcboard.board.service.BoardService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
