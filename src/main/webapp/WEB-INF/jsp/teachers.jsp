<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Προβολή Καθηγητών</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/teachers.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<%@ include file="header.jsp"%>
<div class="main-content">

    <div class="m-bottom">
        <a href="${pageContext.request.contextPath}/teachers/insert">Εισαγωγή Καθηγητή</a>
    </div>

    <div class="form">
        <form id="filterForm" method="GET" action="${pageContext.request.contextPath}/teachers">
            <%--        <input type="text" name="filterId" placeholder="Enter ID" />--%>
            <input type="text" name="filterFirstname" placeholder="Όνομα" />
            <input type="text" name="filterLastname" placeholder="Επώνυμο" />
            <input class="custom-button-green" id="submitBtn" type="submit" value="Αναζήτηση" />
            <button class="reset-button" id="resetBtn" type="button" onclick="reset()">Επαναφορά</button>
        </form>
    </div>

    <table class="m-bottom">
        <thead>
            <tr>
                <th>Κωδικός</th><th>Όνομα</th><th>Επώνυμο</th><th>Διαγραφή</th><th>Ενημέρωση</th><th>Προβολή</th>
            </tr>
        </thead>
<%--        <p>${requestScope.message}</p>--%>
        <c:forEach var = "teacher" items = "${requestScope.teachers}">
            <tr>
                <td>${teacher.id}</td>
                <td>${teacher.firstname}</td>
                <td>${teacher.lastname}</td>
                <td><a href="${pageContext.request.contextPath}/teachers/delete?id=${teacher.id}"
                       onclick="return confirm('Are you sure you want to delete the teacher?')"><i class="fa-solid fa-trash-can"></i></a></td>
                <td><a href="${pageContext.request.contextPath}/teachers/update?id=${teacher.id}&firstname=${teacher.firstname}&lastname=${teacher.lastname}"><i class="fa-solid fa-pen-to-square"></i></a></td>
                <td><a href=""><i class="fa-regular fa-eye"></i></a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<div>
<%--    <c:if test="${requestScope.deleteAPIError}">--%>
        <p>${requestScope.message}</p>
<%--    </c:if>--%>
</div>

<div>
    <c:if test="${requestScope.updateAPIError}">
        <p>Something went wrong in Update</p>
    </c:if>
</div>

<%@ include file="footer.jsp"%>

<script src="${pageContext.request.contextPath}/js/teachers.js">

</script>
</body>
</html>
