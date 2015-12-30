<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.board.BoardModel" %>
<%
	BoardModel boardModel = (BoardModel)request.getAttribute("boardModel");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>게시판 상세보기</title>
<style type="text/css">
	* {font-size: 9pt;}
	.btn_align {width: 600px; text-align: right;}
	table tbody tr th {background-color: gray;}
</style>
<script type="text/javascript">
	function goUrl(url) {
		location.href=url;
	}
	// 삭제 체크
	function deleteCheck(url) {
		if (confirm('정말 삭제하시겠어요?')) {
			location.href=url;
		}
	}
</script>

<body>
	<table border="1" summary="게시판 상세조회">
		<caption>게시판 상세조회</caption>
		<colgroup>
			<col width="100" />
			<col width="400" />
		</colgroup>
		<tbody>
			<tr>
				<th align="center">제목</th>
				<td colspan="3"><%=boardModel.getSubject()%></td>
			</tr>
			<tr>
				<th align="center">작성자</th>
				<td><%=boardModel.getWriter()%></td>
				<th align="center">조회수</th>
				<td width="60"><%=boardModel.getHit()%></td>
			</tr>
			<tr>
				<th align="center">이메일</th>
				<td colspan="3"><%=boardModel.getEmail()%></td>
			</tr>
			<tr>
				<th align="center">등록 일시</th>
				<td colspan="3"><%=boardModel.getRegDate()%></td>
			</tr>
			<tr>
				<td colspan="4"><%=boardModel.getContents()%></td>
			</tr>
		</tbody>
	</table>
	<p class="btn_align">
		<input type="button" value="목록" onclick="goUrl('<%=request.getContextPath()%>/board/BoardListServlet');" />
		<input type="button" value="수정" onclick="goUrl('<%=request.getContextPath()%>/board/BoardModifyServlet?num=<%=boardModel.getNum()%>');" />
		<input type="button" value="삭제" onclick="deleteCheck('<%=request.getContextPath()%>/board/BoardDeleteServlet?num=<%=boardModel.getNum()%>');" />
	</p>
</body>
</html>