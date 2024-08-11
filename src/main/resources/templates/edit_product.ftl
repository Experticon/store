<#include "layout.ftl">
<#macro block name="content">
    <h1>Edit Product</h1>
    <form id="productForm">
        <input type="hidden" id="productId" name="id" value="${product.id}">
        <label for="productName">Name:</label>
        <input type="text" id="productName" name="name" value="${product.name}">
        <br>
        <label for="sku">SKU:</label>
        <input type="text" id="sku" name="sku" value="${product.sku}">
        <br>
        <label for="description">Description:</label>
        <textarea id="description" name="description">${product.description}</textarea>
        <br>

        <label for="category">Category:</label>
        <select id="category" name="category">
                    <#list categories as cat>
                        <option value="${cat.id}" <#if cat.id == product.category.id>selected</#if>>${cat.name}</option>
                    </#list>
                </select>
        <br>
        <label for="price">Price:</label>
        <input type="text" id="price" name="price" value="${product.price}">
        <br>
        <label for="quantity">Quantity:</label>
        <input type="text" id="quantity" name="quantity" value="${product.quantity}">
        <br>
        <button type="submit">Update</button>
    </form>
    <script src="/js/editElem.js"></script>
</#macro>