/** FRONTEND ЧАСТЬ
JavaScript для создания сущностей
createElem.ftl использует функции этого скрипта
Обмен происходит по Data Transfer Object
*/

document.addEventListener('DOMContentLoaded', function() {
    // Функция для создания категории
    function createCategory() {
        document.getElementById('categoryForm').addEventListener('submit', function(event) {
            event.preventDefault(); // Предотвратить стандартное поведение формы
            const categoryName = document.getElementById('categoryName').value;

            fetch('/api/categories/category/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ name: categoryName })
            })
            .then(response => response.json())
            .then(data => {
                if (data.id) {
                    // Перенаправить пользователя на другую страницу или обновить текущую
                    window.location.reload(); // Пример: обновить текущую страницу
                } else {
                    console.error('Error:', data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
        });
    }

    function createProduct() {
            const productForm = document.getElementById('productForm');
            if (productForm) {
                productForm.addEventListener('submit', function(event) {
                    event.preventDefault(); // Предотвратить стандартное поведение формы
                    const productName = document.getElementById('productName').value;
                    const productSku = document.getElementById('sku').value;
                    const productDescription = document.getElementById('description').value;
                    const productCategoryId = document.getElementById('category').value; // Assuming this holds the category ID
                    const productCategoryName = document.getElementById('categoryName').value; // Assuming this holds the category name
                    const productPrice = document.getElementById('price').value;
                    const productQuantity = document.getElementById('quantity').value;
                    const productCreatedAt = new Date(document.getElementById('createdAt').value).toISOString();

                    fetch('/api/products/product/create', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({
                            name: productName,
                            sku: productSku,
                            description: productDescription,
                            category_id: productCategoryId,
                            price: productPrice,
                            quantity: productQuantity,
                            lastQuantityUpdate: productCreatedAt,
                            createdAt: productCreatedAt
                        })
                    })
                    .then(response => response.json())
                    .then(data => {
                        if (data.id) {
                            window.location.reload(); // Пример: обновить текущую страницу
                        } else {
                            console.error('Error:', data.message);
                        }
                    })
                    .catch(error => console.error('Error:', error));
                });
            } else {
                console.error('Product form not found');
            }
        }

    function createStock() {
        document.getElementById('stockMovementForm').addEventListener('submit', function(event) {
            event.preventDefault(); // Предотвратить стандартное поведение формы

            const product = document.getElementById('product').value;
            const quantity = document.getElementById('quantity_stock').value;
            const movementType = document.getElementById('movementType').value;
            const movementDate = new Date(document.getElementById('movementDate').value).toISOString();

            //console.log("Product ID: ", product);
            //console.log("Quantity: ", quantity);
            //console.log("Movement Type: ", movementType);
            //console.log("Movement Date: ", movementDate);

            fetch('/api/stock_mv/stock/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    product_id: product,
                    quantity: quantity,
                    movementType: movementType,
                    movementDate: movementDate
                })
            })
            .then(response => response.json())
            .then(data => {
                if (data.id) {
                    //console.log("Stock Movement Created: ", data);
                     window.location.reload(); // Обновить текущую страницу
                } else {
                    console.error('Error:', data.message);
                }
            })
            .catch(error => console.error('Error:', error));
        });
    }

    if (document.getElementById('categoryForm')) {
        createCategory();
    }

    if (document.getElementById('productForm')) {
        createProduct();
    }

    if (document.getElementById('stockMovementForm')) {
        createStock();
    }


});