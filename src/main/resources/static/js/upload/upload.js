let username = getCookie("username");

let xhrOnProgress = function (fun) {
    xhrOnProgress.onprogress = fun; //绑定监听
    //使用闭包实现监听绑
    return function () {
        //通过$.ajaxSettings.xhr();获得XMLHttpRequest对象
        let xhr = $.ajaxSettings.xhr();
        //判断监听函数是否为函数
        if (typeof xhrOnProgress.onprogress !== 'function')
            return xhr;
        //如果有监听函数并且xhr对象支持绑定时就把监听函数绑定上去
        if (xhrOnProgress.onprogress && xhr.upload) {
            xhr.upload.onprogress = xhrOnProgress.onprogress;
        }
        return xhr;
    }
};

function Submit() {
    $('#uploadFilesModal').modal('show');

    let videoFile = document.getElementById("choice-video-file").files[0]; // js 获取文件对象
    let videoCoverFile = document.getElementById("choice-video-cover-file").files[0]; // js 获取文件对象

    let videoTitle = $("#videoTitle").val();
    let videoType = $("#videoType option:selected").val();
    let videoName = $("#videoName").val();
    let videoDescription = $("#videoDescription").val();

    let videoInfo = {
        "username": username,
        "videoTitle": videoTitle,
        "videoType": videoType,
        "videoName": videoName,
        "videoDescription": videoDescription
    };

    let formFile = new FormData();
    formFile.append("videoFile", videoFile); //加入文件对象
    formFile.append("videoCoverFile", videoCoverFile); //加入文件对象
    formFile.append("videoInfo", JSON.stringify(videoInfo)); //加入文件对象

    $.ajax({
        url: "/uploadVideo",
        data: formFile,
        type: "post",
        dataType: "json",
        cache: false,//上传文件无需缓存
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        xhr: xhrOnProgress(function (e) {
            let percent = e.loaded / e.total;
            $("#progress").width(percent * 100 + "%");
        }),
        success: function (result) {
            document.getElementById("uploadFilesModalTitle").innerHTML = result.data;
        }
    })
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

$('#uploadFilesModal').on('hidden.bs.modal', function (e) {
    document.getElementById("uploadFilesModalTitle").innerHTML = "正在上传";
});