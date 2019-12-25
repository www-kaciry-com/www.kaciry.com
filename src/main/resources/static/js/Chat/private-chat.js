let token = getCookie("Token");
let senderID = getCookie("username");
let userChatID = 10;
let currentUser;
let friends, chat;

$(document).ready(function () {
    $.ajax({
        url: '/getChatList',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        async: false,
        data: {
            username: senderID
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            let json = eval(result);
            let res = analysisChatListData(json);
            $(".people").append(res);
            res = analysisChatDetailsData(json);
            $(".detail-chat").append(res);
            currentUser = json[0].username;
            $(".chat[data-chat=" + json[0].username + "]").addClass('active-chat');
            $(".person[data-chat=" + json[0].username + "]").addClass('active');

            friends = {
                // list: document.querySelector('ul.people'),
                // all: document.querySelectorAll('.left .person'),
                list: document.getElementsByClassName('ul.people')[0],
                all: $('.left .person'),
                name: ''
            };

            chat = {
                container: document.getElementsByClassName('.container .right')[0],
                current: null,
                person: null,
                name: document.getElementsByClassName('.container .right .top .name')[0]
            };

            friends.all.forEach(function (f) {
                f.addEventListener('mousedown', function () {
                    f.classList.contains('active') || setActiveChat(f);
                });
            });
        }
    })
});

$(document).ready(function () {
    $.ajax({
        url: '/getPrivateMsg',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            senderIdentityDocument: senderID,
            receiverIdentityDocument: currentUser
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            let json = eval(result);
            let str = analysisData(json);
            // console.log("getPrivateMsg:  " + currentUser);
            $("div[data-chat='" + currentUser + "']").append(str);
        }
    });
});

$(document).ready(function () {
    window.setInterval("getNewMsg()", 3000);
});

function analysisChatListData(data) {
    let str = "";
    $.each(data, function (i, element) {
        str += "<li class=\"person\" data-chat=\"" + element.username + "\">\n" +
            "                    <img src=\"" + element.userHeadIcon + "\" alt=\"\"/>\n" +
            "                    <span class=\"name\">" + element.userNickName + "</span>\n" +
            "                    <span class=\"time\">2:09 PM</span>\n" +
            "                    <span class=\"preview\">I was wondering...</span>\n" +
            "                </li>";
    });
    return str;
}

function analysisChatDetailsData(data) {
    let str = "";
    $.each(data, function (i, ele) {
        str += "<div class=\"chat\" data-chat=\"" + ele.username + "\">\n" +
            "                    <div class=\"conversation-start\">\n" +
            "                        <span>Today, 6:48 AM</span>\n" +
            "                    </div>\n" +
            "                </div>";
    });
    return str;
}

/**
 * @author kaciry
 * @description  发送私信
 * @date  2019/10/30 13:39
 * @return
 **/
function sendMsg() {
    let msg = $("#msg-input").val();
    $.ajax({
        url: '/privateChat',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            //发送方ID
            senderIdentityDocument: senderID,
            //接收方ID
            receiverIdentityDocument: currentUser,
            //内容
            content: msg,
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            //清空输入框
            $("#msg-input").val("");
            if (result.code === 200) {
                $("div[data-chat='" + currentUser + "']").append("<div class=\"bubble me\">\n" +
                    "                    " + result.data.content + "\n" +
                    "                </div>");
            } else {
                $("div[data-chat='" + currentUser + "']").append("<div class=\"bubble me\">\n" +
                    "                    " + result.msg + "\n" +
                    "                </div>");
            }

        }
    })
}

/**
 * @author kaciry
 * @description  分析需要添加的数据
 * @date  2019/10/30 13:39
 * @param data
 * @return
 **/
function analysisData(data) {
    let str = "";
    $.each(data, function (i, elem) {
        if (elem.senderIdentityDocument === senderID) {
            str += " <div class=\"bubble me\">\n" +
                "                    " + elem.content + "\n" +
                "                </div>";
        } else if (elem.senderIdentityDocument === currentUser) {
            str += " <div class=\"bubble you\">\n" +
                "                    " + elem.content + "\n" +
                "                </div>";
        }
        userChatID = elem.userChatIdentityDocument;
    });
    return str;
}

/**
 * @author kaciry
 * @description  获取新私信
 * @date  2019/10/30 13:41
 * @return
 **/
function getNewMsg() {
    $.ajax({
        url: '/getNewMsg',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            senderIdentityDocument: currentUser,
            receiverIdentityDocument: senderID,
            userChatIdentityDocument: userChatID
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            let json = eval(result);
            let str = analysisData(json);
            $("div[data-chat='" + currentUser + "']").append(str);
        }
    });
}

function sendPicture() {

}

function getPicture() {

}

function setActiveChat(f) {
    friends.list.querySelector('.active').classList.remove('active');
    f.classList.add('active');
    chat.current = chat.container.querySelector('.active-chat');
    chat.person = f.getAttribute('data-chat');
    chat.current.classList.remove('active-chat');
    chat.container.querySelector('[data-chat="' + chat.person + '"]').classList.add('active-chat');
    currentUser = chat.person;
    friends.name = f.querySelector('.name').innerText;
    chat.name.innerHTML = friends.name;
}

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
