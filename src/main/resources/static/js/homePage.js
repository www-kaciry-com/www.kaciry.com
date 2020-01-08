let collectPage = $("#page1");
let followPage = $("#page2");
let contributePage = $("#page");
//获取Token
let token = getCookie("Token");
let username = getCookie("username");
let followContainer = $(".content");
// 密码（最少8位，包括至少一位大写字母，一位小写字母，一个数字，一个特殊字符）：
let passwordPattern = /(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[$@!%*#?&])[A-Za-z\d$@!%*#?&]{8,}$/;

window.onload = queryCollections;

//更改个人信息
function changeInfo() {
    $.ajax({
        url: '/changeInfo', //请求的url
        type: 'post', //请求的方式
        dateType: "json",
        data: $('#user-info').serialize(), //form表单里要提交的内容，里面的input等写上name就会提交，这是序列化
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            alert("Success !changeInfo");
            document.getElementById("email").value = result.userEmail;
            document.getElementById("nickname").value = result.userNickName;
            document.getElementById("mobilephone").value = result.userPhoneNumber;
            document.getElementById("sex").value = result.userSex;
            document.getElementById("userSignature").value = result.userSignature;
        }
    });
}

//查看个人信息
function selectInfo() {
    $.ajax({
        url: '/selectInfo', //请求的url
        type: 'post', //请求的方式
        data: {
            username: username
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            // alert("Success ! selectInfo");
            document.getElementById("email").value = result.userEmail;
            document.getElementById("nickname").value = result.userNickName;
            document.getElementById("mobilephone").value = result.userPhoneNumber;
            document.getElementById("sex").value = result.userSex;
            document.getElementById("userSignature").value = result.userSignature;
        }
    });
}

//获取我的投稿
function selectContribution() {
    let contributeTag = $('.My-Contribution-content');
    $.ajax({
        url: '/selectMyVideo', //请求的url
        type: 'post', //请求的方式
        data: {
            username: username,
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            contributeTag.children().remove();
            let isInited = contributePage.pagination();
            if (!isInited) {
                $("#page").pagination({
                    pageIndex: 0,
                    pageSize: 16,
                    total: result.total,
                    debug: false,
                    showInfo: true,
                    showJump: true,
                    showPageSizes: true,
                    showFirstLastBtn: true,
                    firstBtnText: '首页',
                    lastBtnText: '尾页',
                    prevBtnText: '上一页',
                    nextBtnText: '下一页',
                    jumpBtnText: '跳转',
                    pageElementSort: ['$page', '$jump', '$info'],
                    infoFormat: '{start}~{end},共{total}个投稿',
                });
            }
            let json = eval(result.list);
            let str = analysisData(json, "Contribution");
            contributeTag.append(str);
        }
    });
}

//分析数据
function analysisData(data, ops) {
    let str = '';
    $.each(data, function (i, element) {
        str += "<li class=\"col-6 col-md-3 items\" style=\"display: block\">\n" +
            "                            <span hidden>" + element.videoFilename + "</span>\n" +
            "                            <span hidden>" + ops + "</span>\n" +
            "                            <a href=\"/video?videoid=" + element.videoFilename + "  \" target=\"_blank\" class=\"cover\">\n" +
            "                                <div class=\"cover\"><img src=\"/files/videoCover/" + element.videoCover + "\" alt=\"cover\" class=\"videoCover\"></div>\n" +
            "                            </a>\n" +
            "                            <a href=\"\" target=\"_blank\" title=\"\" class=\"title\">" + element.videoName + "</a>\n" +
            "                            <div class=\"row meta justify-content-between\" style='margin-left: 0;margin-right: 0'>\n" +
            "                                <div>\n" +
            "                                <span class=\"play\"><i class=\"icon\">" + element.videoPlayNum + "</i></span>\n" +
            "                                <span class=\"time\"><i class=\"icon\">" + element.videoData.substring(0, 11) + "</i></span>\n" +
            "                                </div>" +
            "                                <div class=\"btn-group\">\n" +
            "                                      <a tabindex=\"0\" data-toggle=\"dropdown\" data-trigger=\"focus\" style=\"border-radius: 15%;margin-left: 15px\">\n" +
            "                                            <i class=\"iconfont\" style='color: #999999'> </i>\n" +
            "                                      </a>\n" +
            "                                                    <span class=\"dropdown-menu\">\n" +
            "                                            <a class=\"dropdown-item dropdown-delete\" href=\"#\" data-toggle=\"modal\" onclick=\"removeVideo(this)\">移除</a>\n" +
            "                                        </span>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                        </li>";
    });
    return str;
}


function removeVideo(btn) {
    let parentNode = btn.parentElement.parentElement.parentElement.parentElement;
    let nodeText = parentNode.children[0].innerHTML;
    let ops = parentNode.children[1].innerHTML;
    //去掉前后空格！！！！！！！！！！！！！！！！！！巨坑(已修复)
    let videoFileName = $.trim(nodeText);
    //判断当前操作
    if (ops === "Collect") {
        $.ajax({
            url: '/opsStar',//请求的地址
            type: 'post', //请求的方式
            dateType: "json", //请求的数据格式
            data: {
                username: username,
                videoFilename: videoFileName,
                option: "collect",
            },
            error: function () {
                showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
            },
            success: function (result) {
                if (!result) {
                    showNoticeModal("提示", "移除成功！")
                } else {
                    showNoticeModal("提示", "移除失败，请稍后重试！")
                }
            }
        })
    } else if (ops === "Contribution") {
        $.ajax({
            url: '/removeVideo',//请求的地址
            type: 'post', //请求的方式
            dateType: "json", //请求的数据格式
            data: {
                videoFilename: videoFileName
            },
            error: function () {
                showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
            },
            success: function (result) {
                //let json = eval(result);
                //$.each(json, function (i, element) {
                // })
                showNoticeModal("成功", result.data);
                parentNode.remove();
            }
        })
    }

}


function analysisFollowsData(data) {
    let str = "";
    $.each(data, function (i, ele) {
        str += "<div class=\"follow-item\">\n" +
            "                            <div class=\"media\">\n" +
            "                                <img class=\"align-self-start mr-3 follow-img\" src=\"" + ele.userHeadIcon + "\" alt=\"Generic placeholder image\">\n" +
            "                                <div class=\"media-body\">\n" +
            "                                    <p class=\"username-data\" hidden=\"hidden\">" + ele.username + "</p>\n" +
            "                                    <h5 class=\"mt-0 follow-nickname\">" + ele.userNickName + "</h5>\n" +
            "                                    <p class=\"follow-signature\">" + ele.userSignature + "</p>\n" +
            "                                </div>\n" +
            "                                <div class=\"follow-check\">\n" +
            "                                    <button class=\"btn btn-secondary btn-sm\" type=\"button\" onclick=\"cancelFollow(this)\">取消关注</button>\n" +
            "                                    <button class=\"btn btn-primary btn-sm\" type=\"button\" onclick=\"toSendMsg()\">发送消息</button>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "                            <hr>\n" +
            "                        </div>";
    });

    return str;
}

//更改密码
function changePassword() {
    let originPassword = $("#origin-password").val();
    let newPasswordOne = $("#new-password-1").val();
    let newPasswordTwo = $("#new-password-2").val();
    if (checkPassword(newPasswordOne, newPasswordTwo)) {
        $.ajax({
            url: '/changePassword',//请求的地址
            type: 'post', //请求的方式
            dateType: "json", //请求的数据格式
            data: {
                username: username,
                originPassword: originPassword,
                password: newPasswordOne
            },
            error: function () {
                showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
            },
            success: function (result) {
                showNoticeModal("提示！", result.data);
            }
        })
    }
    return false;
}

// 检查两次密码的合法性和一致性
function checkPassword(pwd1, pwd2) {
    if (pwd1 !== pwd2) {
        showNoticeModal("错误提示！", "两次输入的密码不一致！");
        return false;
    } else {
        if (!passwordPattern.test(pwd1)) {
            showNoticeModal("错误提示！", "密码格式错误！（最少8位，包括至少一位大写字母，一位小写字母，一个数字，一个特殊字符：$@!%*#?&）");
            return false
        } else return true
    }
}

// 查询我的收藏
function queryCollections() {
    let collectionsTag = $('.My-Collections');
    $.ajax({
        url: '/postQueryCollect', //请求的url
        type: 'post', //请求的方式
        async: false,
        data: {
            username: username,
            pageNum: 1,
            pageSize: 16
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            collectionsTag.children().remove();
            let isInited = collectPage.pagination();
            if (!isInited) {
                collectPage.pagination({
                    pageIndex: 0,
                    pageSize: 16,
                    total: result.total,
                    debug: false,
                    showInfo: true,
                    showJump: true,
                    showPageSizes: true,
                    showFirstLastBtn: true,
                    firstBtnText: '首页',
                    lastBtnText: '尾页',
                    prevBtnText: '上一页',
                    nextBtnText: '下一页',
                    jumpBtnText: '跳转',
                    pageElementSort: ['$page', '$jump', '$info'],
                    infoFormat: '{start}~{end},共{total}个投稿',
                });
            }
            let json = eval(result.list);
            let str = analysisData(json, "Collect");
            collectionsTag.append(str);
        }
    });
}

//查询关注
function queryFollows() {
    followContainer.children().remove();

    $.ajax({
        url: '/postQueryFollows',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            username: username,
            pageNum: 1,
            pageSize: 10
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            let isInited = followPage.pagination();
            if (!isInited) {
                followPage.pagination({
                    pageIndex: 0,
                    pageSize: 10,
                    total: result.total,
                    debug: false,
                    showInfo: true,
                    showJump: true,
                    showPageSizes: true,
                    showFirstLastBtn: true,
                    firstBtnText: '首页',
                    lastBtnText: '尾页',
                    prevBtnText: '上一页',
                    nextBtnText: '下一页',
                    jumpBtnText: '跳转',
                    pageElementSort: ['$page', '$jump', '$info'],
                    infoFormat: '{start}~{end},共{total}个投稿',
                });
            }
            followContainer.append(analysisFollowsData(result.list));
        }
    })
}

// 翻页控制
contributePage.on("pageClicked", function (event, data) {
    let contributeTag = $('.My-Contribution-content');
    $.ajax({
        url: "/selectMyVideo",
        type: "POST",
        dataType: "json",
        data: {
            token: token,
            username: username,
            pageNum: data.pageIndex + 1,
            pageSize: 16
        },
        success: function (result) {
            contributeTag.children().remove();
            contributeTag.append(analysisData(eval(result.list), "Contribution"));
        }
    })
});

contributePage.on("jumpClicked", function (event, data) {
    let contributeTag = $('.My-Contribution-content');
    $.ajax({
        url: "/selectMyVideo",
        type: "POST",
        dataType: "json",
        data: {
            token: token,
            username: username,
            pageNum: data.pageIndex + 1,
            pageSize: 16
        },
        success: function (result) {

            contributeTag.children().remove();
            let json = eval(result.list);
            let str = analysisData(json, "Contribution");
            contributeTag.append(str);
        }
    });
});

collectPage.on("pageClicked", function (event, data) {

    let collectionsTag = $('.My-Collections');

    $.ajax({
        url: "/postQueryCollect",
        type: "POST",
        dataType: "json",
        data: {
            token: token,
            username: username,
            pageNum: data.pageIndex + 1,
            pageSize: 16
        },
        success: function (result) {
            collectionsTag.children().remove();
            let json = eval(result.list);
            let str = analysisData(json, "Collect");
            collectionsTag.append(str);

        }
    })
});

collectPage.on("jumpClicked", function (event, data) {
    let collectionsTag = $(".My-Collections");
    $.ajax({
        url: "/postQueryCollect",
        type: "POST",
        dataType: "json",
        data: {
            token: token,
            username: username,
            pageNum: data.pageIndex + 1,
            pageSize: 16
        },
        success: function (result) {
            collectionsTag.children().remove();
            let json = eval(result.list);
            let str = analysisData(json, "Collect");
            collectionsTag.append(str);
        }
    });
});

followPage.on("pageClicked", function (event, data) {

    let followsTag = $('.content');

    $.ajax({
        url: "/postQueryFollows",
        type: "POST",
        dataType: "json",
        data: {
            token: token,
            username: username,
            pageNum: data.pageIndex + 1,
            pageSize: 10
        },
        success: function (result) {
            followsTag.children().remove();
            followContainer.append(analysisFollowsData(result.list));

        }
    })
});

followPage.on("jumpClicked", function (event, data) {
    let followsTag = $('.content');
    $.ajax({
        url: "/postQueryFollows",
        type: "POST",
        dataType: "json",
        data: {
            token: token,
            username: username,
            pageNum: data.pageIndex + 1,
            pageSize: 10
        },
        success: function (result) {
            followsTag.children().remove();
            followContainer.append(analysisFollowsData(result.list));
        }
    });
});

//取消关注
function cancelFollow(btn) {
    let res = btn.parentElement.parentElement.children[1].children[0];
    let curNode = btn.parentElement.parentElement.parentElement;
    $.ajax({
        url: '/followHim',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            username: username,
            hisUsername: res.innerHTML
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function () {
            curNode.remove()
        }
    })
}

//跳转到聊天界面
function toSendMsg() {
    window.location.href = "/reply#privateLetters";
}

//获取cookie的value
function getCookie(cookie_name) {
    if (document.cookie.length > 0) {//判断cookie是否存在
        //获取cookie名称加=的索引值
        let c_start = document.cookie.indexOf(cookie_name + "=");
        if (c_start != -1) { //说明这个cookie存在
            //获取cookie名称对应值的开始索引值
            c_start = c_start + cookie_name.length + 1;
            //从c_start位置开始找第一个分号的索引值，也就是cookie名称对应值的结束索引值
            c_end = document.cookie.indexOf(";", c_start);
            //如果找不到，说明是cookie名称对应值的结束索引值就是cookie的长度
            if (c_end == -1) c_end = document.cookie.length;
            //unescape() 函数可对通过 escape() 编码的字符串进行解码
            //获取cookie名称对应的值，并返回
            return unescape(document.cookie.substring(c_start, c_end))
        }
    }
    return "" //不存在返回空字符串
}

//模态框
function showNoticeModal(title, body) {
    $("#noticeModalTitle").text(title);
    $("#notice-modal-body").text(body);
    $('#noticeModal').modal('toggle');
}

//模态框消失时自动清空标题和内容，以便下次调用
$('#noticeModalTitle').on('hidden.bs.modal', function (e) {
    $("#noticeModalTitle").text("");
    $("#notice-modal-body").text("");
});



