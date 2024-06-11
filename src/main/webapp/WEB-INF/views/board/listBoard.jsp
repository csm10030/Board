<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/standard/header.jsp"/>
<jsp:include page="/WEB-INF/views/standard/aside.jsp"/>
<body>
<div id="Frame_Set">
	<table border="1">
	<thead>
	<tr>
		<th>NO</th>
		<th>TITLE</th>
		<th>WRITER</th>
	</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty boardList}">
				<c:forEach var="list" items="${boardList}" varStatus="content">
					<c:if test="${(paging.pageNo-1)*paging.pageSize <= content.index and content.index < paging.pageNo*paging.pageSize}">
						<tr class="boardList">
							<td>${list.boardNo}</td>
							<td>
								<form action="/board/readBoard" method="get">
									<input name="boardNo" hidden="hidden" value="${list.boardNo}">
									<button id="Read_Button" type="submit" onclick="return rollCheck('${loginUser.userRl}')">${list.boardTitle}</button>
								</form>
							</td>
							<td>${list.boardWriter}</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="3" style="text-align: center;">まだ何も作成されてないです。</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	</table>
	<c:if test="${not empty boardList}">
		<jsp:include page="/WEB-INF/views/standard/paging.jsp" flush="true">
		    <jsp:param name="firstPageNo" value="${paging.firstPageNo}" />
		    <jsp:param name="prevPageNo" value="${paging.prevPageNo}" />
		    <jsp:param name="startPageNo" value="${paging.startPageNo}" />
		    <jsp:param name="pageNo" value="${paging.pageNo}" />
		    <jsp:param name="endPageNo" value="${paging.endPageNo}" />
		    <jsp:param name="nextPageNo" value="${paging.nextPageNo}" />
		    <jsp:param name="finalPageNo" value="${paging.finalPageNo}" />
		</jsp:include>
	</c:if>
	<button id="Create_Board_Button" onclick="window.open('/board/createBoard?page=newTab', null, 'heigth=800px, width=1200px')">登録</button>
</div>
</body>
<script type="text/javascript">
let viewPage = 'listBoard';
var newTab='<c:out value="${newTab}"/>';
if(newTab != undefined){
	opener.document.location.href='/board/listBoard';
	self.close();
}
if(document.getElementById('Login_User').value == ''){
	document.getElementById('Create_Board_Button').style.display = 'none';
}
</script>
<jsp:include page="/WEB-INF/views/standard/footer.jsp"/>