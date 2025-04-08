package hello.springbootmvcboard.board.dto;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

// java 8 LocalDateTime (client 에게 일시(일자, 시각) 관련 데이터를 년월일 시분초 로 쉽게 내려줄 수 있다.)
public class BoardDto {
    private int boardId;
    private int userSeq;
    private String username;
    private String userProfileImage;
    private String title;
    private String content;

    // spring 의 기본 json 처리자인 jackson 대신 gson 사용.
    // LocalDateTime 을 json 화 할 때 jackson 과 gson 이 다르게 사용
    // gson 이 더 편함
    private LocalDateTime regDt;
    private int readCount;

    private boolean sameUser; // 현재 로그인한 사용자 (게시글을 보는 사용자) 와 글 작성자가 동일한지 여부

    public BoardDto() {
    }

    public BoardDto(String title, int userSeq, String content) {
        this.title = title;
        this.userSeq = userSeq;
        this.content = content;
    }


    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public LocalDateTime getRegDt() {
        return regDt;
    }

    // mybatis Date Mapping 처리 시 LocalDateTime 으로 바로 처리 못함
    public void setRegDt(Date regDt) {
        this.regDt = LocalDateTime.ofInstant(regDt.toInstant(), ZoneId.systemDefault());
    }

    public boolean isSameUser() {
        return sameUser;
    }

    public void setSameUser(boolean sameUser) {
        this.sameUser = sameUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        this.userProfileImage = userProfileImage;
    }

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "boardId=" + boardId +
                ", userSeq=" + userSeq +
                ", username='" + username + '\'' +
                ", userProfileImage='" + userProfileImage + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", retDt=" + regDt +
                ", readCount=" + readCount +
                ", sameUser=" + sameUser +
                '}';
    }
}
