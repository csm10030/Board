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
					<td colspan="4">会員登録</td>
				</tr>
				<tr>
					<td>メールアドレス</td>
					<td colspan="3">
						<input name="userId" onkeyup="lengthCheck(this, 1)" placeholder="例）example@gmail.com">
					</td>
				</tr>
				<tr>
					<td>パスワード</td>
					<td colspan="3"><input name="userPw" type="password" onkeyup="lengthCheck(this, 2)" placeholder="例）英語と特殊文字を含めて作成してください。"></td>
				</tr>
				<tr>
					<td id="viewError" colspan="4"></td>
				</tr>
				<tr>
					<td colspan="4"><button type="button" onclick="join()">登録</button></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
<jsp:include page="/WEB-INF/views/standard/footer.jsp"/>
<script type="text/javascript">
function join() {
	let form = document.getElementById('Login_Join_Form');
	let userId = document.getElementsByName('userId')[0];
	let userPw = document.getElementsByName('userPw')[0];
	let viewError = document.getElementById('viewError');
	viewError.style.color = 'red';
	let chkJoin = 'n';
	let rt_result = 'n';
	let blankId = '<spring:message code="userVO.userId.blank"/>';
	let blankPw = '<spring:message code="userVO.userPw.blank"/>';
	document.getElementsByClassName('blankId')[0].style.display = 'none';
	document.getElementsByClassName('mailId')[0].style.display = 'none';
	document.getElementsByClassName('blankPw')[0].style.display = 'none';
	
	$.ajax({
		type : 'POST'
		, url : '/board/join'
		, data : 'userId=' + userId.value + '&userPw=' + userPw.value
		, async : false
		, success : 
			function(data) {
				if(data == 'verifyError') {
					chkJoin = 'v';
				} else if (data == 'idError') {
					chkJoin = 'i';
				} else if(data == 'success') {
					chkJoin = 'y';
				} else if(data == 'pwError') {
					chkLogin = 'p';
				}else {
					chkJoin = 'n';
				}
			}
		, error : 
			function(request, status, error) {
				chkJoin = 'n';
				alert('code:' + request.status + '\n' + 'error:' + error);
			}
	})
	joinCheck();
	if (chkJoin == 'i') {
		document.getElementsByClassName('blankId')[0].style.display = 'block';
	} else if(chkLogin == 'p') {
		document.getElementsByClassName('blankPw')[0].style.display = 'block';
	} else if(chkJoin == 'v') {
		viewError.textContent = '既に登録されているメールアドレスです。';
	} else if(chkJoin == 'y') {
		rt_result = 'y';
	} else {
		viewError.textContent = '予期せぬエラーが発生しました。';
	}
	
	if(rt_result=='y') {
		form.action = '/board/index';
		form.method = 'get';
		form.submit();
	} else {
		return false;
	}
};
</script>