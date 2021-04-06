document.addEventListener("DOMContentLoaded", function () {
    window.addEventListener('scroll', function () {
        if (window.scrollY > 50) {
            document.getElementsByClassName('navbar').item(0).classList.add('fixed-top')
            
            navbar_height = document.querySelector('.navbar').offsetHeight
            document.body.style.paddingTop = navbar_height + 'px'
        } else {
            document.getElementsByClassName('navbar').item(0).classList.remove('fixed-top')
            document.body.style.paddingTop = '0'
        }
      });
  });

window.addEventListener('scroll', function () {
    localStorage.scrollX = window.scrollX;
    localStorage.scrollY = window.scrollY;
})
window.addEventListener('load',function () {
    window.scrollTo(localStorage.scrollX || 0, localStorage.scrollY || 0);
})