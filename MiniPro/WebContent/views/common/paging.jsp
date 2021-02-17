<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="center">
	<div class="pagination">
    <a href="javascript:goPage(${params.firstPageNo})" class="first">처음 페이지</a>
    <a href="javascript:goPage(${params.prevPageNo})" class="prev">이전 페이지</a>
    <span>
        <c:forEach var="i" begin="${params.startPageNo}" end="${params.endPageNo}" step="1">
            <c:choose>
                <c:when test="${i eq params.pageNo}"><a href="javascript:goPage(${i})" class="active">${i}</a></c:when>
                <c:otherwise><a href="javascript:goPage(${i})">${i}</a></c:otherwise>
            </c:choose>
        </c:forEach>
    </span>
    <a href="javascript:goPage(${params.nextPageNo})" class="next">다음 페이지</a>
    <a href="javascript:goPage(${params.finalPageNo})" class="last">마지막 페이지</a>
	</div>
</div>