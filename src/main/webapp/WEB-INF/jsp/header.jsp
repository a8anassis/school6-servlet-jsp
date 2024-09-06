<%@ page import="gr.aueb.cf.schoolapp.dao.ITeacherDAO" %>
<%@ page import="gr.aueb.cf.schoolapp.dao.TeacherDAOImpl" %>
<%@ page import="gr.aueb.cf.schoolapp.service.ITeacherService" %>
<%@ page import="gr.aueb.cf.schoolapp.service.TeacherServiceImpl" %>
<%@ page import="gr.aueb.cf.schoolapp.model.Teacher" %>
<%@ page import="gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<header>
    <div class="outer">
        <div class="items">
            <a href=""><img class="gov-gr-logo" src="${pageContext.request.contextPath}/img/gov_header_logo.svg" alt="" width="40" height="auto"></a>
            <!-- <a href=""><img src="./art2.png" alt="" width="40" height="auto"></a> -->
            <span class="title">Coding Factory - Education Reinvented </span>
        </div>
        <div class="login-name">
<%--            <span>${sessionScope.firstname}</span>--%>
<%--            <span>${sessionScope.lastname</span>--%>
            <span>${sessionScope.username}</span>
            <c:if test="${sessionScope.username != null}">
                <a class="log-out" href="${pageContext.request.contextPath}/logout">Έξοδος</a>
            </c:if>

        </div>
    </div>
    <div class="line">

    </div>
</header>
</body>
</html>