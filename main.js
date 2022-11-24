$(document).ready(function () {
  // $("#sidebar").hide();
  $("#book-carousel").slick({
    slidesToShow: 3,
    slidesToScroll: 1,
    initialSlide: 0,
    centerMode: true,
    variableWidth: true,
    arrows: true,
    dots: false,
    speed: 300,
    centerPadding: "0px",
    infinite: true,
    autoplaySpeed: 500,
    autoplay: true,
  });
  setTimeout(() => {
    $("#book-carousel").slick("slickSetOption", "autoplaySpeed", 3000, true); // Set autoplaySpeed to 8 seconds.
  }, 501);
});
