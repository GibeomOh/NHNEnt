<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.board.BoardModel" %>
<%
	BoardModel boardModel = (BoardModel)request.getAttribute("boardModel");
	String currPassword = boardModel.getPassword();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<title>글 수정</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
<style type="text/css">
	* {font-size: 9pt;}
	p {width: 600px; text-align: right;}
	table tbody tr th {background-color: lightgray;}
</style>
<script type="text/javascript">
	function goUrl(url) {
		location.href=url;
	}
	// 수정 폼 체크
	function boardModifyCheck() {
		var form = document.boardModifyForm;
		if (form.subject.value == '') {
			alert('제목을 입력하세요.');
			form.subject.focus();
			return false;
		}
		if (form.contents.value == '') {
			alert('내용을 입력하세요');
			form.content.focus();
			return false;
		}
		if (form.password.value == '') {
			alert('비밀번호를 입력하세요');
			form.password.focus();
			return false;
		} 
		else if(form.password.value != <%=currPassword %>) {
			alert('비밀번호를 확인하세요');
			form.password.focus();
			return false;
		} 
		return true;
	}
</script>

<body>
	<form name="boardModifyForm" action="<%=request.getContextPath() %>/board/BoardModifyServlet" method="post" onsubmit="return boardModifyCheck();">
	<input type="hidden" name="num" value="<%=boardModel.getNum()%>" />
	<table border="1" summary="글 수정">
		<caption>글 수정</caption>
		<colgroup>
			<col width="100" />
			<col width="500" />
		</colgroup>
		<tbody>
			<tr>
				<th align="center">제목</th>
				<td><input type="text" name="subject" size="80" maxlength="100" value="<%=boardModel.getSubject()%>" /></td>
			</tr>
			<tr>
				<th align="center">작성자</th>
				<td><%=boardModel.getWriter()%></td>
			</tr>
			<tr>
				<th align="center">이메일</th>
				<td><%=boardModel.getEmail()%></td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea name="contents" cols="80" rows="10"><%=boardModel.getContents()%></textarea>
				</td>
			</tr>
			<tr>
				<th align="center">비밀번호</th>
				<td><input type="text" name="password" maxlength="20" /></td>
			</tr>
		</tbody>
	</table>
	<p>
		<input type="button" value="목록" onclick="goUrl('<%=request.getContextPath()%>/board/BoardListServlet');" />
		<input type="submit" value="글수정" />
	</p>
	</form>
</body>
</html>