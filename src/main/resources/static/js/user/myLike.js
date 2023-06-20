$(document).ready(function () {
  $(".buttons").click(function () {
    var buttonImg = $(this).find(".like");

    if (buttonImg.hasClass("active")) {
      buttonImg.removeClass("active");
      buttonImg.css("background-image", "url('../img/heart2.png')");
    } else {
      buttonImg.addClass("active");
      buttonImg.css("background-image", "url('../img/heart1.png')");
    }
  });
});