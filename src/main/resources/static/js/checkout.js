document.addEventListener('DOMContentLoaded', function() {
    // Load cart items
    loadCart();
    displayOrderItems();

    // Handle form submission
    document.getElementById('checkoutForm').addEventListener('submit', async function(e) {
        e.preventDefault();

        // Form validation
        const customerName = document.getElementById('customerName').value.trim();
        const customerAddress = document.getElementById('customerAddress').value.trim();
        const customerPhone = document.getElementById('customerPhone').value.trim();
        const paymentMethod = document.querySelector('input[name="paymentMethod"]:checked');

        // Validate form fields
        if (!customerName) {
            alert('Nama pelanggan harus diisi');
            return;
        }
        if (!customerAddress) {
            alert('Alamat harus diisi');
            return;
        }
        if (!customerPhone) {
            alert('Nomor telepon harus diisi');
            return;
        }
        if (!paymentMethod) {
            alert('Silakan pilih metode pembayaran');
            return;
        }
        if (!cart || cart.length === 0) {
            alert('Keranjang belanja kosong');
            return;
        }

        try {
            // Show loading state
            const submitButton = e.target.querySelector('button[type="submit"]');
            submitButton.disabled = true;
            submitButton.innerHTML = '<i class="fas fa-spinner fa-spin me-2"></i>Memproses...';

            // Create order data with simple array of items
            const orderData = {
                customerName: customerName,
                customerAddress: customerAddress,
                customerPhone: customerPhone,
                paymentMethod: paymentMethod.value,
                items: cart.map(item => ({
                    bookId: parseInt(item.id), // Ensure bookId is a number
                    quantity: item.quantity,
                    price: parseFloat(item.price) // Ensure price is a number
                }))
            };

            console.log('Sending order data:', JSON.stringify(orderData, null, 2));

            // Send order to server
            const response = await fetch('/api/transactions', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                body: JSON.stringify(orderData)
            });

            if (!response.ok) {
                // Try to read error message from response
                const errorData = await response.json().catch(() => null);
                throw new Error(errorData?.message || 'Gagal membuat pesanan');
            }

            const data = await response.json();
            console.log('Order success:', data);            // Clear cart
            clearCart();
            
            // Show success message and redirect
            alert('Pesanan berhasil dibuat! Silakan lakukan pembayaran.');
            window.location.href = '/home';

        } catch (error) {
            console.error('Checkout error:', error);
            alert(error.message || 'Terjadi kesalahan saat memproses pesanan');
        } finally {
            // Restore button state
            const submitButton = e.target.querySelector('button[type="submit"]');
            submitButton.disabled = false;
            submitButton.innerHTML = '<i class="fas fa-shopping-cart me-2"></i>Konfirmasi Pembayaran';
        }
    });
});

function displayOrderItems() {
    const container = document.getElementById('orderItems');
    const totalElement = document.getElementById('orderTotal');
    
    if (!container || !cart || cart.length === 0) {
        if (container) {
            container.innerHTML = '<p class="text-center text-muted">Keranjang belanja kosong</p>';
        }
        if (totalElement) {
            totalElement.textContent = 'Rp 0';
        }
        return;
    }

    let total = 0;
    container.innerHTML = '';

    cart.forEach(item => {
        const itemTotal = parseFloat(item.price) * item.quantity;
        total += itemTotal;

        container.innerHTML += `
            <div class="mb-2">
                <div class="d-flex justify-content-between">
                    <div>
                        <h6 class="mb-0">${item.title}</h6>
                        <small class="text-muted">${item.quantity}x @ Rp ${formatPrice(parseFloat(item.price))}</small>
                    </div>
                    <div>
                        <span>Rp ${formatPrice(itemTotal)}</span>
                    </div>
                </div>
            </div>
        `;
    });

    if (totalElement) {
        totalElement.textContent = `Rp ${formatPrice(total)}`;
    }
}

// Make sure we have the formatPrice function
function formatPrice(price) {
    return price.toLocaleString('id-ID');
}
