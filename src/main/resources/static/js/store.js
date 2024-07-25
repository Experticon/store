/** JavaScript для отображения сущностей на главной странице.
Также скрипт отвечает за обработку удаления сущностей и перессылку на их страницы
изменения.
store.ftl использует функции этого скрипта
*/

document.addEventListener('DOMContentLoaded', function() {
    // Function to fetch and display categories
    function fetchCategories() {
        fetch('/api/categories')
            .then(response => response.json())
            .then(data => {
                const categoriesTableBody = document.getElementById('categories-table').getElementsByTagName('tbody')[0];
                categoriesTableBody.innerHTML = ''; // Clear the table body
                data.forEach(category => {
                    const row = categoriesTableBody.insertRow();
                    row.insertCell(0).innerText = category.id;
                    row.insertCell(1).innerText = category.name;
                    const actionsCell = row.insertCell(2);
                    actionsCell.innerHTML = `
                        <button class="delete-category-button" data-id="${category.id}">Delete</button>
                        <form action="/category/edit/${category.id}" method="get">
                            <button type="submit">Edit</button>
                        </form>
                    `;
                });
                attachCategoryEventListeners(); // Привязать обработчик к кнопкам
            })
            .catch(error => console.error('Error fetching categories:', error));
    }

    // Функция удаления сущности
    function deleteCategory(id) {
        fetch(`/api/categories/category/delete/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
                if (!response.ok) {
                    // Если статус ответа не OK, преобразуем тело ответа в JSON и выбрасываем ошибку
                    return response.json().then(errorData => {
                        throw new Error(errorData.message || 'Unknown error occurred');
                    });
                }
                return response.json();
            })
        .then(() => {
            fetchCategories();
        })
        //Это отлавливает ошибку
        .catch(error => console.error('Error deleting category:', error));
    }

    // У каждой функции, которая отображает список сущностей есть event listener.
    // На данный момент к этому обработчику привязана лишь одна функция - функция удаления.
    function attachCategoryEventListeners() {
        document.querySelectorAll('.delete-category-button').forEach(button => {
            button.addEventListener('click', function() {
                const id = this.getAttribute('data-id');
                deleteCategory(id);
            });
        });
    }

// Остальные функции имеют примерно такую же структуру.

    // Function to fetch and display products
    function fetchProducts() {
        fetch('/api/products')
            .then(response => response.json())
            .then(data => {
                const productsTableBody = document.getElementById('products-table').getElementsByTagName('tbody')[0];
                productsTableBody.innerHTML = ''; // Clear the table body
                data.forEach(product => {
                    const row = productsTableBody.insertRow();
                    row.insertCell(0).innerText = product.id;
                    row.insertCell(1).innerText = product.name;
                    row.insertCell(2).innerText = product.sku;
                    row.insertCell(3).innerText = product.description;
                    row.insertCell(4).innerText = product.category.name;
                    row.insertCell(5).innerText = product.price;
                    row.insertCell(6).innerText = product.quantity;
                    row.insertCell(7).innerText = product.lastQuantityUpdate;
                    row.insertCell(8).innerText = product.createdAt;
                    const actionsCell = row.insertCell(9);
                    actionsCell.innerHTML = `
                        <button class="delete-product-button" data-id="${product.id}">Delete</button>
                        <form action="/product/edit/${product.id}" method="get">
                            <button type="submit">Edit</button>
                        </form>
                    `;
                });
                attachProductEventListeners(); // Attach event listeners to the new product buttons
            })
            .catch(error => console.error('Error fetching products:', error));
    }

    // Function to handle deletion of product
    function deleteProduct(id) {
        fetch(`/api/products/product/delete/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
                        if (!response.ok) {
                            // Если статус ответа не OK, преобразуем тело ответа в JSON и выбрасываем ошибку
                            return response.json().then(errorData => {
                                throw new Error(errorData.message || 'Unknown error occurred');
                            });
                        }
                        return response.json();
                    })
        .then(() => {
            fetchProducts(); // Refresh the products list after deletion
        })
        .catch(error => console.error('Error deleting product:', error));
    }

    // Attach event listeners to product buttons
    function attachProductEventListeners() {
        document.querySelectorAll('.delete-product-button').forEach(button => {
            button.addEventListener('click', function() {
                const id = this.getAttribute('data-id');
                deleteProduct(id);
            });
        });
    }

    // Function to fetch and display stock movements
    function fetchStockMovements() {
        fetch('/api/stock_mv')
            .then(response => response.json())
            .then(data => {
                const stockMvTableBody = document.getElementById('stock-mv-table').getElementsByTagName('tbody')[0];
                stockMvTableBody.innerHTML = ''; // Clear the table body
                data.forEach(stockMv => {
                    const row = stockMvTableBody.insertRow();
                    row.insertCell(0).innerText = stockMv.id;
                    row.insertCell(1).innerText = stockMv.product.name;
                    row.insertCell(2).innerText = stockMv.quantity;
                    row.insertCell(3).innerText = stockMv.movementType;
                    row.insertCell(4).innerText = stockMv.movementDate;
                    const actionsCell = row.insertCell(5);
                    actionsCell.innerHTML = `
                            <button class="delete-stock-button" data-id="${stockMv.id}">Delete</button>
                        <form action="/stock_mv/edit/${stockMv.id}" method="get">
                             <button type="submit">Edit</button>
                        </form>
                    `;
                });
                attachStocksEventListeners();
            })
            .catch(error => console.error('Error fetching stock movements:', error));
    }

    function deleteStock(id) {
            fetch(`/api/stock_mv/stock/delete/${id}`, {
                method: 'DELETE'
            })
            .then(response => {
                            if (!response.ok) {
                                // Если статус ответа не OK, преобразуем тело ответа в JSON и выбрасываем ошибку
                                return response.json().then(errorData => {
                                    throw new Error(errorData.message || 'Unknown error occurred');
                                });
                            }
                            return response.json();
                        })
            .then(() => {
                fetchStockMovements(); // Refresh the products list after deletion
            })
            .catch(error => console.error('Error deleting stock:', error));
        }

        function attachStocksEventListeners() {
            document.querySelectorAll('.delete-stock-button').forEach(button => {
                button.addEventListener('click', function() {
                    const id = this.getAttribute('data-id');
                    deleteStock(id);
                });
            });
        }

    // Как только страница загрузится, привяжем функции.
    fetchCategories();
    fetchProducts();
    fetchStockMovements();
});