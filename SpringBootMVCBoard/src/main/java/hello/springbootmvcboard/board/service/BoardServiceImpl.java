package hello.springbootmvcboard.board.service;

import hello.springbootmvcboard.board.dao.BoardDao;
import hello.springbootmvcboard.board.dto.BoardDto;
import hello.springbootmvcboard.board.dto.BoardParamDto;
import hello.springbootmvcboard.board.dto.BoardResultDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    // transaction test
    //  1. @Transactional X (Spring Transaction 관리 AOP 관여 X)
    //      INSERT O , UPDATE X
    //  2. @Transactional O (Spring Transaction 관리 AOP 관여 O, PointCut 에 추가)
    //       1. RuntimeException 계열 객체 throw 되어서 Transaction 관리 AOP 에 전달되면 rollback
    //       2. 예외 발생 X => commit
    //       3. RuntimeException 계열 객체 throw 가 되어도 try-catch 로 묶어버리면 Transaction 관리 AOP 에 전달 X
    //               catch block 에서 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 을 통해 rollback 처리
    @Override
    @Transactional
    public BoardResultDto detailBoard(BoardParamDto boardParamDto) {
        BoardResultDto boardResultDto = new BoardResultDto();
        try{
            // 조회수 처리
            int userReadCnt = boardDao.countBoardUserRead(boardParamDto);

            System.out.println("boardId : " + boardParamDto.getBoardId());
            System.out.println("userSeq : " + boardParamDto.getUserSeq());
            System.out.println("userReadCnt : " + userReadCnt);

            if(userReadCnt == 0){
                boardDao.insertBoardUserRead(boardParamDto); // 현재 게시글을 현재 사용자가 읽었다. 표시 등록
                boardDao.updateBoardReadCount(boardParamDto.getBoardId());
            }
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

            // Spring 제안 방법
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

            // RuntimeException 객체 생성 & throw
            // throw new IllegalStateException("~~~");
        }

        return boardResultDto;
    }

    @Override
    public BoardResultDto insertBoard(BoardDto boardDto) {
        BoardResultDto boardResultDto = new BoardResultDto();
        try{

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
