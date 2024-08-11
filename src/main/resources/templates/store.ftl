<#include "layout.ftl">
<#macro block name="content">
    <h1>Store</h1>

    <h2>Categories</h2>
    <table border="1" id="categories-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>

    <h2>Products</h2>
    <table border="1" id="products-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>SKU</th>
                <th>Description</th>
                <th>Category</th>
                <th>Price</th>
                <th>Real Price</th>
                <th>Quantity</th>
                <th>Last Quantity Update</th>
                <th>Created At</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>

    <h2>Stock Movements</h2>
    <table border="1" id="stock-mv-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Product</th>
                <th>Quantity</th>
                <th>Movement Type</th>
                <th>Movement Date</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>

    <script src="/js/store.js"></script>
</#macro>

