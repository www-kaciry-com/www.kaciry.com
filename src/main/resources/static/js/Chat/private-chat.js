let senderID = $("#senderID").val();
let receiverID = $(".receiverID").text();
let userChatID = "";
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
            receiverIdentityDocument: receiverID,
            //内容
            content: msg,
        },
        error: function () {
            alert("服务器未响应，加载信息失败！");
        },
        success: function (result) {
            if (result.code === 200) {
                $("div[data-chat='person1']").append("<div class=\"bubble me\">\n" +
                    "                    " + result.data.content + "\n" +
                    "                </div>");
            } else {
                $("div[data-chat='person1']").append("<div class=\"bubble me\">\n" +
                    "                    " + result.msg + "\n" +
                    "                </div>");
            }

        }
    })
}

function analysisData(data) {
    let str = "";
    $.each(data, function (i, elem) {
        if (elem.senderIdentityDocument === senderID) {
            str += " <div class=\"bubble me\">\n" +
                "                    " + elem.content + "\n" +
                "                </div>";
        } else if (elem.senderIdentityDocument === receiverID) {
            str += " <div class=\"bubble you\">\n" +
                "                    " + elem.content + "\n" +
                "                </div>";
        }
        userChatID = elem.userChatIdentityDocument;
    });
    return str;
}

$(document).ready(function () {
    window.setInterval("getNewMsg()",3000);

});

$(document).ready(function () {
    $.ajax({
        url: '/getPrivateMsg',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            senderIdentityDocument: senderID,
            receiverIdentityDocument: receiverID
        },
        error: function () {
            alert("服务器未响应，加载信息失败！");
        },
        success: function (result) {
            let json = eval(result);
            let str = analysisData(json);
            $("div[data-chat='person1']").append(str);
        }
    });
});

function getNewMsg() {
    $.ajax({
        url: '/getNewMsg',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            senderIdentityDocument: receiverID,
            userChatIdentityDocument : userChatID
        },
        error: function () {
            alert("服务器未响应，加载视频信息失败！");
        },
        success: function (result) {
            let json = eval(result);
            let str = analysisData(json);
            console.log(str);
            $("div[data-chat='person1']").append(str);

        }
    });
    console.log("getNewMsg()运行了一次!");
}

function sendPicture() {

}

function getPicture() {

}

