<#include "layout.ftl">
<#macro block name="content">
    <h1>Edit Category</h1>
    <form action="/category/update" method="post">
        <input type="hidden" name="id" value="${category.id}">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${category.name}">
        <button type="submit">Update</button>
    </form>
</#macro>