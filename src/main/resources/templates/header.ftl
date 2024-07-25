<div class="navbar">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/create">Create</a></li>
        <#if activeProfile == 'dev'>
                    <li><a href="/api/categories">Categories</a></li>
                    <li><a href="/api/products">Products</a></li>
                    <li><a href="/api/stock_mv">Stock movements</a></li>
        </#if>
    </ul>
</div>
