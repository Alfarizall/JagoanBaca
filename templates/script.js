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

