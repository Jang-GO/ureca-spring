<%@ page import="hello.springbootmvcboard.user.dto.UserDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
    UserDto userDto = (UserDto) session.getAttribute("userDto");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <%-- bootstrap--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.4/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-DQvkBjpPgn7RC31MCQoOeC9TI2kdqa4+BSgNMNj8v77fdC77Kj5zpWFTJaaAoMbC" crossorigin="anonymous">

    <!-- alertify js -->
    <script src="//cdn.jsdelivr.net/npm/alertifyjs@1.14.0/build/alertify.min.js"></script>

    <!-- Bootstrap theme -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.14.0/build/css/themes/bootstrap.min.css"/>

    <title>게시판</title>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container">
        <a class="navbar-brand" href="#">
            <img src="/assets/img/user/<%= userDto.getUserProfileImage()%>" style="width: 24px; height: 24px">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/auth/logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class ="container mb-3">
    <h4 class = "text-center">게시판</h4>

    <div class="input-group mb-3">
        <input id ="inputSearchWord" type="text" class="form-control" placeholder="검색어를 입력하세요.">
        <button id="btnSearchWord" class="btn btn-success" >검색</button>
    </div>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>#</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일시</th>
        </tr>
        </thead>
        <tbody id = "boardTbody">
        </tbody>
    </table>
    <button type="button" id="btnInsertPage" class="btn btn-primary">글쓰기</button>

</div>

<div class="modal" tabindex="-1" id="detailBoardModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">글 상세</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="example table-responsive">
                    <table class="table">
                        <tbody>
                        <tr><td>글번호</td><td id="boardIdDetail">#</td></tr>
                        <tr><td>제목</td><td id="titleDetail">#</td></tr>
                        <tr><td>내용</td><td id="contentDetail">#</td></tr>
                        <tr><td>작성자</td><td id="userNameDetail">#</td></tr>
                        <tr><td>작성일시</td><td id="regDtDetail">#</td></tr>
                        <tr><td>조회수</td><td id="readCountDetail">#</td></tr>
                        </tbody>
                    </table>
                </div>
                <button type="button" id="btnBoardUpdateForm" class="btn btn-sm btn-primary" data-bs-dismiss="modal">글 수정하기</button>
                <button type="button" id="btnBoardDeleteConfirm"  class="btn btn-sm btn-warning" data-bs-dismiss="modal">글 삭제하기</button>
            </div>
        </div>
    </div>
</div>

<div class="modal" tabindex="-1" id="insertBoardModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">글 쓰기</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">

                <div class="mb-3">
                    <label for="titleInsert" class="form-label">제목</label>
                    <input type="text" class="form-control" id="titleInsert">
                </div>
                <div class="mb-3">
                    <label for="contentInsert" class="form-label">내용</label>
                    <textarea class="form-control" id="contentInsert" rows="10"></textarea>
                </div>
                <button id="btnBoardInsert" class="btn btn-sm btn-primary btn-outline" data-bs-dismiss="modal" type="button">등록</button>

            </div>
        </div>
    </div>
</div>
<div class="modal" tabindex="-1" id="updateBoardModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">글 수정</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">

                <div class="mb-3">
                    <label for="titleUpdate" class="form-label">제목</label>
                    <input type="text" class="form-control" id="titleUpdate">
                </div>
                <div class="mb-3">
                    <label for="contentUpdate" class="form-label">내용</label>
                    <textarea class="form-control" id="contentUpdate" rows="10"></textarea>
                </div>
                <button id="btnBoardUpdate" class="btn btn-sm btn-primary" data-bs-dismiss="modal" type="button">수정</button>

            </div>
        </div>
    </div>
</div>

<script src = "/assets/js/utll.js"></script>
<script>
    // logut 처리 방식 구분
    // #1. Javascript 를 이용한 비동기 처리
    //     Logout 이 클릭되면 이벤트 핸들러에서 비동기로 logout 요청 ( 서버에서 로그아웃 처리 후 result:success 전달 )
    //     페이지를 window.location.href 로 login 페이지 이동
    // #2. a 의 href 에 page 요청 ( 서버에서 로그아웃 처리 후 바로 페이지를 이동 )
    let LIST_ROW_COUNT = 10;
    let OFFSET = 0;
    let SEARCH_WORD = '';
    let TOTAL_LIST_COUNT = 0;
    window.onload = function() {
        // 글 목록
        listBoard();

        // 검색어 처리
        document.querySelector("#btnSearchWord").onclick = function(){
           SEARCH_WORD = document.querySelector("#inputSearchWord").value;
           listBoard();
        }

        // 글 등록 모달
        document.querySelector("#btnInsertPage").onclick = function() {
            document.querySelector("#titleInsert").value ='';
            document.querySelector("#contentInsert").value ='';
            let modal = new bootstrap.Modal( document.querySelector("#insertBoardModal"));
            modal.show();
        }

        // 글 등록
        document.querySelector("#btnBoardInsert").onclick = function() {
            // validation
            if(document.querySelector("#titleInsert").value == '' || document.querySelector("#contentInsert").value == ''){
                alert("제목 또는 내용을 모두 입력하세요");
                return;
            }
            insertBoard();
        }

        // 글 수정 모달
        document.querySelector("#btnBoardUpdateForm").onclick = function() {
            // 상세 모달의 data-boardId 을 글 수정 모달의 data-boardId 로 복사
            let boardId = document.querySelector("#detailBoardModal").getAttribute("data-boardId");
            document.querySelector("#updateBoardModal").setAttribute("data-boardId",boardId);

            // 상세 글 제목, 내용을 수정 모달의 input의 value
            document.querySelector("#titleUpdate").value = document.querySelector("#titleDetail").innerHTML;
            document.querySelector("#contentUpdate").value = document.querySelector("#contentDetail").innerHTML;
            // 수정 모달 show,
            let modal = new bootstrap.Modal( document.querySelector("#updateBoardModal"));
            modal.show();
        }

        // 글 수정
        document.querySelector("#btnBoardUpdate").onclick = function() {
            // validation
            if(document.querySelector("#titleUpdate").value == '' || document.querySelector("#contentUpdate").value == ''){
                alert("제목 또는 내용을 모두 입력하세요");
                return;
            }
            updateBoard();
        }

        document.querySelector("#btnBoardDeleteConfirm").onclick = function() {
            // confirm
            if(confirm("이 글을 삭제할까요?")){
                deleteBoard();
            }
        }
    }
    async function listBoard(){
        let fetchOptions = {
            method: "GET",
            headers:{
                "ajax": "true"
            },
            credentials: "same-origin"
        }

        let url = "/boards/list";
        let urlParams = "?limit=" + LIST_ROW_COUNT + "&offset=" + OFFSET + "&searchWord=" + SEARCH_WORD;
        let response = await fetch(url+urlParams, fetchOptions);
        let data = await response.json();

        // console.log(data);

        if(data.result == "success"){
            makeListHtml(data.list);
            TOTAL_LIST_COUNT = data.count;
        }else if(data.result=="fail"){
            alert("글 불러오기 과정에서 오류 발생")
        }else if(data.result == "login"){
            window.location.href = "/pages/login";
        }
    }

    function makeListHtml(list){
        let listHTML = ``;

        list.forEach( el => {
            let boardId = el.boardId;
            let userName = el.username;
            let title = el.title;
            let content = el.content;
            let regDt = el.regDt;
            // LocalDateTime 객체 --> json 처리 결과물이 gson, jackson 2가지가 다르다.
//          console.log(regDt);
            let regDtStr = makeDateStr(regDt.date.year, regDt.date.month, regDt.date.day, '.'); // 2024.07.11
            let readCount = el.readCount;

            listHTML += `<tr style="cursor:pointer" data-boardId=\${boardId}>
                            <td>\${boardId}</td>
                            <td>\${title}</td>
                            <td>\${userName}</td>
                            <td>\${regDtStr}</td>
                            <td>\${readCount}</td>
                        </tr>`;
        });

        document.querySelector("#boardTbody").innerHTML = listHTML;

        document.querySelectorAll("#boardTbody tr").forEach( el => {
            el.onclick = function(){
                let boardId = this.getAttribute("data-boardId");
                detailBoard(boardId);
            }
        } );
    }

    async function detailBoard(boardId){
        let fetchOptions = {
            method: "GET",
            headers:{
                "ajax": "true"
            },
            credentials: "same-origin"
        }

        let url = "/boards/detail/" + boardId;
        let response = await fetch(url, fetchOptions);
        let data = await response.json();

        console.log(data);

        if(data.result == "success"){
            makeDetailHtml(data.dto)
        }else if(data.result=="fail"){
            alert("글 상세 과정에서 오류 발생")
        }else if(data.result == "login"){
            window.location.href = "/pages/login";
        }
    }

    function makeDetailHtml(dto){
        let regDt = dto.regDt;
        let regDtStr = makeDateStr(regDt.date.year, regDt.date.month, regDt.date.day, '.') + ' ' +
            makeTimeStr(regDt.time.hour,regDt.time.minute,regDt.time.second, ":" ) // 2024.07.11

        document.querySelector("#detailBoardModal").setAttribute("data-boardId", dto.boardId);
        document.querySelector("#boardIdDetail").innerHTML = "#" + dto.boardId;
        document.querySelector("#titleDetail").innerHTML = dto.title;
        document.querySelector("#contentDetail").innerHTML = dto.content;
        document.querySelector("#userNameDetail").innerHTML = dto.username;
        document.querySelector("#regDtDetail").innerHTML = regDtStr;
        document.querySelector("#readCountDetail").innerHTML = dto.readCount;

        if( dto.sameUser ){
            document.querySelector("#btnBoardUpdateForm").style.display = "inline-block";
            document.querySelector("#btnBoardDeleteConfirm").style.display = "inline-block";
        }else{
            document.querySelector("#btnBoardUpdateForm").style.display = "none";
            document.querySelector("#btnBoardDeleteConfirm").style.display = "none";
        }

        let modal = new bootstrap.Modal( document.querySelector("#detailBoardModal"));
        modal.show();
    }

    async function insertBoard(){
        let urlParams = new URLSearchParams({
            title : document.querySelector("#titleInsert").value,
            content: document.querySelector("#contentInsert").value
        });

        let fetchOptions = {
            method: "POST",
            headers:{
                "ajax": "true"
            },
            body: urlParams,
            credentials: "same-origin"
        }

        let url = "/boards/insert";
        let response = await fetch(url, fetchOptions);
        let data = await response.json();

        console.log(data);

        if(data.result == "success"){
            alertify.success('글이 등록되었습니다.');
            listBoard();
        }else if(data.result=="fail"){
            alertify.error("글 등록 과정에서 오류 발생")
        }else if(data.result == "login"){
            window.location.href = "/pages/login";
        }
    }

    async function updateBoard(){
        let boardId = document.querySelector("#updateBoardModal").getAttribute("data-boardId");

        let urlParams = new URLSearchParams({
            boardId: boardId,
            title : document.querySelector("#titleUpdate").value,
            content: document.querySelector("#contentUpdate").value
        });

        let fetchOptions = {
            method: "POST",
            headers:{
                "ajax": "true"
            },
            body: urlParams,
            credentials: "same-origin"
        }

        let url = "/boards/update";
        let response = await fetch(url, fetchOptions);
        let data = await response.json();

        console.log(data);

        if(data.result == "success"){
            alertify.success("글이 수정되었습니다.");
            listBoard();
        }else if(data.result=="fail"){
            alertify.error("글 수정 과정에서 오류 발생")
        }else if(data.result == "login"){
            window.location.href = "/pages/login";
        }
    }

    async function deleteBoard(){ // boardId 전송 필요 , 나머지 필요 X
        let boardId = document.querySelector("#detailBoardModal").getAttribute("data-boardId");

        let fetchOptions = {
            method: "GET",
            headers:{
                "ajax": "true"
            }
        }

        let url = "/boards/delete/" + boardId;
        let response = await fetch(url, fetchOptions);
        let data = await response.json();

        console.log(data);

        if(data.result == "success"){
            alertify.success("글이 삭제 되었습니다.");
            listBoard();
        }else if(data.result=="fail"){
            alertify.error("글 삭제 과정에서 오류 발생")
        }else if(data.result == "login"){
            window.location.href = "/pages/login";
        }
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
</body>
</html>