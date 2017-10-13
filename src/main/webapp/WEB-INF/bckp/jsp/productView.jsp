<%--
  Created by IntelliJ IDEA.
  User: jakoubek
  Date: 4/27/2017
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
                    <a href="offer">Produkty</a>
                </li>
                <sec:authorize access="isAuthenticated()">
                    <li class="menu">
                        <a href="logout">Logout</a>
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
                        <a href="ordersList">Objednávky</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="menu">
                        <a a:href="#">Admin</a>
                        <ul class="secondary">
                            <li>
                                <a href="user">Uživatel</a>
                            </li>
                            <li>
                                <a href="product">Produkt</a>
                            </li>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
    <div id="body">
        <div class="header">
            <div>
                <h1>${product.type}</h1>
            </div>
        </div>
        <div>
            <ul>
                <li>
                    <h1>${product.category}</h1>
                    <p>Skvělé a zdravé jídlo</p>
                </li>
                <li>
                    <img src="${product.image}" alt=""/>
                    <h2>${product.price}</h2>

                    <div class="footer">
                        <div class="contact">
                            <form:form modelAttribute="product" action="/addProduct" method="post">
                                <form:hidden path="id"/>
                                <form:hidden path="type"/>
                                <form:hidden path="price"/>
                                <form:hidden path="category"/>
                                <input class="submit" type="submit" name="submit" value="Do košíku"/>
                            </form:form>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div id="footer">
        <div>
            <div class="connect">
                <a href="http://freewebsitetemplates.com/go/facebook/" class="facebook">facebook</a>
                <a href="http://freewebsitetemplates.com/go/twitter/" class="twitter">twitter</a>
                <a href="http://freewebsitetemplates.com/go/googleplus/" class="googleplus">googleplus</a>
                <a href="http://pinterest.com/fwtemplates/" class="pinterest">pinterest</a>
            </div>
            <p>&copy; 2023 Freeeze. All Rights Reserved.</p>
        </div>
    </div>
</div>
</div>
</body>
</html>