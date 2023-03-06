
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Список производителей</h1>
        <ol>
            <c:forEach var="manufacturer" items="${listManufacturers}">
                <li>
                    ${manufacturer.namemanufacturer} ${manufacturer.brand}<br>
                    <ul>
                        <c:forEach var="product" items="${manufacturer.products}">
                            <li>${product.name}. ${product.expirationDate}</li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ol>
   