

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Список покупателей</h1>
        <ol>
            <c:forEach var="customer" items="${listReaders}">
                <li>
                    ${customer.firstname} ${customer.lastname}. Телефон: ${customer.phone}
                </li>
            </c:forEach>
        </ol>
    