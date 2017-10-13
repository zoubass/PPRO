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
                <h1>Administrace produktů</h1>
            </div>
        </div>
        <div class="footer">
            <div class="contact">
                <h1>Vložit produkt</h1>
                <form:form action="/addProductsDb" modelAttribute="product" method="post">
                    <form:input type="text" name="Type" text="${type}" value="Type"
                                onblur="this.value=!this.value?'Product name':this.value;" onfocus="this.select()"
                                onclick="this.value='';" path="type"/>
                    <%--<span class="error" th:if="${#fields.hasErrors('type')}" th:errors="*{type}">Name Error</span>--%>
                    <form:input type="text" name="Price" value="Price"
                                onblur="this.value=!this.value?'Price':this.value;"
                                onfocus="this.select()" onclick="this.value='';" path="price"/>
                    <%--<span class="error" th:if="${#fields.hasErrors('price')}" th:errors="*{price}">Price Error</span>--%>
                    <form:input type="text" name="Category" value="Category"
                                onblur="this.value=!this.value?'Category':this.value;"
                                onfocus="this.select()" onclick="this.value='';" path="category"/>
                    <%--<span class="error" th:if="${#fields.hasErrors('category')}"--%>
                          <%--th:errors="*{category}">Cat Error</span>--%>
                    <form:input type="hidden" name="Id" path="id" text="${id}"/>
                    <form:select type="text" name="Images" path="imageFile">
                        <c:forEach var="imageFile" items="${images}">
                            <form:option value="${imageFile}" text="${imageFile}">${imageFile}</form:option>
                        </c:forEach>
                    </form:select>
                    <p/>
                    <c:if test="!${isValidInput}">
                        <textarea name="meassage" cols="50" rows="7">Zadané hodnoty jsou nevalidní.</textarea>
                    </c:if>
                    <label>${success}</label>
                    <input type="submit" value="Vytvořit" class="submit"/>
                </form:form>

            </div>
            <div class="section">
                <h1>Vložit produkt</h1>
                <p>Obrázek je možné přidat pomocí nadefinování jeho cesty, pro přidání úplně nového obrázku lze přidat
                    obrázek na sdílené uložiště a odkazovat se na něj</p>
            </div>
        </div>
        <div class="footer">
            <div class="contact">
                <h1>Vyhledat produkt</h1>
                <form:form action="/searchProducts" modelAttribute="product" method="post">
                    <label for="type" title="title">Název produktu</label>
                    <input id="type" type="text" name="Type" value=""/>
                    <label id="category" for="type" title="title">Kategorie</label>
                    <input type="text" name="Category" value=""/>
                    <%--<textarea name="meassage" cols="50" rows="7"--%>
                    <%--th:if="!isValidInput">Zadané hodnoty jsou nevalidní.</textarea>--%>
                    <input type="submit" value="Vyhledat" id="submit"/>
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
            <div class="section">
                <h1>Zadejte kriteria pro vyhledání produktů</h1>
                <p>V případě nevyplnění pole se vyhledávají všechny produkty</p>
            </div>
        </div>
    </div>
    <%--<div th:replace="fragments::#footer"></div>--%>
</div>
</body>
</html>
