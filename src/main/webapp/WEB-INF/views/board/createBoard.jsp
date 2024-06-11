<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/standard/header.jsp"/>
<jsp:include page="/WEB-INF/views/standard/aside.jsp"/>
<body>
<div id="Frame_Set">
<form id="Create_Read_Update_Form" action="/board/createBoard" method="post">
	<table>
		<tr>
			<td colspan="1">Title</td>
			<td colspan="4"><input name="boardTitle" onkeyup="limiteBoardTitle(this)"></td>
		</tr>
		<tr>
			<td colspan="3"></td>
			<td colspan="1">Writer</td>
			<td colspan="1"><input name="boardWriter" readonly="readonly" value="${loginUser.userId}"></td>
		</tr>
		<tr>
			<td colspan="1">Content</td>
			<td colspan="4"><textarea name="boardContent" cols="30" rows="60" onkeyup="limiteBoardContent(this)"></textarea></td>
		</tr>
		<tr>
			<td colspan="5"><button type="submit" onclick="createCheck()">登録</button></td>
		</tr>
	</table>
</form>
</div>
</body>
<jsp:include page="/WEB-INF/views/standard/footer.jsp"/>
<script type="text/javascript">
document.getElementsByTagName('header')[0].style.display='none';
document.getElementsByTagName('aside')[0].style.display='none';
document.getElementsByTagName('body')[0].style.width='100%';
document.getElementsByTagName('footer')[0].style.display='none';
</script>