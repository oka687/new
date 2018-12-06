<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>Insert title here</title>
</head>
<body>
	<h1>게시물 상세정보</h1>
	<form action="update" method="post">
		<table border='1'>
			<tr>
				<th>번호</th>
				<td><input type="text" readonly value="${board.no }"></input></td>

				<th>내용</th>
				<td><input type="text" value="${board.contents }"></input></td>

				<th>작성일</th>
				<td><input type="text" readonly value="${board.createdDate }"></input></td>

				<th>조회수</th>
				<td><input type="text" readonly value="${board.viewCount }"></input></td>
			</tr>

			<th>작성자</th>
			<td><input type="text" readonly value="${board.writerNo }"></input></td>
			</tr>

			<th>작성자</th>
			<td><input type="text" readonly value="${board.lessonNo }"></input></td>
			</tr>

			</form>
		</table>
</body>
</html>