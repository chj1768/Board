<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <title>작성글 보기</title>
 </head>
 <body>
	<table width="100%" cellpadding="0" cellspacing="0" border="1">
		<form action="modify" method="post">
			<input type="hidden" name="bId" value="${ content_view.bId }">
			<tr>
				<td> 글번호 </td>
				<td> ${ content_view.bId } </td>
			</tr>
			<tr>
				<td> 이름 </td>
				<td><input type="text" name="bName" value="${ content_view.bName }"> </td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td> <input type="text" name="bTitle" value="${ content_view.bTitle }"> </td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td> <input type="text" name="bContent" value="${ content_view.bContent }"> </td>
			</tr>
			<tr>
				<td> 조회수 </td>
				<td> ${ content_view.bHit } </td>
			</tr>
			<tr>
				<td> 
					<input type="submit" value="수정하기"  /> 
					<a href="list">목록보기</a> 
					<a href="delete?bId=${ content_view.bId }">삭제</a>
					<a href="reply_view?bId=${ content_view.bId }">답변</a>
				</td>
			</tr>
		</form>
	</table>
</body> 
</html>