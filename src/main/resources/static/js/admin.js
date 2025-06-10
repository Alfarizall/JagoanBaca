// Add this to your existing JS
document.addEventListener('DOMContentLoaded', function() {
    // Handle all transaction status update forms
    document.querySelectorAll('.transaction-status-form').forEach(form => {
        form.addEventListener('submit', async function(e) {
            e.preventDefault();
            
            const transactionId = this.dataset.transactionId;
            const status = this.querySelector('select[name="status"]').value;
            const submitButton = this.querySelector('button[type="submit"]');
            
            try {
                submitButton.disabled = true;
                submitButton.innerHTML = '<i class="fas fa-spinner fa-spin"></i>';
                
                const response = await fetch(`/api/transactions/update-status/${transactionId}?status=${status}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });

                const data = await response.json();
                
                if (response.ok) {
                    showAlert('success', 'Status transaksi berhasil diubah');
                    // Update the status display in the table
                    const statusCell = this.closest('tr').querySelector('.status-cell');
                    if (statusCell) {
                        statusCell.textContent = status;
                    }
                } else {
                    throw new Error(data.message || 'Failed to update status');
                }
            } catch (error) {
                showAlert('danger', error.message || 'Gagal mengubah status transaksi');
            } finally {
                submitButton.disabled = false;
                submitButton.innerHTML = 'Update';
            }
        });
    });
});

function showAlert(type, message) {
    const alertContainer = document.querySelector('.container.mt-3');
    if (!alertContainer) return;

    const alertHTML = `
        <div class="alert alert-${type} alert-dismissible fade show" role="alert">
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    `;
    
    // Remove any existing alerts
    alertContainer.querySelectorAll('.alert').forEach(alert => alert.remove());
    
    // Add new alert
    alertContainer.insertAdjacentHTML('beforeend', alertHTML);
}

// Category Management
document.addEventListener('DOMContentLoaded', function() {
    // Add Category
    const addCategoryForm = document.getElementById('addCategoryForm');
    if (addCategoryForm) {
        addCategoryForm.addEventListener('submit', async function(e) {
            e.preventDefault();
            const categoryName = document.getElementById('categoryName').value;
            
            try {
                const response = await fetch('/api/categories', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ name: categoryName })
                });

                if (response.ok) {
                    window.location.reload();
                } else {
                    alert('Gagal menambahkan kategori');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Terjadi kesalahan');
            }
        });
    }

    // Edit Category
    const editButtons = document.querySelectorAll('.edit-category');
    const editCategoryModal = new bootstrap.Modal(document.getElementById('editCategoryModal'));
    const editCategoryForm = document.getElementById('editCategoryForm');

    editButtons.forEach(button => {
        button.addEventListener('click', function() {
            const categoryId = this.dataset.categoryId;
            const categoryName = this.dataset.categoryName;
            
            document.getElementById('editCategoryId').value = categoryId;
            document.getElementById('editCategoryName').value = categoryName;
            
            editCategoryModal.show();
        });
    });

    if (editCategoryForm) {
        editCategoryForm.addEventListener('submit', async function(e) {
            e.preventDefault();
            const categoryId = document.getElementById('editCategoryId').value;
            const categoryName = document.getElementById('editCategoryName').value;

            try {
                const response = await fetch(`/api/categories/${categoryId}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ name: categoryName })
                });

                if (response.ok) {
                    window.location.reload();
                } else {
                    alert('Gagal mengupdate kategori');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Terjadi kesalahan');
            }
        });
    }

    // Delete Category
    const deleteButtons = document.querySelectorAll('.delete-category');
    deleteButtons.forEach(button => {
        button.addEventListener('click', async function() {
            if (confirm('Apakah Anda yakin ingin menghapus kategori ini?')) {
                const categoryId = this.dataset.categoryId;
                
                try {
                    const response = await fetch(`/api/categories/${categoryId}`, {
                        method: 'DELETE'
                    });

                    if (response.ok) {
                        window.location.reload();
                    } else {
                        alert('Gagal menghapus kategori');
                    }
                } catch (error) {
                    console.error('Error:', error);
                    alert('Terjadi kesalahan');
                }
            }
        });
    });
});

// Transaction Status Update
document.addEventListener('DOMContentLoaded', function() {
    // Get CSRF token
    const csrfToken = document.querySelector("meta[name='_csrf']");
    const csrfHeader = document.querySelector("meta[name='_csrf_header']");
    
    if (!csrfToken || !csrfHeader) {
        console.error('CSRF tokens not found');
        return;
    }

    const token = csrfToken.getAttribute("content");
    const header = csrfHeader.getAttribute("content");

    // Add event listeners to all update buttons
    document.querySelectorAll('.update-status-btn').forEach(button => {
        button.addEventListener('click', async function(e) {
            e.preventDefault();
            const transactionId = this.getAttribute('data-transaction-id');
            const row = this.closest('tr');
            const select = row.querySelector('.status-select');
            const statusCell = row.querySelector('.status-cell');
            const newStatus = select.value;

            try {
                const response = await fetch(`/api/transactions/${transactionId}/status`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        [header]: token
                    },
                    body: JSON.stringify({ status: newStatus })
                });

                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                const data = await response.json();
                
                // Update the status cell
                statusCell.textContent = newStatus;
                
                // Show success message
                const alertContainer = document.querySelector('.container.mt-3');
                const successAlert = document.createElement('div');
                successAlert.className = 'alert alert-success alert-dismissible fade show';
                successAlert.innerHTML = `
                    Status berhasil diperbarui menjadi ${newStatus}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                `;
                alertContainer.appendChild(successAlert);
                
                // Remove alert after 3 seconds
                setTimeout(() => {
                    successAlert.remove();
                }, 3000);
            } catch (error) {
                console.error('Error:', error);
                
                // Show error message
                const alertContainer = document.querySelector('.container.mt-3');
                const errorAlert = document.createElement('div');
                errorAlert.className = 'alert alert-danger alert-dismissible fade show';
                errorAlert.innerHTML = `
                    Gagal memperbarui status: ${error.message}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                `;
                alertContainer.appendChild(errorAlert);
                
                // Remove alert after 3 seconds
                setTimeout(() => {
                    errorAlert.remove();
                }, 3000);
            }
        });
    });
});
