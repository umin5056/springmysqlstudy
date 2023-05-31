<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	function fnList() {
		location.href='${contextPath}/upload/list.do';
	}
	
	function fnFileCheck(vThis) {
		// 최대 크기 10MB
		let maxSize = 1024 * 1024 * 10;
		
		// 첨부된 파일 목록
		let files = vThis.files;
		
		// 첨부된 파일 순회(크키 체크, 첨부된 파일명 표시)
		$('#fileList').empty();
		$.each(files, function(i, file){
			
			// 크기 체크
			if(file.size > maxSize) {
				alert('각 첨부파일의 최대 크기는 10MB입니다.');
				$(this).val('');
				return;
			}
			
			// 첨부된 파일명 표시
			$('#fileList').append('<div>' + file.name);
			
		})
	}
	
	function fnRemoveAttach(attachNo, uploadNo) {
		location.href="${contextPath}/upload/deleteAttach.do?attachNo=" + attachNo + "&uploadNo=" + uploadNo;
	}

</script>
</head>
<body>

	<div>
		<h1>UPLOAD 게시글 편집하기</h1>
		<form method="post" enctype="multipart/form-data" action="${contextPath}/upload/updateUpload.do">
			<div>
				<label for="uploadTitle">제목</label> 
				<input id="uploadTitle" name="uploadTitle" value="${upload.uploadTitle}">
			</div>
			<div>
				<label for="uploadContent">내용</label><br>
				<textarea id="uploadContent" name="uploadContent" rows="5" cols="30">${upload.uploadContent}</textarea>
			</div>
			
			<hr>
			
			<div>
				<h4>첨부 목록</h4>
				<div>
					<label for="files">첨부</label>
					<input type="file" id="files" name="files" multiple onchange="fnFileCheck(this)"> <!-- multiple : 다중첨부 가능 -->
				</div>
				
				<c:if test="${empty attachList}">
					<div>첨부된 파일이 없습니다.</div>
				</c:if>
				
				<c:if test="${not empty attachList}">
					<div>기존 첨부된 파일 목록</div>
					<c:forEach items="${attachList}" var="attach">
						<div>
							<a href='${contextPath}/upload/download.do?attachNo=${attach.attachNo}'>
								<c:if test="${attach.hasThumbnail == 1}">
									<img src="${contextPath}/upload/display.do?attachNo=${attach.attachNo}">
								</c:if>
								<c:if test="${attach.hasThumbnail == 0}">
									<img src="${contextPath}/resources/images/attach1.png" width="50px">
								</c:if>
								${attach.originName}
							</a>
							<input type="button" value="삭제"	 onclick="fnRemoveAttach(${attach.attachNo}, ${upload.uploadNo})">
						</div>
					</c:forEach>
				</c:if>
				<hr>
				<div>추가 첨부 목록</div>
				<div id="fileList"></div>
			</div>
			
			<div>
				<input type="hidden" name="uploadNo" value="${upload.uploadNo}">
				<button>수정완료</button>
				<input type="button" value="목록" onclick="fnList()">
			</div>
		</form>
	</div>
	
	
</body>
</html>