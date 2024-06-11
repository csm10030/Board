<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/standard/header.jsp"/>
<jsp:include page="/WEB-INF/views/standard/aside.jsp"/>
<body>
<div id="Frame_Set">
<form id="Create_Read_Update_Form" action="/board/updateBoard" method="get">
	<input name="boardNo" hidden="hidden" value="${readBoard.boardNo}">
	<table border="1">
		<tr>
			<td>Title</td><td>${readBoard.boardTitle}</td>
		</tr>
		<tr>
			<td>Writer</td><td><input id="Board_Writer" hidden="hidden" value="${readBoard.boardWriter}"> ${readBoard.boardWriter}</td>
		</tr>
		<tr>
			<td colspan="2">Content</td>
		</tr>
		<tr>
			<td id="Board_Content" colspan="2" style="height: 180px;">${readBoard.boardContent}</td>
		</tr>
	</table>
	<jsp:include page="/WEB-INF/views/board/listComment.jsp"/>
	<button id="Update_Board_Button" type="submit">修正</button>
</form>
<form id="Delete_Board_Form" action="deleteBoard" method="post">
	<input name="boardNo" hidden="hidden" value="${readBoard.boardNo}">
	<button id="Delete_Board_Button" type="submit" onclick="return deleteCheck()">削除</button>
</form>
</div>
</body>
<script type="text/javascript">
let viewPage = 'readBoard';
let boardNo = document.getElementsByName('boardNo')[0].value;
let boardWriter = document.getElementById('Board_Writer').value;
loginUser = document.getElementById('Login_User').value;
boardContent = document.getElementById('Board_Content');

if(loginUser == '' 
	|| !(loginUser.split(',')[1].split('=')[1] == boardWriter)){
	document.getElementById('Update_Board_Button').style.display = 'none';
	document.getElementById('Delete_Board_Button').style.display = 'none';
}

if(boardContent.textContent.length > 50)
	boardContent.innerHTML = boardContent.textContent.substring(0, 50) + '<br>' + boardContent.textContent.substring(51, 100);
</script>
<jsp:include page="/WEB-INF/views/standard/footer.jsp"/>