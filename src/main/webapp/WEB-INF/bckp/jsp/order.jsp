<%@ page import="cz.eshop.model.PaymentType" %>
<%@ page import="cz.eshop.model.DeliveryType" %><%--
  Created by IntelliJ IDEA.
  User: jakoubek
  Date: 4/27/2017
  Time: 2:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <div id="body" class="contact">
        <div class="header">
            <div>
                <h1>Vyplňte údaje o objednávce</h1>
            </div>
        </div>
        <div class="footer">
            <div class="contact">
                <h1>Objednávka</h1>
                <form:form action="/createOrder" modelAttribute="order" method="post">
                    <label>E-mail</label>
                    <form:input type="email" name="Email" path="userEmail"/>
                    <form:errors path="userEmail"/>

                    <label>City</label>
                    <form:input path="city"/>
                    <form:errors path="city"/>

                    <label>Street</label>
                    <form:input path="street"/>
                    <form:errors path="street"/>

                    <label>PSC</label>
                    <form:input path="psc"/>
                    <form:errors path="psc"/>

                    <label>Typ platby</label>
                    <form:select type="text" name="PaymentType" path="orderType">
                        <c:forEach items="<%=PaymentType.values()%>" var="pType">
                            <form:option value="${pType}">${pType}</form:option>
                        </c:forEach>
                    </form:select>
                    <label>Způsob doručení</label>
                    <form:select type="text" name="Authorities" path="deliveryType">
                        <c:forEach var="value" items="<%=DeliveryType.values()%>">
                            <form:option value="${value}">${value}</form:option>
                        </c:forEach>
                    </form:select>
                    <form:input type="hidden" path="totalPrice"/>
                    <form:input type="hidden" path="totalProductCount"/>
                    <p/>
                    <label>Počet produktů:</label>
                    <span class="total" name="PC" >${order.totalProductCount}</span>
                    <label>Celková cena:</label>
                    <span class="total" name="tp" >${order.totalPrice}</span>
                    <c:if test="${success}"><label class="error" >Vaše objednávka byla úspěšně dokončena!</label></c:if>
                    <input type="submit" value="Dokončit" class="submit" onclick="alert()"/>
                </form:form>
            </div>
            <div class="section">
                <h1>Děkujeme Vám za objednávku</h1>
                <p>Po vyplnění všech údajů, prosím zkontrolujte. Po stisknutí tlačítka "Dokončit" se objednávka
                    uloží.</p>
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
<script>
    function alert() {
        alert("Vaše objednávka byla uložena a čeká na potvrzení.");
    }
</script>
</body>
</html>
