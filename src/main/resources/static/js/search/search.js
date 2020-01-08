let keyword = GetQueryString("information");
let type = GetQueryString("k");
let username = getCookie("username");
if (!type && typeof (type) != "undefined" && type !== 0) {
    type = "video";
}
$(document).ready(function () {
    $(".search-input input").val(keyword);
    $.ajax({
        url: '/searchMsg',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            pageNum: 1,
            pageSize: 20,
            keyword: keyword,
            type: type,
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            $('.video-items').children().remove();
            let isInited = $("#page").pagination();
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
            let json = eval(result.data);
            let str = analysisData(json);
            $('.video-items').append(str);
        }
    })
});


$("#page").on("pageClicked", function (event, data) {
    $.ajax({
        url: "/searchMsg",
        type: "POST",
        dataType: "json",
        data: {
            pageNum: data.pageIndex + 1,
            pageSize: 20,
            keyword: keyword,
            type: type,
        },
        success: function (result) {

            $('.video-items').children().remove();
            let json = eval(result.data);
            let str = analysisData(json);
            $('.video-items').append(str);

        }
    })
});

$("#page").on("jumpClicked", function (event, data) {

    $.ajax({
        url: "/searchMsg",
        type: "POST",
        dataType: "json",
        data: {
            pageNum: data.pageIndex + 1,
            pageSize: 20,
            keyword: keyword,
            type: type,
        },
        success: function (result) {

            $('.video-items').children().remove();
            let json = eval(result.data);
            let str = analysisData(json);
            $('.video-items').append(str);
        }
    });
});

function analysisData(data) {
    let str = '';
    $.each(data, function (i, element) {
        str += "<li class=\"video-item\">\n" +
            "                        <div class=\"cover\">\n" +
            "                            <a href=\"/video?videoid=" + element.videoFilename + " \"><img src=\"/files/videoCover/" + element.videoCover + "\" alt=\"Cover\"></a>\n" +
            "                        </div>\n" +
            "                        <div class=\"info\">\n" +
            "                            <p class=\"title\">" + element.videoName + "</p>\n" +
            "                            <div class=\"tags\">\n" +
            "                                <div class=\"playNum\">\n" +
            "                                    <img src=\"/static/img/searchIcon/img/play.png\" alt=\"playnum\">\n" +
            "                                    <span>" + element.videoPlayNum + "</span>\n" +
            "                                </div>\n" +
            "                                <div class=\"up-data\">\n" +
            "                                    <img src=\"/static/img/searchIcon/img/time.png\" alt=\"\">\n" +
            "                                    <span>" + element.videoData.substring(0, 11) + "</span>\n" +
            "                                </div>\n" +
            "                            </div>\n" +
            "\n" +
            "                            <div class=\"uper\">\n" +
            "                                <a href=\"#\"><img src=\"/static/img/searchIcon/img/user.png\" alt=\"user\"></a>\n" +
            "                                <span>" + element.userNickName + "</span>\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                    </li>";

    });
    return str;
}

function searchUserInfo() {
    $.ajax({
        url: '/searchMsg',//请求的地址
        type: 'POST', //请求的方式
        dateType: "JSON", //请求的数据格式
        data: {
            pageNum: 1,
            pageSize: 20,
            keyword: $(".search-input input").val(),
            type: 'user',
        },
        error: function () {
            alert("服务器未响应，加载视频信息失败！");
        },
        success: function (result) {
            $('#users').children().remove();
            let json = eval(result.data);
            let str = '';
            $.each(json, function (i, element) {
                str += "<div class=\"follow-item\">\n" +
                    "                <div class=\"media\">\n" +
                    "                    <img class=\"align-self-start mr-3 follow-img\" src=\"" + element.userHeadIcon + "\" alt=\"Generic placeholder image\">\n" +
                    "                    <div class=\"media-body\">\n" +
                    "                        <p class=\"username-data\" hidden=\"hidden\">" + element.username + "</p>\n" +
                    "                        <h5 class=\"mt-0 follow-nickname\">" + element.userNickName + "</h5>\n" +
                    "                        <p class=\"follow-signature\">" + element.userSignature + "</p>\n" +
                    "                    </div>\n" +
                    "                    <div class=\"follow-check\">\n" +
                    "                        <button class=\"btn btn-primary btn-sm\" data-id='" + element.username + "' type=\"button\" onclick=\"followHim(this)\">关注</button>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "                <hr>\n" +
                    "            </div>";
            });
            $("#users").append(str);
        }
    })
}

//取消关注
function followHim(btn) {
    let res = btn.parentElement.parentElement.children[1].children[0];
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
        success: function (result) {
            $("[data-id = " + res.innerHTML + "]").text(result.msg);
            if (result.code === 200) {
                showNoticeModal("提示", "关注成功，请到我的关注中查看！");
            } else {
                showNoticeModal("提示", "取消关注成功！");
            }
        }
    })
}

//解决了中文乱码的问题
function GetQueryString(name) {
    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    let r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}

//模态框
function showNoticeModal(title, body) {
    $("#noticeModalTitle").text(title);
    $("#notice-modal-body").text(body);
    $('#noticeModal').modal('toggle');
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

//模态框消失时自动清空标题和内容，以便下次调用
$('#noticeModalTitle').on('hidden.bs.modal', function (e) {
    $("#noticeModalTitle").text("");
    $("#notice-modal-body").text("");
});

//替换指定传入参数的值,paramName为参数,replaceWith为新值
function replaceParamVal(paramName, replaceWith) {
    let oUrl = this.location.href.toString();
    let re = eval('/(' + paramName + '=)([^&]*)/gi');
    let nUrl = oUrl.replace(re, paramName + '=' + replaceWith);
    this.location = nUrl;
    window.location.href = nUrl
}