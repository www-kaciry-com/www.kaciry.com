let username = $("#username").val();
let waitTime = "";

$(document).ready(function () {

    if (typeof (username) == "undefined") {
        window.location.href = "/login";
    }
});

$(document).ready(function () {
    let videoSelector = $("#choice-video-options");
    $.ajax({
        url: '/selectUserVideos',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            username: username
        },
        error: function () {
            alert("服务器未响应，加载信息失败！");
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
                username: username,
                videoAddress: videoAddress
            },
            error: function () {
                alert("服务器未响应，加载信息失败！");
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
    if ($("#choice-video-options option:selected").val() !== "0") {
        $.ajax({
            url: "queryWaitTime",//请求的地址
            type: 'post', //请求的方式
            contentType: "json",
            error: function () {
                alert("服务器未响应，加载信息失败！");
            },
            success: function (result) {
                waitTime = result.data;
                $(".msg-show").text("预计等待： " + waitTime);
            }
        });
        $('#payModal').modal('toggle');
    } else {
        $("#noticeModalTitle").text("提示");
        $("#notice-modal-body").text("请选择一个您想要推广的视频！");
        $('#noticeModal').modal('toggle');
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
            alert("服务器未响应，加载信息失败！");
        },
        success: function (result) {
            $("#noticeModalTitle").text("提示");
            $("#notice-modal-body").text(result.data);
            $('#noticeModal').modal('toggle');
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
