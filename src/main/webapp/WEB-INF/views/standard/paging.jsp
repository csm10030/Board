<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<div class="paginate" style="margin: 0px; padding-top: 12px; box-sizing: border-box; height: 12px; width: 100%;">
    <a href="javascript:goPage(${paging.firstPageNo})" class="first">first</a>
    <a href="javascript:goPage(${paging.prevPageNo})" class="prev">prev</a>
    <span>
        <c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
            <c:choose>
                <c:when test="${i eq paging.pageNo}"><a href="javascript:goPage(${i})" class="choice">${i}</a></c:when>
                <c:otherwise><a href="javascript:goPage(${i})">${i}</a></c:otherwise>
            </c:choose>
        </c:forEach>
    </span>
    <a href="javascript:goPage(${paging.nextPageNo})" class="next">next</a>
    <a href="javascript:goPage(${paging.finalPageNo})" class="last">last</a>
</div>
<script type="text/javascript">
function goPage(x){
	if(viewPage == 'listBoard'){
		boardNo = null;
		location.href='/board/' + viewPage + '?pageNo=' + x;
	}
	else if(viewPage == 'readBoard')
		location.href='/board/' + viewPage +'?pageNo=' + x + '&boardNo=' + boardNo;
}
</script>
</html>