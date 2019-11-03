let collectPage = $("#page1");
let followPage = $("#page2");
let contributePage = $("#page");
let username = $(".username").val();
let followContainer = $(".content");
// 密码（最少8位，包括至少一位大写字母，一位小写字母，一个数字，一个特殊字符）：
let passwordPattern = /(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[$@!%*#?&])[A-Za-z\d$@!%*#?&]{8,}$/;

window.onload = queryCollections;

function changeInfo() {
    $.ajax({
        url: '/changeInfo', //请求的url
        type: 'post', //请求的方式
        dateType: "json",
        data: $('#user-info').serialize(), //form表单里要提交的内容，里面的input等写上name就会提交，这是序列化
        error: function () {
            alert('请求失败');
        },
        success: function (result) {
            alert("Success !changeInfo");
            document.getElementById("email").value = result.userEmail;
            document.getElementById("nickname").value = result.userNickName;
            document.getElementById("mobilephone").value = result.userPhoneNumber;
            document.getElementById("sex").value = result.userSex;
        }
    });
}

function selectInfo() {
    $.ajax({
        url: '/selectInfo', //请求的url
        type: 'post', //请求的方式
        error: function () {
            alert('请求失败');
        },
        success: function (result) {
            // alert("Success ! selectInfo");
            document.getElementById("email").value = result.userEmail;
            document.getElementById("nickname").value = result.userNickName;
            document.getElementById("mobilephone").value = result.userPhoneNumber;
            document.getElementById("sex").value = result.userSex;
        }
    });
}

function selectContribution() {

    let contributeTag = $('.My-Contribution-content');
    $.ajax({
        url: '/selectMyVideo', //请求的url
        type: 'post', //请求的方式
        data: {
            pageNum: 1,
            pageSize: 16
        },
        error: function () {
            alert('请求失败');
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
            let str = analysisData(json);
            contributeTag.append(str);
        }
    });


}

function analysisData(data) {
    let str = '';
    $.each(data, function (i, element) {
        str += "<li class=\"col-6 col-md-3 items\" style=\"display: block\">\n" +
            "                            <a href=\"/video?videoid=" + element.videoFilename + "  \" target=\"_blank\" class=\"cover\">\n" +
            "                                <div class=\"cover\"><img src=\"/files/videoCover/" + element.videoCover + "\" alt=\"cover\" class=\"videoCover\"></div>\n" +
            "                            </a>\n" +
            "                            <a href=\"\" target=\"_blank\" title=\"\" class=\"title\">" + element.videoName + "</a>\n" +
            "                            <div class=\"meta\">\n" +
            "                                <span class=\"play\"><i class=\"icon\">" + element.videoPlayNum + "</i></span>\n" +
            "                                <span class=\"time\"><i class=\"icon\">" + element.videoData.substring(0, 11) + "</i></span>\n" +
            "                            </div>\n" +
            "                        </li>";
    });
    return str;
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

function changePassword() {
    let originPassword = $("#origin-password").val();
    let newPasswordOne =  $("#new-password-1").val();
    let newPasswordTwo = $("#new-password-2").val();
    if (checkPassword(newPasswordOne,newPasswordTwo)){
        $.ajax({
            url: '/changePassword',//请求的地址
            type: 'post', //请求的方式
            dateType: "json", //请求的数据格式
            data: {
                username: username,
                originPassword : originPassword,
                password : newPasswordOne
            },
            error: function () {
                alert("服务器未响应，修改信息失败！");
            },
            success: function (result) {
                $("#noticeModalTitle").text("提示！");
                $("#notice-modal-body").text(result.data);
                $('#noticeModal').modal('toggle');
            }
        })
    }

    return false;
}
// 检查两次密码的合法性和一致性
function checkPassword(pwd1,pwd2) {
    if (pwd1 !== pwd2) {
        $("#noticeModalTitle").text("错误提示！");
        $("#notice-modal-body").text("两次输入的密码不一致！");
        $('#noticeModal').modal('toggle');
        return false;
    } else {
        if (!passwordPattern.test(pwd1)){
            $("#noticeModalTitle").text("错误提示！");
            $("#notice-modal-body").text("密码格式错误！（最少8位，包括至少一位大写字母，一位小写字母，一个数字，一个特殊字符：$@!%*#?&）");
            $('#noticeModal').modal('toggle');
            return false
        } else return true
    }
}

// 查询数据
function queryCollections() {
    let collectionsTag = $('.My-Collections');
    $.ajax({
        url: '/postQueryCollect', //请求的url
        type: 'post', //请求的方式
        data: {
            pageNum: 1,
            pageSize: 16
        },
        error: function () {
            alert('请求失败');
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
            let str = analysisData(json);
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
            alert("服务器未响应，加载信息失败！");
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
            pageNum: data.pageIndex + 1,
            pageSize: 16
        },
        success: function (result) {
            console.log(result);
            contributeTag.children().remove();
            let json = eval(result.list);
            let str = analysisData(json);
            contributeTag.append(str);

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
            pageNum: data.pageIndex + 1,
            pageSize: 16
        },
        success: function (result) {

            contributeTag.children().remove();
            let json = eval(result.list);
            let str = analysisData(json);
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
            pageNum: data.pageIndex + 1,
            pageSize: 16
        },
        success: function (result) {
            collectionsTag.children().remove();
            let json = eval(result.list);
            let str = analysisData(json);
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
            pageNum: data.pageIndex + 1,
            pageSize: 16
        },
        success: function (result) {
            collectionsTag.children().remove();
            let json = eval(result.list);
            let str = analysisData(json);
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
            alert("服务器未响应，加载信息失败！");
        },
        success: function () {
            curNode.remove()
        }
    })
}

function toSendMsg() {
    //TODO 2019年10月11日14:42:04 转到发送消息界面
    window.location.href = "/reply#privateLetters";
}
