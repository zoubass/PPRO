<%--
  Created by IntelliJ IDEA.
  User: jakoubek
  Date: 5/1/2017
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <h1>Přehled objednávek</h1>
            </div>
        </div>
        <div>
            <%--th:unless="${#lists.isEmpty(products)}"--%>
            <table class="table-fill" title="Produkty objednávky">
                <thead>
                <tr>
                    <th>Název produktu</th>
                    <th>Kategorie</th>
                    <th>Cena</th>
                </tr>
                </thead>
                <tbody class="table-hover">
                <c:forEach var="p" items="${products}">
                    <tr>
                        <td class="text-left">${p.type}</td>
                        <td class="text-left">${p.category}</td>
                        <td class="text-left">${p.price} Kc</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%--th:unless="${#lists.isEmpty(orders)}"--%>
            <table class="table-fill" title="Výsledek hledání">
                <tbody class="table-hover">
                <tr>
                    <th>Email</th>
                    <th>Město</th>
                    <th>Ulice</th>
                    <th>PSČ</th>
                    <th>Typ platby</th>
                    <th>Typ dodání</th>
                    <th>Celkem cena</th>
                    <th>Produkty</th>
                    <th>Odstranit</th>
                </tr>
                </tbody>
                <c:forEach var="o" items="${orders}">
                    <tr>
                        <td class="text-left">${o.userEmail}</td>
                        <td class="text-left">${o.city}"</td>
                        <td class="text-left">${o.street}"</td>
                        <td class="text-left">${o.psc}"</td>
                        <td class="text-left">${o.orderType}"</td>
                        <td class="text-left">${o.deliveryType}"</td>
                        <td class="text-left">${o.totalPrice}"</td>
                        <td class="text-left"><a href="/getOrderProducts?id=${o.id}">Zobrazit produkty</a></td>
                        <td class="text-left"><a href="/removeOrder?id=${o.id}">Odstranit</a></td>
                    </tr>
                </c:forEach>
            </table>
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
