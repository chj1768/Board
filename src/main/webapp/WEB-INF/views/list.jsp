<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <title>게시판 목록보기</title>
 </head>
 <body>
	<table cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td width="73">번호</td>
			<td width="379">제목</td>
			<td width="73">작성자</td>
			<td width="164">작성일</td>
			<td width="58">조회수</td>
		</tr>
		<c:forEach items="${list}" var="dto">
			<tr height="1">
			  	<td>${ dto.bId }</td>
			  	<td>
				  	<c:forEach begin="1" end="${ dto.bIndent }">-</c:forEach>
				  	<a href="content_view?bId=${ dto.bId }">${ dto.bTitle }</a>
			  	</td>
			  	<td>${ dto.bName}</td>
			  	<td>${ dto.bDate }</td>
			  	<td>${ dto.bHit }</td>
		  	</tr>
		</c:forEach>
		<tr align="center">
	   		<a href="write_view">글작성</a>
	  	</tr>
	 </table>
	 
</body> 
</html>
