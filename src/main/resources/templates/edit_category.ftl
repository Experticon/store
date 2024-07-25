<#include "layout.ftl">
<#macro block name="content">
    <h1>Edit Category</h1>
    <form id="editCategoryForm">
            <input type="hidden" id="categoryId" name="id" value="${category.id}">
            <label for="name">Name:</label>
            <input type="text" id="categoryName" name="name" value="${category.name}">
            <button type="submit">Update</button>
        </form>
    <script src="/js/editElem.js"></script>
</#macro>
