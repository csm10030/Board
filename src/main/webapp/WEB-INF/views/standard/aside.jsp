<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<aside style="display: none;">
<input id="Login_User" value="${loginUser}" hidden="hidden">
<ul>
	<li>
		<a id="Login_Button" href="/board/login">Login</a>
		<a id="Logout_Button" href="/board/logout">Logout</a>
		<a id="Join_User_Button" href="/board/join">Join</a>
		<a id="Close_Aside_Button" onclick="closeAside()">X</a>
	</li>
	<li>
		<a href="/board">Main</a>
	</li>
	<li>
		<a href="/board/listBoard">FreeBoard</a>
	</li>
	<li>
		<a href="/board/projects">Projects</a>
	</li>
	<li>
		<a href="/board/history">History</a>
	</li>
	<li>
		<a href="/board/etc">etc.</a>
	</li>
</ul>
</aside>