<%--
  Created by IntelliJ IDEA.
  User: jakoubek
  Date: 4/27/2017
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <c:set var="cart" value="${cart}"/>
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
                <h1>Nákupní košík</h1>
            </div>
        </div>
        <div class="footer">
            <div class="contact">
                <div class="table-title">
                    <h1>Seznam produktů v košíku</h1>
                </div>
                <table class="table-fill" title="Zakoupené produkty" unless="${cart}==null">
                    <thead>
                    <tr>
                        <th>Název produktu</th>
                        <th>Kategorie</th>
                        <th>Cena</th>
                        <th>Odebrat</th>
                    </tr>
                    </thead>
                    <tbody class="table-hover">
                    <c:forEach items="${cart.products}" var="p">
                    <tr>
                        <td class="text-left">${p.type}</td>
                        <td class="text-left">${p.category}</td>
                        <td class="text-left">${p.price}  Kč</td>
                        <td class="text-left"><a href="/removeProduct?id=${p.id}">Odebrat</a></td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <h4 if="${cart}!=null">Celková cena:   ${cart.totalValue}   Kč</h4>

                <form:form action="/order" method="post">
                    <input  class="submit" type="submit" name="submit" value="Objednat"/>
                </form:form>
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

