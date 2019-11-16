loadlive2d("live2d", "/static/live2d/model/tia/model.json");

//轮播图板块
$(document).ready(function () {
    $.ajax({
        url: '/initPromoteVideos4Carousel',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {},
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            let json = eval(result);
            let imgTag = $(".carousel-item img");
            let linkTag = $(".carousel-item a");
            $.each(json, function (i, element) {
                imgTag.eq(i).attr("src", "/files/videoCover/" + element.videoCover);
                linkTag.eq(i).attr("href", "/video?videoid=" + element.videoFilename)
            })
        }
    })
});

//推广列表区域
$(document).ready(function () {
    $.ajax({
        url: '/initPromoteVideos4List',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {},
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            let json = eval(result);
            let imgTag = $('.Promote-one img');
            let spanTag = $('.Promote-one span');
            let linkTag = $('.Promote-one a');
            $.each(json, function (i, element) {
                imgTag.eq(i).attr("src", "/files/videoCover/" + element.videoCover);
                spanTag.eq(i).text(element.videoName);
                linkTag.eq(i).attr("href", "/video?videoid=" + element.videoFilename);
            })

        }
    })
});

//音乐板块
$(document).ready(function () {
    let videoType = "音乐";
    let length = 8;
    $.ajax({
        url: '/indexDataInit',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            videoType: videoType,
            length: length,
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            // console.log("success!");
            let json = eval(result);
            let imgTag = $('.Music-card img');
            let spanTag = $('.Music-card span');
            let videoTag1 = $('.Music-card a');
            $.each(json, function (i, element) {
                //$(…)[0] 返回的是一个dom对象
                // 而 attr() 方法 只能被JQuery对象所使用
                // 所以，可以用$(…).eq()
                imgTag.eq(i).attr("src", "/files/videoCover/" + element.videoCover);
                //添加文字描述
                spanTag.eq(i).text(element.videoName);
                // videoTag.eq(i).attr("value",element.videoFileName);
                videoTag1.eq(i).attr("href", "/video?videoid=" + element.videoFilename);
            });
            // console.log(videoTag.eq(0).val());
        }
    })
});

//动画板块
$(document).ready(function () {
    let videoType = "动画";
    let length = 12;
    $.ajax({
        url: '/indexDataInit',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            videoType: videoType,
            length: length,
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            // console.log("success!");
            let json = eval(result);
            let imgTag = $('.donghua img');
            let spanTag = $('.donghua span');
            let videoTag1 = $('.donghua a');
            $.each(json, function (i, element) {
                //$(…)[0] 返回的是一个dom对象
                // 而 attr() 方法 只能被JQuery对象所使用
                // 所以，可以用$(…).eq()
                imgTag.eq(i).attr("src", "/files/videoCover/" + element.videoCover);
                //添加文字描述
                spanTag.eq(i).text(element.videoName);
                // videoTag.eq(i).attr("value",element.videoFileName);
                videoTag1.eq(i).attr("href", "/video?videoid=" + element.videoFilename);
            });
            // console.log(videoTag.eq(0).val());
        }
    })
});

//番剧板块
$(document).ready(function () {
    let videoType = "番剧";
    let length = "12";
    $.ajax({
        url: '/indexDataInit',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            videoType: videoType,
            length: length,
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            let json = eval(result);
            let imgTag = $('.fanju img');
            let spanTag = $('.fanju span');
            let videoTag1 = $('.fanju a');
            $.each(json, function (i, element) {
                //$(…)[0] 返回的是一个dom对象
                // 而 attr() 方法 只能被JQuery对象所使用
                // 所以，可以用$(…).eq()
                imgTag.eq(i).attr("src", "/files/videoCover/" + element.videoCover);
                //添加文字描述
                spanTag.eq(i).text(element.videoName);
                // videoTag.eq(i).attr("value",element.videoFileName);
                videoTag1.eq(i).attr("href", "/video?videoid=" + element.videoFilename);
            });
        }
    })
});

//游戏版块
$(document).ready(function () {
    let videoType = "游戏";
    let length = "12";
    $.ajax({
        url: '/indexDataInit',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            videoType: videoType,
            length: length,
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            let json = eval(result);
            let imgTag = $('.Game-card img');
            let spanTag = $('.Game-card span');
            let videoTag1 = $('.Game-card a');
            $.each(json, function (i, element) {
                //$(…)[0] 返回的是一个dom对象
                // 而 attr() 方法 只能被JQuery对象所使用
                // 所以，可以用$(…).eq()
                imgTag.eq(i).attr("src", "/files/videoCover/" + element.videoCover);
                //添加文字描述
                spanTag.eq(i).text(element.videoName);
                // videoTag.eq(i).attr("value",element.videoFileName);
                videoTag1.eq(i).attr("href", "/video?videoid=" + element.videoFilename);
            });
        }
    })
});

//科技板块
$(document).ready(function () {
    let videoType = "科技";
    let length = 12;
    $.ajax({
        url: '/indexDataInit',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            videoType: videoType,
            length: length,
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            // console.log("success!");
            let json = eval(result);
            let imgTag = $('.technology img');
            let spanTag = $('.technology span');
            let videoTag1 = $('.technology a');
            $.each(json, function (i, element) {
                //$(…)[0] 返回的是一个dom对象
                // 而 attr() 方法 只能被JQuery对象所使用
                // 所以，可以用$(…).eq()
                imgTag.eq(i).attr("src", "/files/videoCover/" + element.videoCover);
                //添加文字描述
                spanTag.eq(i).text(element.videoName);
                // videoTag.eq(i).attr("value",element.videoFileName);
                videoTag1.eq(i).attr("href", "/video?videoid=" + element.videoFilename);
            });
            // console.log(videoTag.eq(0).val());
        }
    })
});

//舞蹈版块2
$(document).ready(function () {
    let videoType = "舞蹈";
    let length = 12;
    $.ajax({
        url: '/indexDataInit',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            videoType: videoType,
            length: length,
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            let json = eval(result);
            let imgTag = $('.Dance-card img');
            let spanTag = $('.Dance-card span');
            let videoTag1 = $('.Dance-card a');
            $.each(json, function (i, element) {
                //$(…)[0] 返回的是一个dom对象
                // 而 attr() 方法 只能被JQuery对象所使用
                // 所以，可以用$(…).eq()
                imgTag.eq(i).attr("src", "/files/videoCover/" + element.videoCover);
                //添加文字描述
                spanTag.eq(i).text(element.videoName);
                // videoTag.eq(i).attr("value",element.videoFileName);
                videoTag1.eq(i).attr("href", "/video?videoid=" + element.videoFilename);
            });
        }
    })
});

//数码板块
$(document).ready(function () {
    let videoType = "数码";
    let length = 12;
    $.ajax({
        url: '/indexDataInit',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            videoType: videoType,
            length: length,
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            // console.log("success!");
            let json = eval(result);
            let imgTag = $('.digital img');
            let spanTag = $('.digital span');
            let videoTag1 = $('.digital a');
            $.each(json, function (i, element) {
                //$(…)[0] 返回的是一个dom对象
                // 而 attr() 方法 只能被JQuery对象所使用
                // 所以，可以用$(…).eq()
                imgTag.eq(i).attr("src", "/files/videoCover/" + element.videoCover);
                //添加文字描述
                spanTag.eq(i).text(element.videoName);
                // videoTag.eq(i).attr("value",element.videoFileName);
                videoTag1.eq(i).attr("href", "/video?videoid=" + element.videoFilename);
            });
            // console.log(videoTag.eq(0).val());
        }
    })
});

//生活版块
$(document).ready(function () {
    let videoType = "生活";
    let length = 12;
    $.ajax({
        url: '/indexDataInit',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            videoType: videoType,
            length: length,
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            let json = eval(result);
            let imgTag = $('.Life-card img');
            let spanTag = $('.Life-card span');
            let videoTag1 = $('.Life-card a');
            $.each(json, function (i, element) {
                //$(…)[0] 返回的是一个dom对象
                // 而 attr() 方法 只能被JQuery对象所使用
                // 所以，可以用$(…).eq()
                imgTag.eq(i).attr("src", "/files/videoCover/" + element.videoCover);
                //添加文字描述
                spanTag.eq(i).text(element.videoName);
                // videoTag.eq(i).attr("value",element.videoFileName);
                videoTag1.eq(i).attr("href", "/video?videoid=" + element.videoFilename);
            });
        }
    })
});

//鬼畜板块
$(document).ready(function () {
    let videoType = "鬼畜";
    let length = 12;
    $.ajax({
        url: '/indexDataInit',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            videoType: videoType,
            length: length,
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            // console.log("success!");
            let json = eval(result);
            let imgTag = $('.kichiku img');
            let spanTag = $('.kichiku span');
            let videoTag1 = $('.kichiku a');
            $.each(json, function (i, element) {
                //$(…)[0] 返回的是一个dom对象
                // 而 attr() 方法 只能被JQuery对象所使用
                // 所以，可以用$(…).eq()
                imgTag.eq(i).attr("src", "/files/videoCover/" + element.videoCover);
                //添加文字描述
                spanTag.eq(i).text(element.videoName);
                // videoTag.eq(i).attr("value",element.videoFileName);
                videoTag1.eq(i).attr("href", "/video?videoid=" + element.videoFilename);
            });
            // console.log(videoTag.eq(0).val());
        }
    })
});

//时尚板块
$(document).ready(function () {
    let videoType = "时尚";
    let length = 12;
    $.ajax({
        url: '/indexDataInit',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            videoType: videoType,
            length: length,
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            // console.log("success!");
            let json = eval(result);
            let imgTag = $('.fashion img');
            let spanTag = $('.fashion span');
            let videoTag1 = $('.fashion a');
            $.each(json, function (i, element) {
                //$(…)[0] 返回的是一个dom对象
                // 而 attr() 方法 只能被JQuery对象所使用
                // 所以，可以用$(…).eq()
                imgTag.eq(i).attr("src", "/files/videoCover/" + element.videoCover);
                //添加文字描述
                spanTag.eq(i).text(element.videoName);
                // videoTag.eq(i).attr("value",element.videoFileName);
                videoTag1.eq(i).attr("href", "/video?videoid=" + element.videoFilename);
            });
            // console.log(videoTag.eq(0).val());
        }
    })
});

//广告板块
$(document).ready(function () {
    let videoType = "广告";
    let length = 12;
    $.ajax({
        url: '/indexDataInit',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            videoType: videoType,
            length: length,
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            // console.log("success!");
            let json = eval(result);
            let imgTag = $('.ad img');
            let spanTag = $('.ad span');
            let videoTag1 = $('.ad a');
            $.each(json, function (i, element) {
                //$(…)[0] 返回的是一个dom对象
                // 而 attr() 方法 只能被JQuery对象所使用
                // 所以，可以用$(…).eq()
                imgTag.eq(i).attr("src", "/files/videoCover/" + element.videoCover);
                //添加文字描述
                spanTag.eq(i).text(element.videoName);
                // videoTag.eq(i).attr("value",element.videoFileName);
                videoTag1.eq(i).attr("href", "/video?videoid=" + element.videoFilename);
            });
            // console.log(videoTag.eq(0).val());
        }
    })
});

//娱乐板块
$(document).ready(function () {
    let videoType = "娱乐";
    let length = 12;
    $.ajax({
        url: '/indexDataInit',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            videoType: videoType,
            length: length,
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            // console.log("success!");
            let json = eval(result);
            let imgTag = $('.entertainment img');
            let spanTag = $('.entertainment span');
            let videoTag1 = $('.entertainment a');
            $.each(json, function (i, element) {
                //$(…)[0] 返回的是一个dom对象
                // 而 attr() 方法 只能被JQuery对象所使用
                // 所以，可以用$(…).eq()
                imgTag.eq(i).attr("src", "/files/videoCover/" + element.videoCover);
                //添加文字描述
                spanTag.eq(i).text(element.videoName);
                // videoTag.eq(i).attr("value",element.videoFileName);
                videoTag1.eq(i).attr("href", "/video?videoid=" + element.videoFilename);
            });
            // console.log(videoTag.eq(0).val());
        }
    })
});

//影视板块
$(document).ready(function () {
    let videoType = "影视";
    let length = 12;
    $.ajax({
        url: '/indexDataInit',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            videoType: videoType,
            length: length,
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            // console.log("success!");
            let json = eval(result);
            let imgTag = $('.movies img');
            let spanTag = $('.movies span');
            let videoTag1 = $('.movies a');
            $.each(json, function (i, element) {
                //$(…)[0] 返回的是一个dom对象
                // 而 attr() 方法 只能被JQuery对象所使用
                // 所以，可以用$(…).eq()
                imgTag.eq(i).attr("src", "/files/videoCover/" + element.videoCover);
                //添加文字描述
                spanTag.eq(i).text(element.videoName);
                // videoTag.eq(i).attr("value",element.videoFileName);
                videoTag1.eq(i).attr("href", "/video?videoid=" + element.videoFilename);
            });
            // console.log(videoTag.eq(0).val());
        }
    })
});

//舞蹈板块1
$(document).ready(function () {
    let videoType = "舞蹈";
    let length = 6;
    $.ajax({
        url: '/indexDataInit',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            videoType: videoType,
            length: length,
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            // console.log(result);
            let json = eval(result);
            let imgTag = $('.recommend img');
            let pTag = $('.card-mark p');
            let videoTag = $('.recommend a');
            $.each(json, function (i, element) {
                imgTag.eq(i).attr("src", "files/videoCover/" + element.videoCover);
                pTag.eq(i).text(element.videoName);
                videoTag.eq(i).attr("href", "/video?videoid=" + element.videoFilename);
            })

        }
    })
});

//音乐排行榜
$(document).ready(function () {
    let videoType = "音乐";
    let length = 8;
    $.ajax({
        url: '/playRank',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
            videoType: videoType,
            length: length
        },
        error: function () {
            showNoticeModal("服务器错误！", "服务器未响应，稍后再试！");
        },
        success: function (result) {
            let json = eval(result);
            let aTag = $(".rank a");

            $.each(json, function (i, element) {
                aTag.eq(i).attr("href", "/video?videoid=" + element.videoFilename);
                aTag.eq(i).text(element.videoName);
            })

        }
    });

    return false;
});


//工具 , 导航栏中固定定位对锚点链接影响的解决方法
function locateAt(e) {
    e = document.getElementById(e);/*以id命名的锚点*/
    let y = e.offsetTop;
    while (e === e.offsetParent) {
        y += e.offsetTop;
    }
    y -= 55;/*悬浮菜单的高度*/
    window.scrollTo(0, y);
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

