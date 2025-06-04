// script.js

document.addEventListener("DOMContentLoaded", () => {
  const links = document.querySelectorAll(".custom-nav-link");

  links.forEach(link => {
    link.addEventListener("mouseenter", () => {
      link.style.transform = "scale(1.1)";
    });

    link.addEventListener("mouseleave", () => {
      link.style.transform = "scale(1)";
    });
  });
});

const wrapper = document.querySelector('.highlight-wrapper');
const slides = document.querySelectorAll('.highlight');

let index = 0;
let isDragging = false;
let startPos = 0;
let currentTranslate = 0;
let prevTranslate = 0;
let animationID;

// Ambil lebar setiap slide (assume full viewport)
const slideWidth = window.innerWidth;

// Geser otomatis setiap 5 detik
setInterval(() => {
  if (!isDragging) {
    index = (index + 1) % slides.length; // loop kembali ke awal
    setPositionByIndex();
  }
}, 5000);

function setPositionByIndex() {
  currentTranslate = index * -slideWidth;
  prevTranslate = currentTranslate;
  setSliderPosition();
}

function setSliderPosition() {
  wrapper.style.transform = `translateX(${currentTranslate}px)`;
}

function animation() {
  setSliderPosition();
  if (isDragging) requestAnimationFrame(animation);
}

// Drag events
wrapper.addEventListener('mousedown', dragStart);
wrapper.addEventListener('mouseup', dragEnd);
wrapper.addEventListener('mouseleave', dragEnd);
wrapper.addEventListener('mousemove', drag);

// Optional: support touch (HP/tablet)
wrapper.addEventListener('touchstart', dragStartTouch);
wrapper.addEventListener('touchend', dragEnd);
wrapper.addEventListener('touchmove', dragTouch);

function dragStart(e) {
  isDragging = true;
  startPos = e.pageX;
  animationID = requestAnimationFrame(animation);
  wrapper.style.cursor = 'grabbing';
}

function dragStartTouch(e) {
  isDragging = true;
  startPos = e.touches[0].clientX;
  animationID = requestAnimationFrame(animation);
}

function drag(e) {
  if (!isDragging) return;
  const currentPosition = e.pageX;
  currentTranslate = prevTranslate + currentPosition - startPos;
}

function dragTouch(e) {
  if (!isDragging) return;
  const currentPosition = e.touches[0].clientX;
  currentTranslate = prevTranslate + currentPosition - startPos;
}

function dragEnd() {
  cancelAnimationFrame(animationID);
  isDragging = false;
  const movedBy = currentTranslate - prevTranslate;

  if (movedBy < -100) {
    index = (index + 1) % slides.length; // maju, loop ke awal
  } else if (movedBy > 100) {
    index = (index - 1 + slides.length) % slides.length; // mundur, loop ke akhir
  }

  setPositionByIndex();
  wrapper.style.cursor = 'grab';
}

// Inisialisasi posisi awal
setPositionByIndex();

// Book Detail Modal functionality
document.addEventListener('DOMContentLoaded', function() {
    const bookDetailModal = new bootstrap.Modal(document.getElementById('bookDetailModal'));
    const modalElement = document.getElementById('bookDetailModal');
    let currentBook = null;

    // Handle modal hidden event
    modalElement.addEventListener('hidden.bs.modal', function () {
        // Reset modal content when hidden
        currentBook = null;
        document.getElementById('bookTitle').textContent = '';
        document.getElementById('bookPrice').textContent = '';
        document.getElementById('bookDescription').textContent = '';
        document.getElementById('bookStock').textContent = '';
        document.getElementById('bookCategory').textContent = '';
        document.getElementById('bookImage').src = '';
    });

    // Handle view details button clicks
    document.querySelectorAll('.view-details').forEach(button => {
        button.addEventListener('click', function() {
            const bookId = this.dataset.bookId;
            currentBook = {
                id: bookId,
                title: this.closest('.card').querySelector('.book-title').textContent,
                price: parseFloat(this.dataset.bookPrice),
                description: this.dataset.bookDescription,
                stock: parseInt(this.dataset.bookStock),
                category: this.dataset.bookCategory,
                imageUrl: this.closest('.card').querySelector('.card-img').src
            };

            // Update modal content
            document.getElementById('bookTitle').textContent = currentBook.title;
            document.getElementById('bookPrice').textContent = 'Rp ' + currentBook.price.toLocaleString('id-ID');
            document.getElementById('bookDescription').textContent = currentBook.description;
            document.getElementById('bookStock').textContent = currentBook.stock;
            document.getElementById('bookCategory').textContent = currentBook.category;
            document.getElementById('bookImage').src = currentBook.imageUrl;

            // Check if book is in favorites
            checkFavoriteStatus(bookId);
            
            // Show the modal
            bookDetailModal.show();
        });
    });

    // Add favorite functionality
    function checkFavoriteStatus(bookId) {
        const favoriteBtn = document.getElementById('toggleFavorite');
        fetch(`/favorites/check/${bookId}`)
            .then(response => response.json())
            .then(isFavorite => {
                updateFavoriteButton(isFavorite);
            })
            .catch(() => {
                // Handle error silently
            });
    }

    // Add to Cart functionality
    const addToCartModal = document.getElementById('addToCartModal');
    if (addToCartModal) {
        addToCartModal.addEventListener('click', function() {
            if (!currentBook) return;
            addToCart(currentBook.id, currentBook.title, currentBook.price);
            bookDetailModal.hide();
        });
    }

    // Buy Now functionality
    const buyNowModal = document.getElementById('buyNowModal');
    if (buyNowModal) {
        buyNowModal.addEventListener('click', function() {
            if (!currentBook) return;
            addToCart(currentBook.id, currentBook.title, currentBook.price);
            bookDetailModal.hide();
            window.location.href = '/checkout';
        });
    }

    // Handle favorite button clicks
    const favoriteBtn = document.getElementById('toggleFavorite');
    if (favoriteBtn) {
        favoriteBtn.addEventListener('click', function() {
            if (!currentBook) return;
            
            fetch(`/favorites/toggle/${currentBook.id}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(response => response.json())
            .then(isFavorite => {
                updateFavoriteButton(isFavorite);
                showNotification(
                    isFavorite ? 'Buku ditambahkan ke favorit' : 'Buku dihapus dari favorit',
                    isFavorite ? 'success' : 'info'
                );
            })
            .catch(error => {
                showNotification('Gagal mengubah status favorit', 'error');
            });
        });
    }
});

// Utility functions
function updateFavoriteButton(isFavorite) {
    const favoriteBtn = document.getElementById('toggleFavorite');
    if (favoriteBtn) {
        if (isFavorite) {
            favoriteBtn.classList.add('btn-danger');
            favoriteBtn.classList.remove('btn-outline-danger');
        } else {
            favoriteBtn.classList.remove('btn-danger');
            favoriteBtn.classList.add('btn-outline-danger');
        }
    }
}

// Notification function
function showNotification(message, type = 'success') {
    // You can implement your own notification system here
    alert(message);
}

