package hello.springbootmvcboard.board.service;

import hello.springbootmvcboard.board.dao.BoardDao;
import hello.springbootmvcboard.board.dto.BoardDto;
import hello.springbootmvcboard.board.dto.BoardParamDto;
import hello.springbootmvcboard.board.dto.BoardResultDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
    private final BoardDao boardDao;

    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public BoardResultDto listBoard(BoardParamDto boardParamDto) {
        BoardResultDto boardResultDto = new BoardResultDto();

        // 예외 처리
        // 처리과정 중 오류 발생??
        // 1. 직접 제어
        // 2. Spring Framework
        try{
            // board controller 는 BoardService의 listBoard() 1회 호출
            // 그러나 여기서는 BoardDao 의 메소드 2개 호출
            List<BoardDto> boardDtos = boardDao.listBoard(boardParamDto);
            int count = boardDao.listBoardTotalCount();
            boardResultDto.setList(boardDtos);
            boardResultDto.setCount(count);
            boardResultDto.setResult("success");
        }catch (Exception e){
            e.printStackTrace();
            boardResultDto.setResult("fail");
        }

        return boardResultDto;
    }

    @Override
    public BoardResultDto listBoardSearchWord(BoardParamDto boardParamDto) {
        BoardResultDto boardResultDto = new BoardResultDto();

        try{
            List<BoardDto> boardDtos = boardDao.listBoardSearchWord(boardParamDto);
            int count = boardDao.listBoardSearchWordTotalCount(boardParamDto);
            boardResultDto.setList(boardDtos);
            boardResultDto.setCount(count);
            boardResultDto.setResult("success");
        }catch (Exception e){
            e.printStackTrace();
            boardResultDto.setResult("fail");
        }

        return boardResultDto;
    }
}
