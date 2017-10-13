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
    <title>Dinner Shop</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table-style.css" type="text/css"/>
    <script src="@{js/mobile.js}" type="text/javascript"></script>
</head>

<body>
<div id="header">
    <div>
        <a href="index/"><img class="logo" src="img/logo.png" width="513" height="84" alt="" title=""></a>
        <a href="index/"><img  src="img/waitress.png" width="332" height="205" alt="" title=""></a>
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
                <h1>Nákupní košík</h1>
                    <table title="Zakoupené produkty" unless="${cart}==null">
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
                        <input  id="submit" type="submit" name="submit" value="Objednat"/>
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
</body>
</html>

