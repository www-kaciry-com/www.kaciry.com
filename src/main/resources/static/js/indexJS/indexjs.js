loadlive2d("live2d", "/static/live2d/model/tia/model.json");

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
            alert("服务器未响应，加载视频信息失败！");
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
                videoTag1.eq(i).attr("href", "/video?videoid=" + element.videoFileName);
            });
            // console.log(videoTag.eq(0).val());
        }
    })
});

//舞蹈板块
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
            alert("服务器未响应，加载视频信息失败！");
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

