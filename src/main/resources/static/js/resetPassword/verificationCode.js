//vCode为请求完成后返回的正确验证码，全局变量
let vCode;
//email为要修改信息的用户的索引
let email = "";
//页面加载完成时，第一步指示变色
$(document).ready(function () {
    stepSetColor(0);
});

//步骤变色（添加）
function stepSetColor(steps) {
    $(".steps-icon:eq(" + steps + ")").css({
        "color": "#f25d8e",
        "border": "2px solid #f25d8e"
    });

    $(".des:eq(" + steps + ")").css("color", "#f25d8e");
    //阻止默认行为
    return false;
}

//步骤变色（去除）
function stepCancelColor(steps) {
    $(".steps-icon:eq(" + steps + ")").css({
        "color": "#99a2aa",
        "border": "2px solid #99a2aa"
    });

    $(".des:eq(" + steps + ")").css("color", "#99a2aa");
    //阻止默认行为
    return false;
}

//发送验证码请求
function sendCode() {
    //获取页面输入的Email地址
    let receiver = $('#email').val();
    //将其赋给全局变量email，以便修改密码时作为索引
    email = receiver;
    //异步请求
    $.ajax({
        //请求的地址
        url: '/postResetPwd',
        //请求的方式
        type: 'post',
        //请求的数据格式
        dateType: 'json',
        //请求的数据
        data: {
            receiver: receiver
        },
        //错误响应提示
        error: function () {
            alert('服务器未响应，请重试！');
        },
        //正确接收返回值
        success: function (result) {
            //不等于false时就是正确返回了验证码
            if (result !== "false") {
                //返回值为String，强制转型为int
                vCode = parseInt(result);
                //隐藏上一个form表格
                $('#input-email').attr('hidden', true);
                //显示校验验证码form表格
                $('#input-code').removeAttr('hidden');
                stepCancelColor(0);
                stepSetColor(1);
            } else {
                //失败提示，刷新页面
                alert('发送验证码失败，请重试！');
                window.location.reload();
            }
        }
    });
    //阻止默认行为
    return false;

}

//校验验证码是否正确
function checkCode() {
    //获取输入的验证码，强制转为int以便比较
    let value = parseInt($("#v-code").val());
    //与全局变量中的vCode比较，===严格相等
    if (value === vCode) {
        //如果正确，隐藏当前form表单
        $("#input-code").attr("hidden", true);
        //显示更改密码表单
        $("#reset-password").removeAttr("hidden");
        //取消第二部的指示，显示第三部的指示
        stepCancelColor(1);
        stepSetColor(2);
    } else {
        //错误提示
        alert("验证码错误！");
    }
    //阻止默认行为
    return false;

}

function resetPassword() {
    //获取两次输入的密码
    let pwd1 = $("#password-1").val();
    let pwd2 = $("#password-2").val();
    //比较两次输入的密码是或否一致
    if (pwd1 === pwd2) {
        //一致，执行重置密码请求
        $.ajax({
            //请求的地址
            url: '/postUpdatePwd',
            //请求的方式
            type: 'post',
            //请求的数据格式
            dateType: "json",
            //请求的数据
            data: {
                email: email,
                password: pwd1,
            },
            //请求失败提示
            error: function () {
                alert("服务器未响应，修改信息失败！");
            },
            //请求成功，返回结果
            success: function (result) {
                //返回类型为布尔类型
                if (result) {
                    alert("修改成功");
                    //清空内容
                    $("#password-1").attr("value", '');
                    //清空内容
                    $("#password-2").attr("value", '');
                } else {
                    //为false，提示修改失败
                    alert("修改失败，请稍后重试！");
                }

            }
        });
    } else {
        //若密码不一致，提示用户
        alert('密码不一致，请重新输入！');
        //清空两个输入框的内容
        $("#password-1").attr("value", '');//清空内容
        $("#password-2").attr("value", '');//清空内容
    }
    //阻止默认行为
    return false;
}