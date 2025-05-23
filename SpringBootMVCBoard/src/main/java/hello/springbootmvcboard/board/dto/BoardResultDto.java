package hello.springbootmvcboard.board.dto;

import java.util.List;
public class BoardResultDto {
    private String result; // 등록, 수정, 삭제 작업 성공, 실패
    private List<BoardDto> list; // 목록
    private BoardDto dto; // 상세
    private int count; // 목록 데이터 건수

    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public List<BoardDto> getList() {
        return list;
    }
    public void setList(List<BoardDto> list) {
        this.list = list;
    }
    public BoardDto getDto() {
        return dto;
    }
    public void setDto(BoardDto dot) {
        this.dto = dot;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BoardResultDto [result=" + result + ", list=" + list + ", dot=" + dto + ", count=" + count + "]";
    }
}
