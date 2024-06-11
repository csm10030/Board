<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/standard/header.jsp"/>
<jsp:include page="/WEB-INF/views/standard/aside.jsp"/>
<body>
<div id="Frame_Set">
	<span class="blankId error" style="display: none;"><spring:message code="userVO.userId.blank"/></span>
	<span class="mailId error" style="display: none;"><spring:message code="userVO.userId.email"/></span>
	<span class="blankPw error" style="display: none;"><spring:message code="userVO.userPw.blank"/></span>
	<form:form id="Login_Join_Form">
		<table>
			<tr>
				<td colspan="4">ログイン</td>
			</tr>
			<tr>
				<td>メールアドレス</td>
				<td colspan="3"><input name="userId" onkeyup="lengthCheck(this, 1)"></td>
			</tr>
			<tr>
				<td>パスワード</td>
				<td colspan="3"><input name="userPw" type="password" onkeyup="lengthCheck(this, 2)" ></td>
			</tr>
			<tr>
				<td id="viewError" colspan="4"></td>
			</tr>
			<tr>
				<td colspan="4"><button type="button" onclick="login()">ログイン</button></td>
			</tr>
		</table>
	</form:form>
</div>
</body>
<jsp:include page="/WEB-INF/views/standard/footer.jsp"/>
<script type="text/javascript">
function login() {
	let form = document.getElementById('Login_Join_Form');
	let userId = document.getElementsByName('userId')[0];
	let userPw = document.getElementsByName('userPw')[0];
	let viewError = document.getElementById('viewError');
	viewError.style.color = 'red';
	let chkLogin = 'n';
	let rt_result = 'n';
	document.getElementsByClassName('blankId')[0].style.display = 'none';
	document.getElementsByClassName('blankPw')[0].style.display = 'none';
	
	$.ajax({
		type : 'POST'
		, url : '/board/login'
		, data : 'userId=' + userId.value + '&userPw=' + userPw.value
		, async : false
		, success : 
			function(data) {
				if (data == 'idError') {
					 chkLogin = 'i';
				} else if(data == 'verifyError') {
					chkLogin = 'v';
				} else if(data == 'pwError') {
					chkLogin = 'p';
				} else if(data == 'success') {
					chkLogin = 'y';
				} else {
					chkLogin = 'n';
				}
			}
		, error : 
			function(request, status, error) {
				chkLogin = 'n';
				alert('code:' + request.status + '\n' + 'error:' + error);
			}
	})
	loginCheck();
	if (chkLogin == 'i') {
		document.getElementsByClassName('blankId')[0].style.display = 'block';
	} else if(chkLogin == 'v') {
		viewError.textContent = 'メール認証を行ってください。';
	} else if(chkLogin == 'p') {
		document.getElementsByClassName('blankPw')[0].style.display = 'block';
	} else if(chkLogin == 'y') {
		rt_result = 'y';
	} else {
		viewError.textContent = '予期せぬエラーが発生しました。';
	}
	
	if(rt_result=='y') {
		form.action = '/board/signin';
		form.method = 'post';
		form.submit();
	} else {
		return false;
	}
};
</script>