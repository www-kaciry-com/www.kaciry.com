let token = getCookie("Token");
let username = getCookie("username");
let waitTime = "";

$(document).ready(function () {
    let videoSelector = $("#choice-video-options");
    $.ajax({
        url: '/selectNormalVideo',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            username: username
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            let json = eval(result);
            let str = analysisData(json);
            videoSelector.append(str);
        }
    })
});

function selectorChange() {
    let videoCover = $(".video-cover");
    let videoUsername = $("#video-username");
    let videoTitle = $("#video-title");
    let videoType = $("#video-type");
    let videoDescription = $("#video-description");
    let videoDate = $("#video-date");
    let videoName = $("#video-name");
    let videoPlayNum = $("#video-play-num");
    let videoAddress = $("#choice-video-options option:selected").val();
    let videoSource = $(".video-source");

    if (videoAddress !== "0") {
        $.ajax({
            url: '/initVideo',//请求的地址
            type: 'post', //请求的方式
            dateType: "json", //请求的数据格式
            data: {
                token: token,
                username: username,
                videoAddress: videoAddress
            },
            error: function () {
                showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
            },
            success: function (result) {
                videoCover.attr("src", "/files/videoCover/" + result.videoCover);
                videoUsername.val("    " + result.username);
                videoTitle.val("    " + result.videoTitle);
                videoType.val("    " + result.videoType);
                videoDescription.val("    " + result.videoDescription);
                videoDate.val("    " + result.videoData);
                videoName.val("    " + result.videoName);
                videoPlayNum.val("    " + result.videoPlayNum);
                //必须更改video标签的src属性，更改source的没用,放在结尾执行，否者下面的语句不执行
                videoSource.attr("src", "/files/video/" + result.videoFilename);
            }
        })
    } else {
        videoCover.attr("src", "/static/img/nav_wrapper.jpg");
        videoUsername.val("    选择视频后在此显示用户名称");
        videoTitle.val("    选择视频后在此显示视频标题");
        videoType.val("    选择视频后在此显示视频类型");
        videoDescription.val("    选择视频后在此显示视频描述");
        videoDate.val("    选择视频后在此显示视频投稿时间");
        videoName.val("    选择视频后在此显示视频名称");
        videoPlayNum.val("    选择视频后在此显示视频播放数量");
    }
    return false;
}

function queryWaitTime() {
    let options = $("#promote-type-options option:selected").val();
    if ($("#choice-video-options option:selected").val() !== "0") {

        $.ajax({
            url: "/queryWaitTime",//请求的地址
            type: 'post', //请求的方式
            dateType: "json",
            data: {
                options: options
            },
            error: function () {
                showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
            },
            success: function (result) {
                waitTime = result.data;
                $(".msg-show").text("预计等待： " + waitTime);
            }
        });
        $('#payModal').modal('toggle');
    } else {
        showNoticeModal("提示", "请选择一个您想要推广的视频！");
    }
    return false;
}

function promoteVideo() {
    $('#payModal').modal('hide');
    let promoteOption = $("#promote-type-options option:selected").val();
    let promoteVideo = $("#choice-video-options option:selected").val();
    $.ajax({
        url: '/promoteVideo',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        sync: false,
        data: {
            videoFilename: promoteVideo,
            promoteType: promoteOption
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            showNoticeModal("提示", result.data);
        }
    })
}

$('#payModal').on('hidden.bs.modal', function () {
    $(".msg-show").text();
});

function analysisData(data) {
    let str = "";
    $.each(data, function (i, element) {
        str += "<option value=\"" + element.videoFilename + "\">" + element.videoName + "</option>";
    });
    return str;
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
