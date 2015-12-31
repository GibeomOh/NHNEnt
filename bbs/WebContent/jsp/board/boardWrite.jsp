<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 작성</title>
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
	// 등록 폼 체크
	function boardWriteCheck() {
		var form = document.boardWriteForm;
		var mailexp = /[a-z0-9]{2,}@[a-z0-9-]{2,}\.[a-z0-9]{2,}/i; 
		
		if (form.subject.value == '') {
			alert('제목을 입력하세요.');
			form.subject.focus();
			return false;
		}
		if(!mailexp.test(form.email.value)) {
			alert("이메일을 형식을 확인하세요");
			form.email.focus();
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
		return true;
	}
</script>
</head>

<body>
	<form name="boardWriteForm" action="<%=request.getContextPath()%>/board/BoardWriteServlet" method="post" onsubmit="return boardWriteCheck();">
	<table border="1" summary="글 작성">
		<caption>글 작성</caption>
		<colgroup>
			<col width="100" />
			<col width="500" />
		</colgroup>
		<tbody>
			<tr>
				<th align="center">제목</th>
				<td><input type="text" name="subject" size="80" maxlength="100" /></td>
			</tr>
			<tr>
				<th align="center">이메일</th>
				<td><input type="text" name="email" maxlength="20" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<textarea name="contents" rows="10" cols="100" ></textarea>
				</td>
			</tr>
			<tr>
				<th align="center">비밀번호</th>
				<td><input type="password" name="password" maxlength="20" /></td>
			</tr>
		</tbody>
	</table>
	<p>
		<input type="button" value="목록" onclick="goUrl('<%=request.getContextPath()%>/board/BoardListServlet');" />
		<input type="submit" value="글쓰기" />
	</p>
	</form>
</body>
</html>