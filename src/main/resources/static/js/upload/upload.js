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

function submitForm() {
    $('#uploadFilesModal').modal('show');

    let videoFile = document.getElementById("choice-video-file").files[0]; // js 获取文件对象
    let videoCoverFile = document.getElementById("choice-video-cover-file").files[0]; // js 获取文件对象

    let videoCoins = $("#videoCoins").val();

    let videoTitle = $("#videoTitle").val();
    let videoType = $("#videoType option:selected").val();
    let videoName = $("#videoName").val();
    let videoDescription = $("#videoDescription").val();

    let videoInfo = {
        "username": username,
        "videoCoins": videoCoins,
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

function submitMusic() {
    alert(11);
    $('#uploadFilesModal').modal('show');

    let musicFile = document.getElementById("choice-music-file").files[0]; // js 获取文件对象
    let musicCoverFile = document.getElementById("choice-music-cover-file").files[0]; // js 获取文件对象
    let musicLrcFile = document.getElementById("choice-music-lrc-file").files[0]; // js 获取文件对象

    let musicAuthor = $("#musicAuthor").val();

    let musicTitle = $("#musicTitle").val();
    let musicType = $("#musicType option:selected").val();
    let musicName = $("#musicName").val();


    let musicInfo = {
        "username": username,
        "musicAuthor": musicAuthor,
        "musicTitle": musicTitle,
        "musicType": musicType,
        "musicName": musicName,
    };

    let formFile = new FormData();
    formFile.append("musicFile", musicFile); //加入文件对象
    formFile.append("musicCoverFile", musicCoverFile); //加入文件对象
    formFile.append("musicLrcFile", musicLrcFile); //加入文件对象
    formFile.append("musicInfo", JSON.stringify(musicInfo)); //加入文件对象

    $.ajax({
        url: "/uploadMusic",
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

<!--富文本框-->

//调用富文本编辑
$(document).ready(function () {
    //var text = $($("#summernote").summernote("code")).text();
    //console.log(text);
    // var markupStr = '<p><img src="/files/columnImgShortTime/20191222221151262.jpg" style="width: 300px;"><img src="/files/columnImgShortTime/20191222221156097.jpg" style="width: 25%;"></p><p>十大法萨芬</p><h1>撒的撒的</h1><p><span style="background-color: rgb(255, 0, 0);">撒旦法撒旦反反复复反反复复发股份</span></p><p>撒地方大师傅</p>';
    // $('#summernote').summernote('code', markupStr);
    var $summernote = $('#summernote').summernote({
        placeholder: 'Hello bootstrap 4',
        height: 300,
        minHeight: null,
        maxHeight: null,
        focus: true,
        //调用图片上传
        callbacks: {//回调函数，重写onImageUpload方法
            onImageUpload: function (files, editor, welEditable) {
                sendFile(this, files[0], editor, welEditable);
            }
        }

    });
});

function sendFile(val, files, editor, welEditable) {
    let result;
    let data = new FormData();
    data.append("files", files);
    $.ajax({
        data: data,
        //dataType: 'string',
        type: "POST",
        url: "/uploadColumn",
        cache: false,
        contentType: false,
        processData: false,
        responseType: "json",
        success: function (callback) {
            result = callback;
            //alert(result);
            $(val).summernote('editor.insertImage', "/files/columnImgShortTime/" + callback);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function submitColumn() {

    let columnContent = $("#summernote").summernote("code");
    console.log(columnContent);
    let columnInfo = {
        "username": username,
        "columnContent": columnContent,
    };
    let formFile = new FormData();
    formFile.append("columnInfo", JSON.stringify(columnInfo)); //加入文件对象
    $.ajax({
        data: formFile,
        //dataType: 'string',
        type: "POST",
        url: "/submitColumn",
        cache: false,
        contentType: false,
        processData: false,
        responseType: "json",
        success: function (result) {
            //document.getElementById("uploadFilesModalTitle").innerHTML = result.data;
            alert(result.data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
    //$summernote.onImageUpload().saveFile (files, editor, $editable) {


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