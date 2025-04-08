package hello.springbootmvcboard.board.service;

import hello.springbootmvcboard.board.dto.BoardParamDto;
import hello.springbootmvcboard.board.dto.BoardResultDto;



public interface BoardService {
    BoardResultDto listBoard(BoardParamDto boardParamDto); // limit, offset
    BoardResultDto listBoardSearchWord(BoardParamDto boardParamDto); // limit, offset
}
