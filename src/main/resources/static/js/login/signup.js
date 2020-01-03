// 用户名（4到16位，字母数字下划线，减号）：
let usernamePattern = /^[-_a-zA-Z0-9]{4,16}$/;
// 邮箱
let emailPattern = /^[a-zA-Z0-9]{1,10}@[a-zA-Z0-9]{1,5}\.[a-zA-Z0-9]{1,5}$/;
// 密码（最少8位，包括至少一位大写字母，一位小写字母，一个数字，一个特殊字符）：
let passwordPattern = /(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[$@!%*#?&])[A-Za-z\d$@!%*#?&]{8,}$/;

//勾选协议后才可点击按钮
(function () {
    let cb = document.getElementById("agreement");
    cb.onclick = function () {
        if (cb.checked === true) {
            document.getElementById("signup_btn").removeAttribute("disabled");
        } else {
            document.getElementById("signup_btn").disabled = true;
        }
    }
})();

//检查用户名合法性
function checkUsername() {
    let username = $("#username").val();
    if (!usernamePattern.test(username)) {
        $(".username-msg").empty();
        $(".username-msg").append("用户名错误！（4到16位，仅支持字母数字下划线，减号）");
        return false
    }else return  true;
}

// 检查Email的合法性
function checkEmail() {
    let email = $("#email").val();
    return emailPattern.test(email);

}


// 检查两次密码的合法性和一致性
function checkPassword() {
    let pwd1 = $("#password1").val();
    let pwd2 = $("#password2").val();

    if (pwd1 !== pwd2) {
        $(".password-msg").empty();
        $(".password-msg").append("两次密码不一致！");
        return false;
    } else {
        if (!passwordPattern.test(pwd1)){
            $(".password-msg").empty();
            $(".password-msg").append("密码格式错误！（最少8位，包括至少一位大写字母，一位小写字母，一个数字，一个特殊字符：$@!%*#?&）");
            return false
        } else return true

    }

}

//登陆前检查整个表单的正确性
function checkForm() {

    let username = checkUsername();
    let email = checkEmail();
    let password = checkPassword();
    if (username) {
        $(".username-msg").empty();
    }
    if (password) {
        $(".password-msg").empty();
    }
   if (username && email && password) {
       return true;
   }else {
       $('#msg').modal('show');
       return false;
   }
}