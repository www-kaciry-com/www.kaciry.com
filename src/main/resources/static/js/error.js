let cip = returnCitySN["cip"];
let url = window.location.href;
$(".cip").children().remove();
$(".cip").append(cip);

$(".error-url").children().remove();
$(".error-url").append(url);