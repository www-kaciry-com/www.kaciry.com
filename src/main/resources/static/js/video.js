let videoAddress = GetQueryString("videoid");
let username = $("#username").val();
let pageComment = $('#pageComment');
let page = $("#page");
$(document).ready(function () {
    // let videoid = document.getElementById("videoid").value;

    let videoURL;
    $.ajax({
        url: '/initVideo',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            username: username,
            videoAddress: videoAddress
        },
        error: function () {
            alert("服务器未响应，加载视频信息失败！");
        },
        success: function (result) {
            // console.log(result);
            if ((result.videoStars / 10000) > 1) {
                document.getElementById("starNum").innerText = Math.round(result.videoStars / 10000.0) + "万";
                if (result.isStar === 1) {
                    $('.ops img').eq(0).attr("src", "/static/img/videoOps/star_after.png");
                }
            } else {
                document.getElementById("starNum").innerText = result.videoStars;
                if (result.isStar === 1) {
                    $('.ops img').eq(0).attr("src", "/static/img/videoOps/star_after.png");
                }
            }
            if ((result.videoCoins / 10000) > 1) {
                document.getElementById("coinNum").innerText = Math.round(result.videoCoins / 10000.0) + "万";
                if (result.isCoin === 1) {
                    $('.ops img').eq(1).attr("src", "/static/img/videoOps/coin_after.png");
                }
            } else {
                document.getElementById("coinNum").innerText = result.videoCoins;
                if (result.isCoin === 1) {
                    $('.ops img').eq(1).attr("src", "/static/img/videoOps/coin_after.png");
                }
            }
            if ((result.videoConnections) / 10000 > 1) {
                document.getElementById("connectionNum").innerText = Math.round(result.videoConnections / 10000.0) + "万";
                if (result.isConnection === 1) {
                    $('.ops img').eq(2).attr("src", "/static/img/videoOps/cn_after.png");
                }
            } else {
                document.getElementById("connectionNum").innerText = result.videoConnections;
                if (result.isConnection === 1) {
                    $('.ops img').eq(2).attr("src", "/static/img/videoOps/cn_after.png");
                }
            }
            if ((result.videoShares / 10000) > 1) {
                document.getElementById("shareNum").innerText = Math.round(result.videoShares / 10000.0) + "万";
            } else {
                document.getElementById("shareNum").innerText = result.videoShares;
            }
            if ((result.videoPlayNum / 10000) > 1) {
                document.getElementById("videoPlayNum").innerText = Math.round(result.videoPlayNum / 10000.0) + "万";
            } else {
                document.getElementById("videoPlayNum").innerText = result.videoPlayNum + "播放";
            }
            //大小写
            if (result.videoBarrages > 999) {
                document.getElementById("barrageNum").innerText = "999+弹幕";
            } else {
                document.getElementById("barrageNum").innerText = result.videoBarrages + "弹幕";
            }
            document.getElementById("videoTitle").innerText = "【" + result.videoType + "】" + result.videoName;
            document.getElementById("info").innerText = result.videoDescription;
            videoURL = "/files/video/" + videoAddress;
            dp = new DPlayer({
                element: document.getElementById('dplayer'),
                //开启键盘控制热键
                hotkey: true,
                //开启截图功能
                screenshot: true,
                //预加载，自动
                preload: 'auto',
                //默认音量，50
                volume: 50,
                //互斥，阻止多个播放器同时播放，当前播放器播放时暂停其他播放器
                mutex: true,
                video: {
                    //视频的路径
                    url: videoURL,
                },
                //弹幕功能，不需要可以删除
                danmaku: {
                    //海量弹幕模式，即使重叠也展示全部弹幕，请注意播放器会记忆用户设置，用户手动设置后即失效
                    unlimited: true,
                    //弹幕库的ID，每个视频的弹幕库不能一样，可以把url作为id
                    id: videoAddress,
                    //这个是官网写好的弹幕接口，可以直接使用，就是不太稳定
                    api: 'https://api.prprpr.me/dplayer/'
                }
            });

        }
    })
});
$(document).ready(function () {

    $.ajax({
        url: '/selectVideoComment', //请求的url
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        // language=JQuery-CSS
        data: {
            videoAddress: videoAddress,
            pageNum: 1,
            pageSize: 10,
        },
        error: function () {
            alert("error");
        },
        success: function (result) {
            let json = eval(result.list);

            let playNum = result.total;
            if ((playNum / 10000.0) >= 1) {
                document.getElementById("playNum").innerHTML = (playNum / 10000.00) + "万";
            } else {
                document.getElementById("playNum").innerHTML = result.total;
            }
            pageComment.children().remove();
            let isInited = page.pagination();
            if (!isInited) {
                page.pagination({
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
                    infoFormat: '{start}~{end}条,共{total}条评论',
                });
            }
            let str = analysisData(json);
            pageComment.append(str);


            return false;
        }
    });
});

function analysisData(data) {
    let str = '';
    $.each(data, function (i, element) {
        str += "                <div class=\"media\">\n" +
            "                    <img class=\"align-self-start mr-3\" src=\"" + element.userHeadIcon + "\" alt=\"HeadIcon\">\n" +
            "                    <div class=\"media-body\">\n" +
            "                        <h5 class=\"mt-0 media-nickname\">" + element.userNickName + "</h5>\n" +
            "                        <p>" + element.content + "</p>\n" +
            "                        <div class=\"media-p\">\n" +
            "                            <span>" + element.sendDate + "</span>\n" +
            "                            <a href=\"#\" class=\"reply-child\">回复</a>\n" +
            "                        </div>\n" +
            "                        <hr class=\"media-hr\">\n" +
            "                    </div>\n" +
            "                    <div class=\"media-bottom\"></div>\n" +
            "                </div>";
    });
    return str;
}

page.on("pageClicked", function (event, data) {
    $.ajax({
        url: "/selectVideoComment",
        type: "POST",
        dataType: "json",
        data: {
            videoAddress: videoAddress,
            pageNum: data.pageIndex + 1,
            pageSize: 10
        },
        success: function (result) {
            pageComment.children().remove();
            let json = eval(result.list);
            let str = analysisData(json);
            pageComment.append(str);
        }
    })
});
page.on("jumpClicked", function (event, data) {

    $.ajax({
        url: "/selectVideoComment",
        type: "POST",
        dataType: "json",
        data: {
            videoAddress: videoAddress,
            pageNum: data.pageIndex + 1,
            pageSize: 10
        },
        success: function (result) {
            pageComment.children().remove();
            let json = eval(result.list);
            let str = analysisData(json);
            pageComment.append(str);
        }
    });
});


function sendComment() {
    let content = document.getElementById("discuss").value;
    let username = document.getElementById("username").value;
    if (content !== "") {
        $.ajax({
            url: '/comment', //请求的url
            type: 'post', //请求的方式
            dateType: "json",
            data: {
                username: username,
                videoAddress: videoAddress,
                content: content,

            },
            error: function () {
                alert("服务器开小差了，请稍后重试!");
            },
            success: function (result) {
                $("#discuss").val("");//清空
                alert("发表成功!");
                // navTools(1);
            }
        });
    } else {
        alert("Content is null msg !");
    }
    return false;
}

$(document).ready(function init() {
    let len = 50;      //默认显示字数
    let ctn = document.getElementById("info");  //获取div对象
    let content = ctn.innerHTML;                   //获取div里的内容

    // alert(content);
    let span = document.createElement("span");     //创建<span>元素
    let a = document.createElement("a");           //创建<a>元素
    span.innerHTML = content.substring(0, len);     //span里的内容为content的前len个字符

    a.innerHTML = content.length > len ? "... 展开" : "";  ////判断显示的字数是否大于默认显示的字数    来设置a的显示
    a.href = "javascript:void(0)";//让a链接点击不跳转

    a.onclick = function () {
        if (a.innerHTML.indexOf("展开") > 0) {      //如果a中含有"展开"则显示"收起"
            a.innerHTML = "<<&nbsp;收起";
            span.innerHTML = content;
        } else {
            a.innerHTML = "... 展开";
            span.innerHTML = content.substring(0, len);
        }
    };
    // 设置div内容为空，span元素 a元素加入到div中
    ctn.innerHTML = "";
    ctn.appendChild(span);
    ctn.appendChild(a);
});

function GetQueryString(name) {

    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    let r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

function clickStar() {
    if (typeof (username) == "undefined") {
        window.location.href = "/login";
    } else {
        $.ajax({
            url: '/opsStar', //请求的url
            type: 'post', //请求的方式
            dateType: "json",
            data: {
                username: username,
                videoFileName: videoAddress,
                option : "star",
            },
            error: function () {
                alert("服务器开小差了，请稍后重试!");
            },
            success: function (result) {
                // console.log(result);

                if (result === true) {
                    $('.ops img').eq(0).attr("src", "/static/img/videoOps/star_after.png");
                    let stars = document.getElementById("starNum");
                    let num;
                    let a = stars.innerText.indexOf("万");
                    if (a !== -1) {
                        num = parseInt(stars.innerText) * 10000 + 1;
                        stars.innerText = Math.round(num / 10000.0) + "万";
                    } else {
                        num = parseInt(stars.innerText) + 1;
                        stars.innerText = num;
                    }
                    // stars.innerHTML = parseInt(stars.innerText) + 1;
                } else {
                    $('.ops img').eq(0).attr("src", "/static/img/videoOps/star_before.png");
                    let stars = document.getElementById("starNum");
                    let num;
                    let a = stars.innerText.indexOf("万");
                    if (a !== -1) {
                        num = parseInt(stars.innerText) * 10000 - 1;
                        stars.innerText = Math.round(num / 10000.0) + "万";
                    } else {
                        num = parseInt(stars.innerText) - 1;
                        stars.innerText = num;
                    }

                }

            }
        });
    }

    return false;
}
function clickCollect() {
    if (typeof (username) == "undefined") {
        window.location.href = "/login";
    } else {
        $.ajax({
            url: '/opsStar', //请求的url
            type: 'post', //请求的方式
            dateType: "json",
            data: {
                username: username,
                videoFileName: videoAddress,
                option : "collect",
            },
            error: function () {
                alert("服务器开小差了，请稍后重试!");
            },
            success: function (result) {
                // console.log(result);

                if (result === true) {
                    $('.ops img').eq(2).attr("src", "/static/img/videoOps/cn_after.png");
                    let collections = document.getElementById("connectionNum");
                    let num;
                    let a = collections.innerText.indexOf("万");
                    if (a !== -1) {
                        num = parseInt(collections.innerText) * 10000 + 1;
                        collections.innerText = Math.round(num / 10000.0) + "万";
                    } else {
                        num = parseInt(collections.innerText) + 1;
                        collections.innerText = num;
                    }
                    // stars.innerHTML = parseInt(stars.innerText) + 1;
                } else {
                    $('.ops img').eq(2).attr("src", "/static/img/videoOps/cn_before.png");
                    let collections = document.getElementById("connectionNum");
                    let num;
                    let a = collections.innerText.indexOf("万");
                    if (a !== -1) {
                        num = parseInt(collections.innerText) * 10000 - 1;
                        collections.innerText = Math.round(num / 10000.0) + "万";
                    } else {
                        num = parseInt(collections.innerText) - 1;
                        collections.innerText = num;
                    }
                }
            }
        });
    }

    return false;
}
// function navTools(pageNum) {
//
//     $.ajax({
//             url: '/selectVideoComment', //请求的url
//             type: 'post', //请求的方式
//             dateType: "json", //请求的数据格式
//             // language=JQuery-CSS
//             data: {
//                 videoAddress: videoAddress,
//                 pageNum: pageNum,
//                 pageSize: 10,
//             },
//             error: function () {
//                 alert("error");
//             },
//             success: function (result) {
//                 let json = eval(result.list);
//                 let inner = document.getElementById("pageComment");
//                 inner.innerHTML = "";
//                 $.each(json, function (i, element) {
//                     $("#pageComment").append("<div class=\"media\">\n" +
//                         "        <a class=\"media-left\">\n" +
//                         "            <div class=\"headIcon-div\">\n" +
//                         "                <img src=\"" + element.headicon + "\" alt=\"头像\" class=\"headIcon\">\n" +
//                         "            </div>\n" +
//                         "\n" +
//                         "        </a>\n" +
//                         "        <div class=\"media-body\">\n" +
//                         "            <h4 class=\"media-nickname\">" + element.user_nickname + "</h4>\n" +
//                         "            <p class=\"media-p\">" + element.content + "</p>\n" +
//                         "            <div class=\"media-p\">\n" +
//                         "                <span>" + element.date + "</span>\n" +
//                         "                <a href=\"#\" class=\"reply-child\">回复</a>\n" +
//                         "            </div>\n" +
//                         "            <hr class=\"media-hr\"/>\n" +
//                         "        </div>\n" +
//                         "        <div class=\"media-bottom\"></div>\n" +
//                         "    </div>");
//                 });
//
//                 $("#pageComment").append("<div class=\"pageToolNav\">\n" +
//                     "        <ul class=\"pagination row\" id=\"pagination\">\n" +
//                     "            <li class=\"page-item\">\n" +
//                     "                <a class=\"page-link previous\" id=\"previousPage\" href=\"#\">上一页</a>\n" +
//                     "            </li>\n" +
//                     "            <li class=\"page-item\"><a class=\"page-link \" id=\"page1\" href=\"#\" >" + result.pageNum + "</a></li>\n" +
//                     "            <li class=\"page-item\"><a class=\"page-link \" id=\"page2\" href=\"#\">" + result.nextPage + "</a></li>\n" +
//                     "            <li class=\"page-item\"><a class=\"page-link \" id=\"page3\" href=\"#\" >" + (result.nextPage + 1) + "</a></li>\n" +
//                     "\n" +
//                     "            <li class=\"page-item\"><a>...</a></li>\n" +
//                     "            <li class=\"page-item\"><a class=\"page-link\" id=\"firstPage\" href=\"#\">首页</a></li>\n" +
//                     "            <li class=\"page-item\"><a class=\"page-link\" id=\"lastPage\" href=\"#\" >尾页</a></li>\n" +
//                     "            <li class=\"page-item\">\n" +
//                     "                <a class=\"page-link next\" id=\"nextPage\" href=\"#\" >下一页</a>\n" +
//                     "            </li>\n" +
//                     "        </ul>\n" +
//                     "    </div>");
//                 console.log("pageNum : " + result.pageNum);
//                 let ul = document.getElementById('pagination');
//                 ul.onclick = function (event) {
//                     event = event || window.event;
//
//                     let firstPage = "首页";
//                     let lastPage = "尾页";
//                     let nextPage = "下一页";
//                     let previousPage = "上一页";
//                     let curOperationString = event.target.innerHTML;
//                     if (nextPage === curOperationString) {
//                         navTools(parseInt(result.nextPage));
//                     } else if (previousPage === curOperationString) {
//                         navTools(parseInt(result.pageNum) - 1);
//                     } else if (firstPage === curOperationString) {
//                         navTools(1);
//                     } else if (lastPage === curOperationString) {
//                         navTools(parseInt(result.lastPage));
//                     } else {
//                         navTools(parseInt(curOperationString));
//                     }
//                     return false;
//                 }
//             },
//         }
//     );
//
//     return false;
// }