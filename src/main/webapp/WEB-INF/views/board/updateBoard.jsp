<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/standard/header.jsp"/>
<jsp:include page="/WEB-INF/views/standard/aside.jsp"/>
<div id="Frame_Set">
<form id="Create_Read_Update_Form" action="/board/updateBoard" method="post">
<input name="boardNo" hidden="hidden" value="${updateBoard.boardNo}">
	<table>
		<tr>
			<td colspan="1">Title</td>
			<td colspan="4"><input name="boardTitle" onkeyup="limiteBoardTitle(this)" value="${updateBoard.boardTitle}"></td>
		</tr>
		<tr>
			<td colspan="3"></td>
			<td colspan="1">Writer</td>
			<td colspan="1"><input name="boardWriter" readonly="readonly" value="${updateBoard.boardWriter}"></td>
		</tr>
		<tr>
			<td colspan="1">Content</td>
			<td colspan="4"><textarea name="boardContent" cols="30" rows="60" onkeyup="limiteBoardContent(this)">${updateBoard.boardContent}</textarea></td>
		</tr>
	</table>
	<button type="submit" onclick="createCheck()">完了</button>
</form>
</div>
</body>
<jsp:include page="/WEB-INF/views/standard/footer.jsp"/>