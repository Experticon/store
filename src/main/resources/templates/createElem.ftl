<#include "layout.ftl">
<#macro block name="content">
   <h1>Create category</h1>
       <form action="/category/create" method="post">
           <label for="categoryName">Category Name:</label>
           <input type="text" id="categoryName" name="name" required>
           <button type="submit">Create Category</button>
       </form>

       <h1>Create product</h1>
       <form action="/product/create" method="post">
           <label for="productName">Product Name:</label>
           <input type="text" id="productName" name="name" required>
           <br>
           <label for="sku">SKU:</label>
           <input type="text" id="sku" name="sku" required>
           <br>
           <label for="description">Description:</label>
           <textarea id="description" name="description"></textarea>
           <br>
           <label for="category">Category:</label>
           <select id="category" name="category">
               <#list categories as category>
                   <option value="${category.id}">${category.name}</option>
               </#list>
           </select>
           <br>
           <label for="price">Price:</label>
           <input type="text" id="price" name="price" required>
           <br>
           <label for="quantity">Quantity:</label>
           <input type="number" id="quantity" name="quantity" required>
           <br>
           <label for="createdAt">Created At:</label>
           <input type="datetime-local" id="createdAt" name="createdAt" required>
           <button type="submit">Create Product</button>
       </form>

       <h1>Create stock movement</h1>
       <form action="/stock-movement/create" method="post">
           <label for="product">Product:</label>
           <select id="product" name="product">
               <#list products as product>
                   <option value="${product.id}">${product.name}</option>
               </#list>
           </select>
           <br>
           <label for="quantity">Quantity:</label>
           <input type="number" id="quantity" name="quantity" required>
           <br>
           <label for="movementType">Movement Type:</label>
           <select id="movementType" name="movementType">
               <option value="IN">In</option>
               <option value="OUT">Out</option>
           </select>
           <br>
           <label for="movementDate">Movement Date:</label>
           <input type="datetime-local" id="movementDate" name="movementDate" required>
           <button type="submit">Create Stock Movement</button>
       </form>
</#macro>