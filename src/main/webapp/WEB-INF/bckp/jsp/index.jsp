<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
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
            <ul class="navigation">
                <li>
                    <a class="active" href="/index">Domů</a>
                </li>
                <li>
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
</div>
<div id="body" class="home">
    <div class="header">
        <img src="img/bg-home.jpg" alt=""/>
        <div>
            <a href="product.html">Freeze Delight</a>
        </div>
    </div>
    <div class="body">
        <div>
            <div>
                <h1>Nový produkt</h1>
                <h2>Maliny s jogurtem, jak překvapivé!</h2>
                <p>Lorem Ipsum rulez Lorem Ipsum rulez Lorem Ipsum rulez Lorem Ipsum rulez Lorem Ipsum rulez Lorem
                    Ipsum rulez Lorem Ipsum rulez </p>
            </div>
            <img src="img/yogurt.jpg" alt=""/>
        </div>
    </div>
    <div class="footer">
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
