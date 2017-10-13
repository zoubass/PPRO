<%--
  Created by IntelliJ IDEA.
  User: jakoubek
  Date: 5/1/2017
  Time: 3:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Frozen Yogurt Shop</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
    <script src="@{js/mobile.js}" type="text/javascript"></script>
</head>
<body>
<div id="page">
    <div id="header">
        <div>
            <a href="/index" class="logo"><img src="img/logo.png" alt=""/></a>
            <ul class="navigation">
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
    <div id="body">
        <div class="header">
            <div>
                <h1>ERROR</h1>
            </div>
        </div>
        <div class="body">
            <img src="images/bg-header-about.jpg" alt=""/>
        </div>
        <div class="footer">
            <div class="sidebar">
                <h1>E R R O R</h1>
            </div>
            <div class="article">
                <h1>Kontaktujte prosím administrátora</h1>
                <p>Důvod erroru: </p>
                <span>${errorMessage}</span>
                <p>Error code: </p>
                <span>${errorCode}</span>
            </div>
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
</body>
</html>

