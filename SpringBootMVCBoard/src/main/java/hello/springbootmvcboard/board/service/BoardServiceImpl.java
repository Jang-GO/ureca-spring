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

    // 게시글 상세 정보 + 조회수 처리
    @Override
    public BoardResultDto detailBoard(BoardParamDto boardParamDto) {
        BoardResultDto boardResultDto = new BoardResultDto();
        try{
            // 조회수 처리

            // 게시글 상세
            BoardDto boardDto = boardDao.detailBoard(boardParamDto);
            // sameUser
            if(boardDto.getUserSeq() == boardParamDto.getUserSeq()){
                boardDto.setSameUser(true);
            }

            boardResultDto.setDto(boardDto);
            boardResultDto.setResult("success");
        }catch (Exception e){
            e.printStackTrace();
            boardResultDto.setResult("fail");
        }

        return boardResultDto;
    }

    @Override
    public BoardResultDto insertBoard(BoardDto boardDto) {
        BoardResultDto boardResultDto = new BoardResultDto();
        try{
            // 조회수 처리

            // 게시글 상세
            int ret = boardDao.insertBoard(boardDto);

            if(ret==1) boardResultDto.setResult("success");
            else boardResultDto.setResult("fail");
        }catch (Exception e){
            e.printStackTrace();
            boardResultDto.setResult("fail");
        }

        return boardResultDto;
    }

    @Override
    public BoardResultDto updateBoard(BoardDto boardDto) {
        BoardResultDto boardResultDto = new BoardResultDto();
        try{
            int ret = boardDao.updateBoard(boardDto);

            if(ret==1) boardResultDto.setResult("success");
            else boardResultDto.setResult("fail");
        }catch (Exception e){
            e.printStackTrace();
            boardResultDto.setResult("fail");
        }

        return boardResultDto;
    }

    @Override
    public BoardResultDto deleteBoard(int boardId) {
        BoardResultDto boardResultDto = new BoardResultDto();
        try{
            int ret = boardDao.deleteBoard(boardId);

            if(ret==1) boardResultDto.setResult("success");
            else boardResultDto.setResult("fail");
        }catch (Exception e){
            e.printStackTrace();
            boardResultDto.setResult("fail");
        }

        return boardResultDto;
    }
}
