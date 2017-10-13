<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        .error {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
        }

        .msg {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #31708f;
            background-color: #d9edf7;
            border-color: #bce8f1;
        }

    </style>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Dinner Shop</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css" type="text/css"/>
    <script src="@{js/mobile.js}" type="text/javascript"></script>
</head>

<body onload='document.loginForm.username.focus();'>
<div id="header">
    <div>
        <a href="index/"><img class="logo" src="img/logo.png" width="513" height="84" alt="" title=""></a>
        <a href="index/"><img src="img/waitress.png" width="332" height="205" alt="" title=""></a>
    </div>
</div>

<div id="body">
    <div id="content">
        <div>
            <div>
                <h2>Zadejte prosím přihlašovací údaje</h2>

                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>
                <c:if test="${not empty msg}">
                    <div class="msg">${msg}</div>
                </c:if>
                <form name='loginForm'
                      action="<c:url value='/j_spring_security_check' />" method='POST'>
                    <table style="background: transparent">
                        <tr>
                            <td>Uživatel:</td>
                            <td><input type='text' name='username' value=''></td>
                        </tr>
                        <tr>
                            <td>Heslo:</td>
                            <td><input type='password' name='password'/></td>
                        </tr>
                        <tr>
                            <td colspan='2'><input name="submit" type="submit"
                                                   value="Přihlásit"/></td>
                        </tr>
                    </table>

                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>