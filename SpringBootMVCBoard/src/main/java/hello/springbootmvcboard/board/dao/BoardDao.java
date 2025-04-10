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

    BoardDto detailBoard(BoardParamDto boardParamDto);

    int insertBoard(BoardDto boardDto);
    int updateBoard(BoardDto boardDto);
    int deleteBoard(int boardId);

    // 상세 - 조회수
    // 현재 사용자가 현재 게시글을 읽었는 지 판단
    int countBoardUserRead(BoardParamDto boardParamDto); // boardId ,userSeq
    // 현재 사용가자 현재 게시글을 읽었다는 표시 추가
    int insertBoardUserRead(BoardParamDto boardParamDto); // boardId ,userSeq
    // 현재 게시글의 조회수 증가
    int updateBoardReadCount(int boardId);
}
