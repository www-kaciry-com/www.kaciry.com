
let collectPage = $("#page1");
let contributePage = $("#page");

function changeInfo() {
    $.ajax({
        url: '/changeInfo', //请求的url
        type: 'post', //请求的方式
        dateType: "json",
        data: $('#user-info').serialize(), //form表单里要提交的内容，里面的input等写上name就会提交，这是序列化
        error: function () {
            alert('请求失败');
        },
        success: function (result) {
            alert("Success !changeInfo");
            document.getElementById("email").value = result.userEmail;
            document.getElementById("nickname").value = result.userNickName;
            document.getElementById("mobilephone").value = result.userPhoneNumber;
            document.getElementById("sex").value = result.userSex;
        }
    });
}

function selectInfo() {
    $.ajax({
        url: '/selectInfo', //请求的url
        type: 'post', //请求的方式
        error: function () {
            alert('请求失败');
        },
        success: function (result) {
            // alert("Success ! selectInfo");
            document.getElementById("email").value = result.userEmail;
            document.getElementById("nickname").value = result.userNickName;
            document.getElementById("mobilephone").value = result.userPhoneNumber;
            document.getElementById("sex").value = result.userSex;
        }
    });
}

function selectContribution() {

    let contributeTag = $('.My-Contribution-content');
    $.ajax({
        url: '/selectMyVideo', //请求的url
        type: 'post', //请求的方式
        data: {
            pageNum: 1,
            pageSize: 16
        },
        error: function () {
            alert('请求失败');
        },
        success: function (result) {
            contributeTag.children().remove();
            let isInited = contributePage.pagination();
            if (!isInited) {
                $("#page").pagination({
                    pageIndex: 0,
                    pageSize: 16,
                    total: result.total,
                    debug: false,
                    showInfo: true,
                    showJump: true,
                    showPageSizes: true,
                    showFirstLastBtn:true,
                    firstBtnText:'首页',
                    lastBtnText:'尾页',
                    prevBtnText:'上一页',
                    nextBtnText:'下一页',
                    jumpBtnText:'跳转',
                    pageElementSort: ['$page', '$jump', '$info'],
                    infoFormat:'{start}~{end},共{total}个投稿',
                });
            }
            let json = eval(result.list);
            let str = analysisData(json);
            contributeTag.append(str);
        }
    });


}

contributePage.on("pageClicked", function (event, data) {
    let contributeTag = $('.My-Contribution-content');
    $.ajax({
        url: "/selectMyVideo",
        type: "POST",
        dataType: "json",
        data: {
            pageNum: data.pageIndex + 1,
            pageSize: 16
        },
        success: function (result) {
            console.log(result);
            contributeTag.children().remove();
            let json = eval(result.list);
            let str = analysisData(json);
            contributeTag.append(str);

        }
    })
});
contributePage.on("jumpClicked", function (event, data) {
    let contributeTag = $('.My-Contribution-content');
    $.ajax({
        url: "/selectMyVideo",
        type: "POST",
        dataType: "json",
        data: {
            pageNum: data.pageIndex + 1,
            pageSize: 16
        },
        success: function (result) {

            contributeTag.children().remove();
            let json = eval(result.list);
            let str = analysisData(json);
            contributeTag.append(str);
        }
    });
});

function analysisData(data) {
    let str = '';
    $.each(data, function (i, element) {
        str += "<li class=\"col-6 col-md-3 items\" style=\"display: block\">\n" +
            "                            <a href=\"/video?videoid=" + element.videoFilename + "  \" target=\"_blank\" class=\"cover\">\n" +
            "                                <div class=\"cover\"><img src=\"/files/videoCover/" + element.videoCover + "\" alt=\"cover\" class=\"videoCover\"></div>\n" +
            "                            </a>\n" +
            "                            <a href=\"\" target=\"_blank\" title=\"\" class=\"title\">" + element.videoName + "</a>\n" +
            "                            <div class=\"meta\">\n" +
            "                                <span class=\"play\"><i class=\"icon\">" + element.videoPlayNum + "</i></span>\n" +
            "                                <span class=\"time\"><i class=\"icon\">" + element.videoData.substring(0, 11) + "</i></span>\n" +
            "                            </div>\n" +
            "                        </li>";
    });
    return str;
}

function queryCollections() {
    let collectionsTag = $('.My-Collections');
    $.ajax({
        url: '/postQueryCollect', //请求的url
        type: 'post', //请求的方式
        data: {
            pageNum: 1,
            pageSize: 16
        },
        error: function () {
            alert('请求失败');
        },
        success: function (result) {
            collectionsTag.children().remove();
            let isInited = collectPage.pagination();
            if (!isInited) {
                collectPage.pagination({
                    pageIndex: 0,
                    pageSize: 16,
                    total: result.total,
                    debug: false,
                    showInfo: true,
                    showJump: true,
                    showPageSizes: true,
                    showFirstLastBtn:true,
                    firstBtnText:'首页',
                    lastBtnText:'尾页',
                    prevBtnText:'上一页',
                    nextBtnText:'下一页',
                    jumpBtnText:'跳转',
                    pageElementSort: ['$page', '$jump', '$info'],
                    infoFormat:'{start}~{end},共{total}个投稿',
                });
            }
            let json = eval(result.list);
            let str = analysisData(json);
            collectionsTag.append(str);
        }
    });
}



collectPage.on("pageClicked", function (event, data) {

    let collectionsTag = $('.My-Collections');

    $.ajax({
        url: "/postQueryCollect",
        type: "POST",
        dataType: "json",
        data: {
            pageNum: data.pageIndex + 1,
            pageSize: 16
        },
        success: function (result) {
            console.log(result);
            collectionsTag.children().remove();
            let json = eval(result.list);
            let str = analysisData(json);
            collectionsTag.append(str);

        }
    })
});


collectPage.on("jumpClicked", function (event, data) {
    let collectionsTag = $(".My-Collections");
    $.ajax({
        url: "/postQueryCollect",
        type: "POST",
        dataType: "json",
        data: {
            pageNum: data.pageIndex + 1,
            pageSize: 16
        },
        success: function (result) {
            collectionsTag.children().remove();
            let json = eval(result.list);
            let str = analysisData(json);
            collectionsTag.append(str);
        }
    });
});