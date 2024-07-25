<#include "layout.ftl">
<#macro block name="content">
    <h1>Edit Stock Movement</h1>
    <form id="stockMovementForm">
        <input type="hidden" id="stockId" name="id" value="${stock_mv.id}">
        <label for="product">Product:</label>
        <select id="product" name="productId">
            <#list products as product>
                <option value="${product.id}" <#if product.id == stock_mv.product.id>selected</#if>>${product.name}</option>
            </#list>
        </select>
        <label for="quantity">Quantity:</label>
        <input type="text" id="quantity" name="quantity" value="${stock_mv.quantity}">
        <label for="movementType">Movement Type:</label>
        <select id="movementType" name="movementType">
            <option value="IN" <#if stock_mv.movementType == "IN">selected</#if>>In</option>
            <option value="OUT" <#if stock_mv.movementType == "OUT">selected</#if>>Out</option>
        </select>
        <label for="movementDate">Movement Date:</label>
        <input type="datetime-local" id="movementDate" name="movementDate" value="${formattedMovementDate}">
        <button type="submit">Update</button>
    </form>
    <script src="/js/editElem.js"></script>
</#macro>
