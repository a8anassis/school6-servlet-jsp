<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Teacher Update</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/teacher-update.css">
</head>
<body>
<%@ include file="header.jsp"%>
<div class="main-content">
	<div class="form m-bottom">
		<form method="POST" action="${pageContext.request.contextPath}/teachers/update">
			<div class="row m-bottom">
				<label for="tid">Κωδικός</label>
				<input id="tid" type="text" name="id" value="${requestScope.updateDTO.id}" readonly>
				<p class="validation-error"></p>
			</div>

			<div class="row m-bottom">
				<label for="firstname">Όνομα</label>
				<input id="firstname" type="text" name="firstname" value="${requestScope.updateDTO.firstname}">
				<p class="validation-error">${requestScope.firstnameMessage}</p>
			</div>

			<div class="row m-bottom">
				<label for="lastname">Επώνυμο</label>
				<input id="lastname" type="text" name="lastname" value="${requestScope.updateDTO.lastname}">
				<p class="validation-error">${requestScope.lastnameMessage}</p>
			</div>

			<div>
				<button type="submit">Ενημέρωση</button>
			</div>

		</form>	
	</div>	

<%--	<c:if test="${requestScope.isError}">--%>
		<p>${requestScope.message}</p>
<%--	</c:if>--%>
</div>
</body>
</html>
