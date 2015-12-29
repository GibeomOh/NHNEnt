<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.board.BoardModel, java.util.List" %>
<%
	List<BoardModel> boardList = (List<BoardModel>)request.getAttribute("boardList");
	BoardModel boardModel = (BoardModel)request.getAttribute("boardModel");
	int totalCount = (Integer)request.getAttribute("totalCount");
	String pageNavigator = (String)request.getAttribute("pageNavigator");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<!-- XHTML 네임스페이스: 작성된 페이지가 XHTML로 작성되었음을 알림 -->
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>게시판 목록</title>
<style type="text/css">
	* {font-size: 9pt;}
	p {width: 600px; text-align: right;}
	table thead tr th {background-color: gray;}
</style>
<script type="text/javascript">
	function goUrl(url) {
		location.href=url;
	}
</script>
<%

%>
</head>
<body>
	<table border="1" summary="게시판 목록">
		<caption>게시판 목록</caption>
		<colgroup>
			<col width="50" />
			<col width="300" />
			<col width="80" />
			<col width="100" />
			<col width="70" />
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록 일시</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<%
			if (totalCount == 0) {
			%>
			<tr>
				<td align="center" colspan="5">등록된 게시물이 없습니다.</td>
			</tr>
			<%
			} else {
				for (int i=0, size=boardList.size(); i<size; i++) {
					BoardModel board = boardList.get(i);
			%>
			<tr>
				<td align="center"><%=totalCount %></td>
				<td><a href="boardViewServlet?num=<%=board.getNum()%>"><%=board.getSubject()%></a></td>
				<td align="center"><%=board.getEmail()%></td>
				<td align="center"><%=board.getRegDate().substring(0, 10) %></td>
				<td align="center"><%=board.getHit()%></td>
			</tr>
			<%
				}
			}
			%>
		</tbody>
		<tfoot>
			<tr>
				<td align="center" colspan="5"><%=pageNavigator%></td>
			</tr>
		</tfoot>
	</table>
	<p>
		<input type="button" value="목록" onclick="goUrl('<%=request.getContextPath()%>/board/boardListServlet');" />
		<input type="button" value="글쓰기" onclick="goUrl('<%=request.getContextPath()%>/board/boardWriteServlet');" />
	</p>
</body>
</html>