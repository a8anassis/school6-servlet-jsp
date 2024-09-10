<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Εισαγωγή Καθηγητή</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/teacher-insert.css">
</head>
<body>
<%@ include file="header.jsp"%>
<div class="main-content">

    <div class="form m-bottom">
        <form method="POST" action="${pageContext.request.contextPath}/teachers/insert">
            <div class="row m-bottom">
                <input class="m-bottom" type="text" name="firstname" value="${requestScope.insertDTO.firstname}" required placeholder="Όνομα">
                <p class="validation-error">${requestScope.firstnameMessage}</p>
            </div>
            <div class="row m-bottom">
                <input class="m-bottom" type="text" name="lastname" value="${requestScope.insertDTO.lastname}" required placeholder="Επώνυμο">
                <p class="validation-error">${requestScope.lastnameMessage}</p>
            </div>
            <div class="row">
                <button type="submit">Εισαγωγή</button>
            </div>
        </form>
    </div>

    <div class="m-bottom">
        <a href="${pageContext.request.contextPath}/teachers">Επιστροφή</a>
    </div>

    <div>
        ${requestScope.errorMessage}
    </div>
</div>

<%@ include file="footer.jsp"%>
</body>
</html>
