<#include "layout.ftl">
<#macro block name="content">
    <h1>Edit Product</h1>
    <form action="/product/update" method="post">
        <input type="hidden" name="id" value="${product.id}">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${product.name}">
        <label for="sku">SKU:</label>
        <input type="text" id="sku" name="sku" value="${product.sku}">
        <label for="description">Description:</label>
        <textarea id="description" name="description">${product.description}</textarea>
        <label for="category">Category:</label>
        <select id="category" name="category">
            <#list categories as category>
                <option value="${category.id}" <#if category.id == product.category.id>selected</#if>>${category.name}</option>
            </#list>
        </select>
        <label for="price">Price:</label>
        <input type="text" id="price" name="price" value="${product.price}">
        <label for="quantity">Quantity:</label>
        <input type="text" id="quantity" name="quantity" value="${product.quantity}">
        <button type="submit">Update</button>
    </form>
</#macro>