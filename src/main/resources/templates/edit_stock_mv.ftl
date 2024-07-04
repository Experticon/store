<#include "layout.ftl">
<#macro block name="content">
    <h1>Edit Stock Movement</h1>
    <form action="/stock_mv/update" method="post">
        <input type="hidden" name="id" value="${stock_mv.id}">
        <label for="product">Product:</label>
        <select id="product" name="productId">
            <#list products as product>
                <option value="${product.id}" <#if product.id == stock_mv.product.id>selected</#if>>${product.name}</option>
            </#list>
        </select>
        <label for="quantity">Quantity:</label>
        <input type="text" id="quantity" name="quantity" value="${stock_mv.quantity}">
        <label for="movementType">Movement Type:</label>
        <input type="text" id="movementType" name="movementType" value="${stock_mv.movementType}">
        <label for="movementDate">Movement Date:</label>
        <input type="text" id="movementDate" name="movementDate" value="${stock_mv.movementDate}">
        <button type="submit">Update</button>
    </form>
</#macro>