document.addEventListener('DOMContentLoaded', function() {
    // Add Category Form Handler
    const addCategoryForm = document.getElementById('addCategoryForm');
    if (addCategoryForm) {
        addCategoryForm.addEventListener('submit', function(e) {
            e.preventDefault();
            const formData = new FormData(addCategoryForm);
            const categoryData = {
                name: formData.get('name')
            };

            fetch('/api/categories', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(categoryData)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // Close modal and refresh page
                const modal = bootstrap.Modal.getInstance(document.getElementById('addCategoryModal'));
                modal.hide();
                window.location.reload();
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Terjadi kesalahan saat menambah kategori');
            });
        });
    }

    // Edit Category Form Handler
    const editCategoryForm = document.getElementById('editCategoryForm');
    if (editCategoryForm) {
        // Populate edit modal when shown
        const editCategoryModal = document.getElementById('editCategoryModal');
        editCategoryModal.addEventListener('show.bs.modal', function(event) {
            const button = event.relatedTarget;
            const categoryId = button.getAttribute('data-id');
            const categoryName = button.getAttribute('data-name');
            
            document.getElementById('editCategoryId').value = categoryId;
            document.getElementById('editCategoryName').value = categoryName;
        });

        editCategoryForm.addEventListener('submit', function(e) {
            e.preventDefault();
            const formData = new FormData(editCategoryForm);
            const categoryId = formData.get('id');
            const categoryData = {
                id: categoryId,
                name: formData.get('name')
            };

            fetch(`/api/categories/${categoryId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(categoryData)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                const modal = bootstrap.Modal.getInstance(document.getElementById('editCategoryModal'));
                modal.hide();
                window.location.reload();
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Terjadi kesalahan saat mengubah kategori');
            });
        });
    }

    // Delete Category Handler
    const deleteButtons = document.querySelectorAll('.delete-category');
    deleteButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            const categoryId = this.getAttribute('data-id');
            
            fetch(`/api/categories/${categoryId}`, {
                method: 'DELETE'
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                window.location.reload();
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Terjadi kesalahan saat menghapus kategori');
            });
        });
    });
});
