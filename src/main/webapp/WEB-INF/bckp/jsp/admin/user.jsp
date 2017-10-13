<%@ page import="cz.eshop.model.AuthoritiesEnum" %><%--
  Created by IntelliJ IDEA.
  User: jakoubek
  Date: 5/1/2017
  Time: 3:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Frozen Yogurt Shop</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css" type="text/css"/>
    <script src="@{js/mobile.js}" type="text/javascript"></script>
</head>
<body>
<div id="page">
    <div id="header">
        <div>
            <a href="/index" class="logo"><img src="img/logo.png" alt=""/></a>
            <ul id="navigation">
                <li class="selected">
                    <a href="/index">Domů</a>
                </li>
                <li class="menu">
                    <a href="/offer">Produkty</a>
                </li>
                <sec:authorize access="isAuthenticated()">
                    <li class="menu">
                        <a href="/logout">Logout</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <li class="menu">
                        <a href="/login">Login</a>
                    </li>
                </sec:authorize>
                <li>
                    <a href="/cart">Košík</a>
                </li>
                <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
                    <li>
                        <a href="/ordersList">Objednávky</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="menu">
                        <a a:href="#">Admin</a>
                        <ul class="secondary">
                            <li>
                                <a href="/user">Uživatel</a>
                            </li>
                            <li>
                                <a href="/product">Produkt</a>
                            </li>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
    <div id="body" class="contact">
        <div class="header">
            <div>
                <h1>Administrace uživatelů</h1>
            </div>
        </div>

        <div class="footer">
            <div class="contact">
                <h1>Přidej uživatele</h1>
                <form:form action="/addUser" modelAttribute="userDto" method="post">
                    <form:input type="text" name="Name" onblur="this.value=!this.value?'Username':this.value;"
                           onfocus="this.select()" onclick="this.value='';" path="user.username"/>
                    <c:if test=}"
                    <%--<span th:if="${#fields.hasErrors('user.username')}" th:errors="*{user.username}">Name Error</span>--%>
                    <form:input type="text" name="Email" onblur="this.value=!this.value?'Password':this.value;"
                           onfocus="this.select()" onclick="this.value='';" path="user.password"/>
                    <%--<span th:if="${#fields.hasErrors('user.password')}" th:errors="*{user.password}">Name Error</span>--%>
                    <form:input type="text" name="Subject" onblur="this.value=!this.value?'Enabled':this.value;"
                           onfocus="this.select()" onclick="this.value='';" path="user.enabled"/>
                    <%--<span th:if="${#fields.hasErrors('user.enabled')}" th:errors="*{user.enabled}">Name Error</span>--%>
                    <form:select type="text" name="Authorities" path="authorities.authority">
                        <c:forEach var="a" items="<%=AuthoritiesEnum.values()%>">
                        <form:option value="${a}">${a}</form:option>
                        </c:forEach>
                    </form:select>
                    <p/>
                    <label class="error">${message}</label>
                    <p/>
                    <input type="submit" value="Vytvořit" id="submit"/>
                </form:form>
            </div>
            <div class="section">
                <h1>Vložte uživatele</h1>
                <p>Pro vložení uživatele zadejte postupně username, password, zda je povolený a roli.</p>
            </div>
        </div>
        <div class="footer">
            <div class="contact">
                <h1>Vyhledej uzivatele</h1>
                <form:form action="/showUsers" modelAttribute="userDto" method="post">
                    <form:input type="text" name="Name" path="user.username"/>
                    <p/>
                    <input type="submit" value="Hledat" id="submit"/>
                </form:form>
                <p/>
                <table>
                    <tr>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Enabled</th>
                        <th>Odstranit</th>
                    </tr>
                    <c:forEach var="user" items="${results}">
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.password}</td>
                        <td>${user.enabled}</td>
                        <td><a href="/removeUser?username=${user.username}">Odstranit</a></td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="section">
                <h1>Zadejte kriteria pro vyhledání uživatelů</h1>
                <p>V případě nevyplnění pole se vyhledají všichni uživatelé</p>
            </div>
        </div>
    </div>
    <div th:replace="fragments::footer"></div>
</div>
</body>
</html>