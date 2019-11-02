let senderID = $("#senderIdentityDocument").val();
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
            alert("服务器未响应，加载信息失败！");
        },
        success: function (result) {
            let json = eval(result);
            let res = analysisChatListData(json);
            $(".people").append(res);
            res = analysisChatDetailsData(json);
            $(".detail-chat").append(res);
            currentUser = json[0].username;
            document.querySelector(".chat[data-chat=" + json[0].username + "]").classList.add('active-chat');
            document.querySelector(".person[data-chat=" + json[0].username + "]").classList.add('active');

            friends = {
                list: document.querySelector('ul.people'),
                all: document.querySelectorAll('.left .person'),
                name: ''
            };

            chat = {
                container: document.querySelector('.container .right'),
                current: null,
                person: null,
                name: document.querySelector('.container .right .top .name')
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
    console.log(senderID);
    console.log(currentUser);
    $.ajax({
        url: '/getPrivateMsg',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            senderIdentityDocument: senderID,
            receiverIdentityDocument: currentUser
        },
        error: function () {
            alert("服务器未响应，加载信息失败！");
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
    $.each(data,function (i, ele) {
            str += "<div class=\"chat\" data-chat=\""+ele.username+"\">\n" +
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
            alert("服务器未响应，加载信息失败！");
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
            alert("服务器未响应，加载视频信息失败！");
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

