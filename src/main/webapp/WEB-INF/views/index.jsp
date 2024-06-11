<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/standard/header.jsp"/>
<jsp:include page="/WEB-INF/views/standard/aside.jsp"/>
<body>
<div id="Frame_Set">
<input id="Error_Message" value="${errorMessage}" hidden="hidden">
<c:choose>
	<c:when test="${loginUser.userRl > 1}">
		<table border="1" style="text-align: center; table-layout: fixed;">
		<thead>
			<tr>
				<th>NO</th>
				<th>ID</th>
				<th>PW</th>
				<th>ROLL</th>
				<th>MCOUNT</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${userList}">
			<tr>
				<td>${list.userNo}</td>
				<td>${list.userId}</td>
				<td style="overflow: hidden;">${list.userPw}</td>
				<td>${list.userRl}</td>
				<td>${list.userMc}</td>
			</tr>
			</c:forEach>
		</tbody>
		</table>
	</c:when>
	<c:otherwise>
		<div style="height: 100%; width: 100%; text-align: center; font-size: 2.4em;">
			Spring Boardへようこそ。
		</div>
	</c:otherwise>
</c:choose>
</div>
</body>
<script type="text/javascript">
	let errorMessage = document.getElementById('Error_Message');
	if(errorMessage.value != '' && errorMessage.value != null){
		alert(errorMessage.value);
	}
</script>
<jsp:include page="/WEB-INF/views/standard/footer.jsp"/>