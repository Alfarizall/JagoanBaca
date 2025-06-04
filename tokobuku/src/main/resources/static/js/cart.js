// Shopping Cart functionality
let cart = [];

document.addEventListener('DOMContentLoaded', function() {
    // Load cart from localStorage
    loadCart();
    
    // Initialize UI elements if they exist
    const cartItemsContainer = document.getElementById('cartItems');
    if (cartItemsContainer) {
        displayCartItems();
        updateTotalAmount(); // Update total on page load
    }

    // Handle clear cart button
    const clearCartBtn = document.getElementById('clearCart');
    if (clearCartBtn) {
        clearCartBtn.addEventListener('click', function() {
            if (confirm('Apakah Anda yakin ingin mengosongkan keranjang?')) {
                clearCart();
                updateTotalAmount(); // Update total after clearing
                showNotification('Keranjang telah dikosongkan');
            }
        });
    }

    // Handle checkout button
    const checkoutBtn = document.getElementById('checkoutCart');
    if (checkoutBtn) {
        checkoutBtn.addEventListener('click', function() {
            if (cart.length === 0) {
                alert('Keranjang belanja kosong!');
                return;
            }
            // Redirect to checkout page
            window.location.href = '/checkout';
        });
    }

    // Handle quantity buttons and remove items
    if (cartItemsContainer) {
        cartItemsContainer.addEventListener('click', function(e) {
            const cartItem = e.target.closest('.cart-item');
            if (!cartItem) return;
            
            const bookId = cartItem.dataset.bookId;
            
            if (e.target.classList.contains('increase-quantity')) {
                updateQuantity(bookId, 1);
                updateTotalAmount();
            } else if (e.target.classList.contains('decrease-quantity')) {
                updateQuantity(bookId, -1);
                updateTotalAmount();
            } else if (e.target.classList.contains('remove-item') || 
                      e.target.closest('.remove-item')) {
                if (confirm('Apakah Anda yakin ingin menghapus buku ini dari keranjang?')) {
                    removeFromCart(bookId);
                    updateTotalAmount();
                    showNotification('Buku telah dihapus dari keranjang');
                }
            }
        });
    }

    // Handle add to cart from modal
    const addToCartModal = document.getElementById('addToCartModal');
    if (addToCartModal) {
        addToCartModal.addEventListener('click', function() {
            const modal = document.getElementById('bookDetailModal');
            const bookId = modal.querySelector('.view-details').dataset.bookId;
            const title = modal.querySelector('#bookTitle').textContent;
            const price = parseFloat(modal.querySelector('#bookPrice').textContent.replace(/[^0-9]/g, ''));
            const imageUrl = modal.querySelector('#bookImage').src;
            const category = modal.querySelector('#bookCategory').textContent;
            
            addToCart(bookId, title, price, imageUrl, category);
            bootstrap.Modal.getInstance(modal).hide();
        });
    }
});

function addToCart(bookId, title, price, imageUrl = '', category = '') {
    const existingItem = cart.find(item => item.id === bookId);
    
    if (existingItem) {
        existingItem.quantity += 1;
    } else {
        cart.push({
            id: bookId,
            title: title,
            price: price,
            imageUrl: imageUrl,
            category: category,
            quantity: 1
        });
    }

    saveCart();
    updateCartDisplay();
    showNotification('Buku ditambahkan ke keranjang');
}

function removeFromCart(bookId) {
    cart = cart.filter(item => item.id !== bookId);
    saveCart();
    displayCartItems();
    updateTotalAmount(); // Update total after removing item
}

function updateQuantity(bookId, change) {
    const item = cart.find(item => item.id === bookId);
    if (item) {
        item.quantity += change;
        if (item.quantity < 1) {
            removeFromCart(bookId);
        } else {
            saveCart();
            updateCartDisplay();
        }
    }
}

function clearCart() {
    cart = [];
    saveCart();
    displayCartItems();
    updateTotalAmount(); // Update total after clearing
}

function displayCartItems() {
    const container = document.getElementById('cartItems');
    if (!container) return;

    container.innerHTML = '';
    
    if (cart.length === 0) {
        container.innerHTML = '<p class="text-center">Keranjang belanja kosong</p>';
        return;
    }

    cart.forEach(item => {
        const template = document.getElementById('cartItemTemplate');
        const clone = template.content.cloneNode(true);
        
        const cartItem = clone.querySelector('.cart-item');
        cartItem.dataset.bookId = item.id;
        
        const image = clone.querySelector('.book-image');
        image.src = item.imageUrl || '/img/default-book.jpg';
        image.alt = item.title;
        
        clone.querySelector('.book-title').textContent = item.title;
        clone.querySelector('.book-category').textContent = item.category;
        clone.querySelector('.quantity').textContent = item.quantity;
        clone.querySelector('.price').textContent = 'Rp ' + formatPrice(item.price * item.quantity);
        
        container.appendChild(clone);
    });

    updateTotalAmount();
}

function updateCartDisplay() {
    if (document.getElementById('cartItems')) {
        displayCartItems();
    }
    if (document.getElementById('cartCount')) {
        document.getElementById('cartCount').textContent = cart.reduce((total, item) => total + item.quantity, 0);
    }
}

function updateTotalAmount() {
    const totalAmount = cart.reduce((total, item) => total + (item.price * item.quantity), 0);
    const totalElement = document.getElementById('totalAmount');
    if (totalElement) {
        totalElement.textContent = 'Rp ' + formatPrice(totalAmount);
    }
    
    // Update empty cart message
    const cartItems = document.getElementById('cartItems');
    if (cartItems && cart.length === 0) {
        cartItems.innerHTML = '<p class="text-center my-4">Keranjang belanja kosong</p>';
    }
    
    // Update checkout button state
    const checkoutBtn = document.getElementById('checkoutCart');
    if (checkoutBtn) {
        checkoutBtn.disabled = cart.length === 0;
    }
}

function formatPrice(price) {
    return price.toLocaleString('id-ID');
}

function saveCart() {
    localStorage.setItem('cart', JSON.stringify(cart));
}

function loadCart() {
    const savedCart = localStorage.getItem('cart');
    if (savedCart) {
        cart = JSON.parse(savedCart);
    }
}

function showNotification(message, type = 'success') {
    alert(message);
}

// Book Detail Modal functionality
const bookDetailModal = new bootstrap.Modal(document.getElementById('bookDetailModal'));
let currentBook = null;

function showBookDetails(bookId) {
    // In a real application, you would fetch this data from the server
    // For now, we'll get it from the DOM
    const bookElement = document.querySelector(`[data-book-id="${bookId}"]`).closest('.card');
    
    currentBook = {
        id: bookId,
        title: bookElement.querySelector('.book-title').textContent,
        description: bookElement.querySelector('.book-desc').getAttribute('data-full-description') || 
                    bookElement.querySelector('.book-desc').textContent,
        price: parseFloat(bookElement.querySelector('.view-details').getAttribute('data-book-price')),
        imageUrl: bookElement.querySelector('.card-img').src,
        stock: bookElement.querySelector('.view-details').getAttribute('data-book-stock'),
        category: bookElement.querySelector('.view-details').getAttribute('data-book-category')
    };

    // Update modal content
    document.getElementById('bookTitle').textContent = currentBook.title;
    document.getElementById('bookDescription').textContent = currentBook.description;
    document.getElementById('bookPrice').textContent = `Rp ${formatPrice(currentBook.price)}`;
    document.getElementById('bookImage').src = currentBook.imageUrl;
    document.getElementById('bookStock').textContent = currentBook.stock || 'Tersedia';
    document.getElementById('bookCategory').textContent = currentBook.category || 'Umum';

    // Show modal
    bookDetailModal.show();
}
