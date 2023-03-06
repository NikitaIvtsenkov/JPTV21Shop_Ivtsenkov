
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Список продуктов</h1>
        <ol>
            <c:forEach var="product" items="${listProducts}">
                <li>${product.name}. 
                    <c:forEach var="manufacturer" items="${product.manufacturers}">
                        ${manufacturer.firstname} ${manufacturer.lastname}. 
                    </c:forEach>
                </li>
                
            </c:forEach>
        </ol>
    