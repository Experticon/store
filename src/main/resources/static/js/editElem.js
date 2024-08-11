/** FRONTEND ЧАСТЬ
JavaScript для изменения сущностей
Файлы, использующие этот скрипт:
edit_category.ftl
edit_edit_product.ftl
edit_stock_mv.ftl
Обмен происходит по Data Transfer Object
*/

document.addEventListener('DOMContentLoaded', function() {
    function updateCategory() {
        const categoryForm = document.getElementById('editCategoryForm');
        if (categoryForm) {
            categoryForm.addEventListener('submit', function(event) {
                event.preventDefault();
                const categoryId = document.getElementById('categoryId').value;
                const categoryName = document.getElementById('categoryName').value;

                fetch(`/api/categories/category/update?id=${categoryId}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({ name: categoryName })
                })
                .then(response => response.json())
                .then(data => {
                    if (data.id) {
                        window.location.href = '/'; // Перенаправить на главную страницу
                    } else {
                        console.error('Error:', data.message);
                    }
                })
                .catch(error => console.error('Error:', error));
            });
        }
    }

    function updateProduct() {
        const productForm = document.getElementById('productForm');
        if (productForm) {
            productForm.addEventListener('submit', function(event) {
                event.preventDefault(); // Предотвратить стандартное поведение формы
                const productId = document.getElementById('productId').value;
                const productName = document.getElementById('productName').value;
                const productSku = document.getElementById('sku').value;
                const productDescription = document.getElementById('description').value;
                const productCategory = document.getElementById('category').value;
                const productPrice = document.getElementById('price').value;
                const productQuantity = document.getElementById('quantity').value;

                fetch(`/api/products/product/update?id=${productId}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        name: productName,
                        sku: productSku,
                        description: productDescription,
                        category_id: productCategory,
                        price: productPrice,
                        quantity: productQuantity
                    })
                })
                .then(response => response.json())
                .then(data => {
                    if (data.id) {
                        window.location.href = '/'; // Перенаправить на главную страницу
                    } else {
                        console.error('Error:', data.message);
                    }
                })
                .catch(error => console.error('Error:', error));
            });
        }
    }

    function updateStockMovement() {
            document.getElementById('stockMovementForm').addEventListener('submit', function(event) {
                event.preventDefault(); // Предотвратить стандартное поведение формы

                const stockId = document.getElementById('stockId').value;
                const productId = document.getElementById('product').value;
                const quantity = document.getElementById('quantity').value;
                const movementType = document.getElementById('movementType').value;
                const movementDate = new Date(document.getElementById('movementDate').value).toISOString();

                fetch(`/api/stock_mv/stock/update?id=${stockId}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        product_id: productId,
                        quantity: quantity,
                        movementType: movementType,
                        movementDate: movementDate
                    })
                })
                .then(response => response.json())
                .then(data => {
                    if (data.id) {
                        window.location.href = '/'; // Перенаправить на главную страницу после обновления
                    } else {
                        console.error('Error:', data.message);
                    }
                })
                .catch(error => console.error('Error:', error));
            });
        }

    /** Напишем условия на случай того, если та или иная форма не была найдена.
     В данном случае это необходимо, ведь отдельная страница обращается к одному скрипту,
     а этой странице нужна только одна функция этого скрипта
     */
    if (document.getElementById('editCategoryForm')) {
        updateCategory();
    }

    if (document.getElementById('productForm')) {
        updateProduct();
    }

    if (document.getElementById('stockMovementForm')) {
            updateStockMovement();
        }
});
