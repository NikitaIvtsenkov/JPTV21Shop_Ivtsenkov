

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="w-100 d-flex justify-content-center">Покупка продукта</h1>
<div class="w-100 d-flex justify-content-center">
    <div class="card border-0" style="width: 25rem;">
      <div class="card-body">
        <h5 class="card-title w-100">Список продуктов</h5>
        <form action="createHistory" method="POST">
            <p class="card-text w-100">
                <select name="productId" class="w-100">
                    <option selected disabled>Выберите продукт</option>
                    <c:forEach var="product" items="${listProducts}">
                        <option value="${product.id}">${product.name}</option>
                    </c:forEach>
                </select>
            </p>
            
            <p class="card-text w-100 d-flex justify-content-end">
                <input type="submit" value="Купить продукт">
            </p>
        </form>
           
      </div>
    </div>
</div>
        
        
    