<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
	<%@ include file="header.jsp"%>

        <div class="container-fluid">
            <div class="container">
                <div class="row">
                    <h1 class="text-grey">Login</h1>
                </div>
                <div class=container>
                    <c:if test="${requestScope.isError eq 'true'}">
                        <p style="color: red">Login Error</p>
                    </c:if>
                </div>
                <form method="POST" action="${pageContext.request.contextPath}/login">
                    <div class="row">
                        <input type="email" name="username" required placeholder="E-mail">
                        <span></span>
                    </div>
                    <div class="row">
                        <input type="password" name="password" required placeholder="Password">
                        <span></span>
                    </div>
                    <div class="row">
                        <button type="submit">Sign In</button>
                    </div>
                </form>
                <div class="row text-grey">
                    <a href="#">Lost your password?</a>
                </div>
            </div>
            <div class="row">
                <p>Don't have an account? <a href="${pageContext.request.contextPath}/users/register">Sign up here!</a></p>
            </div>
        </div>



    <%@include file="footer.jsp"%>
</body>
</html>