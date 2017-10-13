<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jakoubek
  Date: 5/1/2017
  Time: 3:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <h1>Administrace produktů</h1>
            </div>
            <div>
                <h1>Vložit produkt</h1>
                <form:form enctype="multipart/form-data" action="/addProductsDb" modelAttribute="product" method="post">
                    <form:input type="text" name="Type" text="${type}" value="Product name"
                                onblur="this.value=!this.value?'Product name':this.value;" onfocus="this.select()"
                                onclick="this.value='';" path="type"/>
                    <form:errors path="type"/>
                    <form:input type="text" name="Price" value="Price"
                                onblur="this.value=!this.value?'Price':this.value;"
                                onfocus="this.select()" onclick="this.value='';" path="price"/>
                    <form:errors path="price"/>
                    <form:input type="text" name="Category" value="Category"
                                onblur="this.value=!this.value?'Category':this.value;"
                                onfocus="this.select()" onclick="this.value='';" path="category"/>
                    <form:errors path="category"/>
                    <form:input type="hidden" name="Id" path="id" text="${id}"/>
                    <form:input style="background: transparent;" path="imageFile" name="file" type="file"/>
                    <p/>
                    <c:if test="!${isValidInput}">
                        <textarea name="meassage" cols="50" rows="7">Zadané hodnoty jsou nevalidní.</textarea>
                    </c:if>
                    <p>
                        <label>${success}</label>
                        <input type="submit" value="Vytvořit" id="submit"/>
                    </p>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form:form>
            </div>
            <div>
                <h1>Vyhledat produkt</h1>
                <form:form action="/searchProducts" modelAttribute="product" method="post">
                    <label for="type" title="title">Název produktu</label>
                    <input id="type" type="text" name="Type" value=""/>
                    <label id="category" for="type" title="title">Kategorie</label>
                    <input type="text" name="Category" value=""/>
                    <%--<textarea name="meassage" cols="50" rows="7"--%>
                    <%--th:if="!isValidInput">Zadané hodnoty jsou nevalidní.</textarea>--%>
                    <p>
                        <input type="submit" value="Vyhledat" class="submit"/>
                    </p>
                </form:form>
                <p/>
                <table class="table-fill" title="Výsledek hledání">
                    <tbody class="table-hover">
                    <tr>
                        <th>Název</th>
                        <th>Cena</th>
                        <th>Kategorie</th>
                        <th>Odstranit</th>

                    </tr>
                    </tbody>
                    <c:forEach var="p" items="${products}">
                        <tr>
                            <td class="text-left">${p.type}</td>
                            <td class="text-left">${p.price}</td>
                            <td class="text-left"><a href="/updateProducts?id=${p.id}">Editovat</a></td>
                            <td class="text-left"><a href="/removeProductdb?id=${p.id}">Odstranit</a></td>
                        </tr>
                    </c:forEach>
                </table>
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
