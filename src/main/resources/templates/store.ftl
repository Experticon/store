<#include "layout.ftl">
<#macro block name="content">
    <h1>Store</h1>

    <h2>Categories</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
        </thead>
        <tbody>
            <#list categories as category>
                <tr>
                    <td>${category.id}</td>
                    <td>${category.name}</td>
                    <td>
                                    <form action="/category/delete/${category.id}" method="post">
                                        <button type="submit">Delete</button>
                                    </form>
                                    <form action="/category/edit/${category.id}" method="get">
                                        <button type="submit">Edit</button>
                                    </form>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>

    <h2>Products</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>SKU</th>
                <th>Description</th>
                <th>Category</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Last Quantity Update</th>
                <th>Created At</th>
            </tr>
        </thead>
        <tbody>
            <#list products as product>
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.sku}</td>
                    <td>${product.description}</td>
                    <td>${product.category.name}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>${product.lastQuantityUpdate}</td>
                    <td>${product.createdAt}</td>
                    <td>
                                    <form action="/product/delete/${product.id}" method="post">
                                        <button type="submit">Delete</button>
                                    </form>
                                    <form action="/product/edit/${product.id}" method="get">
                                        <button type="submit">Edit</button>
                                    </form>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>

    <h2>Stock Movements</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Product</th>
                <th>Quantity</th>
                <th>Movement Type</th>
                <th>Movement Date</th>
            </tr>
        </thead>
        <tbody>
            <#list stock_mv as stock>
                <tr>
                    <td>${stock.id}</td>
                    <td>${stock.product.name}</td>
                    <td>${stock.quantity}</td>
                    <td>${stock.movementType}</td>
                    <td>${stock.movementDate}</td>
                    <td>
                        <form action="/stock_mv/delete/${stock.id}" method="post">
                            <button type="submit">Delete</button>
                        </form>
                        <form action="/stock_mv/edit/${stock.id}" method="get">
                             <button type="submit">Edit</button>
                        </form>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>

</#macro>

