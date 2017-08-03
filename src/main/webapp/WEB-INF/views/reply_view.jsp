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
		<form action="reply" method="post">
			<input type="hidden" name="bId" value="${ reply_view.bId }">
			<input type="hidden" name="bGroup" value="${ reply_view.bGroup }">
			<input type="hidden" name="bStep" value="${ reply_view.bStep }">
			<input type="hidden" name="bIndent" value="${ reply_view.bIndent }">
			<tr>
				<td> 글번호 </td>
				<td> ${ reply_view.bId } </td>
			</tr>
			<tr>
				<td> 이름 </td>
				<td><input type="text" name="bName" value="${ reply_view.bName }"> </td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td> <input type="text" name="bTitle" value="${ reply_view.bTitle }"> </td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td> <input type="text" name="bContent" value="${ reply_view.bContent }"> </td>
			</tr>
			<tr>
				<td> 조회수 </td>
				<td> ${ reply_view.bHit } </td>
			</tr>
			<tr>
				<td> 
					<input type="submit" value="답변하기"  /> 
					<a href="list">목록보기</a> 
				</td>
			</tr>
		</form>
	</table>
</body> 
</html>