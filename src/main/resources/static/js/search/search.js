let keyword = GetQueryString("information");
$(document).ready(function () {

    $.ajax({
        url: '/searchMsg',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            pageNum: 1,
            pageSize: 20,
            keyword: keyword,
        },
        error: function () {
            alert("服务器未响应，加载视频信息失败！");
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
            let json = eval(result.list);
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
        },
        success: function (result) {

            $('.video-items').children().remove();
            let json = eval(result.list);
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
        },
        success: function (result) {

            $('.video-items').children().remove();
            let json = eval(result.list);
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


//解决了中文乱码的问题
function GetQueryString(name) {
    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    let r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}
