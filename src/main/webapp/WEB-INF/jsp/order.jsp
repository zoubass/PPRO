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
<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Dinner Shop</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css" type="text/css"/>
    <script src="@{js/mobile.js}" type="text/javascript"></script>
</head>
<body>
<div id="header">
    <div>
        <a href="index"><img class="logo" src="img/logo.png" width="513" height="84" alt="" title=""></a>
        <a href="index"><img src="img/waitress.png" width="332" height="205" alt="" title=""></a>
        <ul class="navigation">
            <li>
                <a href="/index">Domů</a>
            </li>
            <li>
                <a href="/offer">Nabídka</a>
            </li>
            <sec:authorize access="isAuthenticated()">
                <li>
                    <a href="/logout">Odhlasit</a>
                </li>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <li>
                    <a href="/login">Prihlasit</a>
                </li>
            </sec:authorize>
            <li>
                <a href="/cart">Košík</a>
            </li>
            <li class="dropdown">
                <a href="#" class="dropbtn">Ostatni</a>
                <div class="dropdown-content">
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
                        <a href="/ordersList">Objednávky</a>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a href="/user">Uživatel</a>
                        <a href="/product">Produkt</a>
                    </sec:authorize>
                </div>
            </li>
        </ul>
    </div>
</div>
<div id="body">
    <div id="content">
        <div>
            <div>
                <h1>Vyplňte údaje o objednávce</h1>
            </div>


            <div>
                <h1>Objednávka</h1>
                <p>
                    <form:form action="/createOrder" modelAttribute="order" method="post">
                    <label>E-mail</label>
                    <form:input type="email" path="userEmail"/>
                    <form:errors path="userEmail"/>

                    <label>City</label>
                    <form:input path="city"/>
                    <form:errors path="city"/>

                    <label>Street</label>
                    <form:input path="street"/>
                    <form:errors path="street"/>
                </p>
                <p>
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
                <p>
                    <label>Počet produktů:</label>
                    <span class="total" name="PC">${order.totalProductCount}</span>
                </p>
                <p>
                    <label>Celková cena:</label>
                    <span class="total" name="tp">${order.totalPrice}</span></p>
                <c:if test="${success}">
                    <div>
                        <label class="error">Vaše objednávka byla úspěšně dokončena!</label>
                        <p>
                            <span>Dejte nám vědět jak jste byli s nákupem spokojeni</span>
                            <form:form action="/addFeedback" modelAttribute="feedback">
                                <c:set value="${order.userEmail}" var="email"/>
                                <form:input type="hidden" path="authorEmail"/>
                            <label>Hodnocení</label>
                        <div class="rating">
                            <span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span>
                        </div>
                        <label>Komentář (nepovinný)</label>
                        <form:input path="comment" type="text"/>
                        <p>
                            <input type="submit" value="Ohodnotit a ukončit objednávku"/>
                        </p>
                        </form:form>
                        </p>
                    </div>
                </c:if>
                <p>
                    <input type="submit" value="Dokončit" id="submit" onclick="alert()"/>
                </p>
                </form:form>
            </div>

        </div>
    </div>
</div>

<div id="footer">
    <div>
        <ul>
            <li class="first">
                <h2>Delivery Hotline</h2>
                <h3>Call 0-123-456-789</h3>
                <ul>
                    <li>
                        <a href="http://www.freewebsitetemplates.com/go/facebook" class="facebook"></a>
                    </li>
                    <li>
                        <a href="http://www.freewebsitetemplates.com/go/twitter" class="twitter"></a>
                    </li>
                    <li>
                        <a href="http://www.freewebsitetemplates.com/go/googleplus" class="googleplus"></a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="index.html"><img class="logo" src="img/logo-footer.png" alt=""></a>
                <ul class="navigation">
                    <li>
                        <a href="index.html">Home</a>
                    </li>
                    <li>
                        <a href="about.html">About Us</a>
                    </li>
                    <li>
                        <a href="menu.html">Menu</a>
                    </li>
                    <li>
                        <a href="contact.html">Contact Us</a>
                    </li>
                </ul>
                <span>&copy; 2023 RetroDiner.com. All Rights Reserved</span>
            </li>
            <li class="last">
                <h2>Follow Us By Email</h2>
                <form action="index.html">
                    <input type="text" name="subscribe" value="Enter Your Email Here...">
                    <input type="submit" value="">
                </form>
            </li>
        </ul>
    </div>
</div>
<script>
    function alert() {
        alert("Vaše objednávka byla uložena a čeká na potvrzení.");
    }
</script>
</body>
</html>
