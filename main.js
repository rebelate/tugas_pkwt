$(document).ready(function () {
  // $("#sidebar").hide();
  $("#book-carousel").slick({
    slidesToShow: 3,
    slidesToScroll: 1,
    initialSlide: 1,
    centerMode: true,
    variableWidth: true,
    arrows: true,
    dots: false,
    speed: 300,
    centerPadding: "0px",
    infinite: true,
    autoplaySpeed: 5000,
    autoplay: true,
  });

});