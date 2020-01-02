function checkEmptyArgs() {
    let username = $("#username").val();
    let password = $("#password").val();
    let flg = $(".signin_btn").hasOwnProperty("disabled");

    if (username.length === 0 || password.length === 0) {
        if (!flg) {
            $('.signin_btn').attr("disabled", true);
        }
    } else {
        $('.signin_btn').removeAttr("disabled");
    }
}

function trySend() {
    let username = $("#username").val();
    let password = $("#password").val();
    $.ajax({
        url: '/rsaKey1',
        type: 'post',
        dateType: "json",
        async: false,
        data: {
            username: username
        },
        success: function (data) {
            let pwdKey = new RSAUtils.getKeyPair(data.exponent, "", data.modulus);
            let reversedPwd = password.split("").reverse().join("");
            let encryptedPwd = RSAUtils.encryptedString(pwdKey, reversedPwd);
            $.ajax({
                url: '/rsaKey2',
                type: 'post',
                dateType: "json",
                async: false,
                data: {
                    username: username,
                    password: encryptedPwd,
                    checkbox: $("#styled-checkbox-2").is(':checked')
                },
                success:function (data) {
                    if (data.data === "success"){
                        $.ajax({
                            url: '/getIPAddress',//请求的地址
                            type: 'post', //请求的方式
                            dateType: "json", //请求的数据格式
                            data: {
                                ip: returnCitySN["cip"],
                                city: returnCitySN["cname"]
                            },
                            success: function () {
                                console.log("success");
                            },
                            error: function () {
                                console.log(error);
                            }
                        });
                        window.location.href = "index.html"
                    }else {
                        document.getElementsByClassName("notice-p")[0].innerHTML = "";
                        $(".notice-p").append(data.data);
                    }
                },
                error:function () {
                    console.log("fail2");
                }
            });
        },
        error:function () {
            console.log("fail1");
        }
    });
    return false;
}



