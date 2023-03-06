

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Новый продукт</h1>
        <form method="POST" action="createProduct">
            Название продукта: <input type="text" name="name" value=""><br>
            Срок годности продукта: <input type="text" name="expirationDate" value=""><br>
            Количество продукта на складе: <input type="text" name="quantity" value=""><br>
            Список производителей:<br> 
            <select name="manufacturers" multiple="true">
                <c:forEach var="manufacturer" items="${listManufacturers}">
                    <option value="${manufacturer.id}">${manufacturer.firstname} ${manufacturer.lastname}</option>
                </c:forEach>
            </select><br>
            <input type="submit" value="Добавить">
        </form>
    
