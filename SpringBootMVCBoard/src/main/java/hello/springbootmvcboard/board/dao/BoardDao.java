package hello.springbootmvcboard.board.dao;

import hello.springbootmvcboard.board.dto.BoardDto;
import hello.springbootmvcboard.board.dto.BoardParamDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {

    List<BoardDto> listBoard(BoardParamDto boardParamDto); // limit, offset
    int listBoardTotalCount(); //

    // 검색어
    List<BoardDto> listBoardSearchWord(BoardParamDto boardParamDto); // limit, offset, searchWord
    int listBoardSearchWordTotalCount(BoardParamDto boardParamDto); // searchWord
}
