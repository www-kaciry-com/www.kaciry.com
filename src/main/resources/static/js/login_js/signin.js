function checkEmptyArgs() {
    let username = $("#username").val();
    let password = $("#password").val();
    let flg = $(".signin_btn").hasOwnProperty("disabled");

    if (username.length === 0|| password.length === 0) {
        if (!flg) {
            $('.signin_btn').attr("disabled", true);
        }
    } else {
        $('.signin_btn').removeAttr("disabled");
    }

}
