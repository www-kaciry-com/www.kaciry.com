let vm = new Vue({
    el: '#admin',
    data: {
        /**
         * 查看举报相关数据
         */
        //当前页面视频举报数据集合（若搜索则为一条数据）
        videoReportList: [],
        //点击查看详情按钮 一条视频举报数据（videoReportList[]中根据主键获取）
        videoReportDetailsList: [],
        //当前页面评论举报数据集合（若搜索则为一条数据）
        commentReportList: [],
        //点击查看详情按钮 一条评论举报数据（commentReportList[]中根据主键获取）
        commentReportDetailsList: [],
        //下拉框举报类型集合
        reportTypeList: [
            {
                reportType: '视频举报',
                value: '视频举报',
            },
            {
                reportType: '评论举报',
                value: '评论举报',
            },
        ],
        //下拉框默认选中的举报类型
        selected: '视频举报',//v-model绑定下拉框的value值
        //视频举报默认选中状态
        showVideoReport: true,
        //评论举报默认选中状态
        showCommentReport: false,

        /**
         * 查看用户信息相关数据
         */
        //当前页面用户信息数据集合（若搜索则为一条数据）
        allUserList: [],
        //点击查看详情按钮 一条用户信息数据（从allUserList[]中根据主键获取）
        userList: [],

        /**
         * 查看视频信息相关数据
         */
        //当前页面用户信息数据集合（若搜索则为一条数据）
        allVideoList: [],
        //点击查看详情按钮 一条用户信息数据（从allVideoList[]中根据主键获取）
        videoList: [],
        /**
         * 查看音乐信息相关数据
         */
        //当前页面用户信息数据集合（若搜索则为一条数据）
        allMusicList: [],
        //点击查看详情按钮 一条用户信息数据（从allVideoList[]中根据主键获取）
        musicList: [],
        /**
         * 审核视频相关数据
         */
        //当前页面用户信息数据集合（若搜索则为一条数据）
        allUncheckedVideoList: [],
        //点击查看详情按钮 一条用户信息数据（从allVideoList[]中根据主键获取）
        uncheckVideoList: [],
        changeColor: false,
        /**
         * 分页相关数据
         */
        cur: 1,
        all: 0,
        index: '',
        pageNum: 0,
        star: 0,
        searchValue: '',//v-model绑定搜索框中的输入值
        videoFilename: '',
        /**
         * echarts
         */
        userIpList: [],
        mapList: [],

        totalList: [],
    },
    created() {  //页面加载即执行
        // this.selectInfo_eCharts();
        this.selectTotal();
    },

    watch: {
        cur: function (newValue, oldValue) {
            console.log(arguments);
        }
    },
    methods: {
        /**
         * 分页
         */
        btnClick(num) {
            console.log("btn:" + num + "   cur::" + this.cur);
            if (num !== this.cur) {
                this.cur = num;
                this.pageNum = num;
            }
        },
        pageNumAdd() {
            this.pageNum = this.pageNum + 1;
            console.log("this.pageNum:" + this.pageNum);

        },
        pageNumSub() {
            if (this.pageNum > 0)
                this.pageNum--;
        },

        /**
         * 查看举报JS开始
         */
        //改变举报类型选中状态
        changeReportTypeStatus() {
            this.showVideoReport = !this.showVideoReport;
            this.showCommentReport = !this.showCommentReport;
        },
        //下拉框选择举报类型
        chooseReportType() {
            this.changeReportTypeStatus();
            switch (this.selected) {
                case "视频举报":
                    this.selectVideoReport();
                    break;
                case "评论举报":
                    this.selectCommentReport();
                    break;
            }
        },
        //查看视频举报总条数
        selectVideoReportNum() {
            this.$http.post('/selectVideoReportNum',
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    this.all = Math.ceil(result.body / 8);
                    //console.log(result);
                } else {
                    alert('wrong!!!')
                }
            })
        },
        //查看评论举报总条数
        selectCommentReportNum() {
            this.$http.post('/selectCommentReportNum',
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    this.all = Math.ceil(result.body / 8);
                    //console.log(result);
                } else {
                    alert('wrong!!!')
                }
            })
        },
        //查看视频举报
        selectVideoReport() {
            let pn = JSON.parse(JSON.stringify(this.pageNum));
            if (pn !== 0) {
                pn--;
            }
            this.$http.post('/adminSelectVideoReport',
                {
                    star: pn * 8,
                    pageSize: 8,
                },
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body.length<0) {
                        alert("未查询到视频举报信息!!!")
                    }
                    this.videoReportList = result.body;
                } else {
                    alert('wrong!!!')
                }

            });
            this.selectVideoReportNum();
        },
        //查看评论举报
        selectCommentReport() {
            let pn = JSON.parse(JSON.stringify(this.pageNum));
            if (pn !== 0) {
                pn--;
            }
            this.$http.post('/adminSelectCommentReport',
                {
                    star: pn * 8,
                    pageSize: 8,
                },
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    this.commentReportList = result.body;
                    console.log(8585);
                    console.log(this.commentReportList);
                } else {
                    alert('wrong!!!')
                }
            });
            this.selectCommentReportNum();
        },
        //从缓存（videoReportList）中获取点击查看的视频举报详细信息
        queryVideoReportDetails(reportVideoIdentityDocument) {
            console.log(this.videoReportList);
            this.videoReportList.forEach(videoReportDetailsList => {
                if (videoReportDetailsList.reportVideoIdentityDocument === reportVideoIdentityDocument) {
                    this.videoReportDetailsList = videoReportDetailsList;
                    console.log(this.videoReportDetailsList);
                    //根据路径找视频
                    this.$refs.video1.src = "/files/video/" + this.videoReportDetailsList.videoFilename;
                    //console.log(this.videoFilename);

                }
            })
        },
        //从缓存（commentReportList）中获取点击查看的评论举报详细信息
        queryCommentReportDetails(commentIdentityDocument) {
            this.commentReportList.forEach(commentReportDetailsList => {
                if (commentReportDetailsList.commentIdentityDocument === commentIdentityDocument) {
                    this.commentReportDetailsList = commentReportDetailsList;
                    //console.log(this.commentReportDetailsList);
                }
            })
        },
        //根据用户输入的举报编号搜索举报详细信息
        searchReport() {
            //判断是视频举报还是评论举报
            // 1 视频举报
            if (this.showVideoReport === true) {
                let reportVideoId = this.searchValue; //v-model  数据绑定 搜索框中的输入值
                this.$http.post('/adminSearchVideoReportDetailsInfo',
                    {
                        reportVideoId: reportVideoId,
                    },
                    {emulateJSON: true}).then(result => {
                    if (result.status === 200) {
                        if (result.body.length < 1) {
                            alert("当前编号不存在，请重新输入！");
                            this.searchValue = "";
                            return;
                        }
                        this.videoReportList = result.body;
                        this.searchValue = "";
                    } else {
                        alert('wrong!!!');
                        this.searchValue = "";
                    }
                })
            }
            // 2 评论举报
            else if (this.showCommentReport === true) {
                let reportCommentId = this.searchValue;//v-model  数据绑定 搜索框中的输入值
                this.$http.post('/adminSearchCommentReportDetailsInfo',
                    {
                        reportCommentId: reportCommentId,
                    },
                    {emulateJSON: true}).then(result => {
                    if (result.status === 200) {
                        if (result.body.length < 1) {
                            alert("当前编号不存在，请重新输入！");
                            this.searchValue = "";
                            return;
                        }
                        this.commentReportList = result.body;
                        this.searchValue = "";
                    } else {
                        alert('wrong!!!');
                        this.searchValue = "";
                    }
                })
            } else {
                alert("请选择举报类型，再进行查询")
            }
        },
        //查看视频举报页面跳转
        queryVideoReportPageSkip() {
            let maxPage = this.all;
            let skipPage = Number(document.getElementById("input-queryVideoReport").value);
            if (!skipPage) {
                alert("请输入跳转页码");
            } else if (skipPage < 1 || skipPage > maxPage) {
                alert("您输入的页码超过页数范围了！");
            } else {
                if (skipPage !== this.cur) {
                    this.cur = skipPage;
                    this.pageNum = skipPage;
                }
                //console.log(this.pageNum);
                this.selectVideoReport();
            }
        },
        //查看评论举报页面跳转
        queryCommentReportPageSkip() {
            let maxPage = this.all;
            let skipPage = Number(document.getElementById("input-queryCommentReport").value);
            if (!skipPage) {
                alert("请输入跳转页码");
            } else if (skipPage < 1 || skipPage > maxPage) {
                alert("您输入的页码超过页数范围了！");
            } else {
                if (skipPage !== this.cur) {
                    this.cur = skipPage;
                    this.pageNum = skipPage;
                }
                this.selectCommentReport();
            }
        },
        //封禁视频
        banVideo(videoFilename) {
            this.$http.post('/adminBanVideoById',
                {
                    videoFilename: videoFilename,
                },
                {emulateJSON: true}).then(result => {
                // console.log(result);
                if (result.body > 0) {
                    alert('视频封禁成功')
                } else {
                    alert('wrong!!!')
                }
            })
        },
        //封禁评论
        banComment(commentIdentityDocument) {
            //let commentIdentityDocument = this.commentReportDetailsList.commentIdentityDocument;
            //console.log(commentIdentityDocument);
            this.$http.post('/adminBanCommentById',
                {
                    commentIdentityDocument: commentIdentityDocument,
                },
                {emulateJSON: true}).then(result => {
                //console.log(result);
                if (result.body > 0) {
                    alert('评论封禁成功')
                } else {
                    alert('wrong!!!')
                }
            })
        },

        /*查看举报JS结束*/


        /**
         * 查看用户信息JS开始
         */
        //查看用户信息页面总条数
        selectUserInfoNum() {
            this.$http.post('/selectUserInfoNum',
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    this.all = Math.ceil(result.body / 8);
                    //console.log(result);
                } else {
                    alert('页面条数信息加载失败!!!')
                }
            })
        },
        //获取当前页数全部用户信息
        selectUserInfo() {
            let pn = JSON.parse(JSON.stringify(this.pageNum));
            if (pn !== 0) {
                pn--;
            }
            this.$http.post('/adminSelectUserInfo',
                {
                    star: pn * 8,
                    pageSize: 8
                },
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    this.allUserList = result.body;
                } else {
                    alert('用户信息加载失败!!!')
                }
            });
            this.selectUserInfoNum();
        },
        //从缓存（allUserList）中获取点击查看的用户详细信息
        queryUserDetailsInfo(username) {
            this.allUserList.forEach(userList => {
                if (userList.username === username) {
                    this.userList = userList;
                    console.log(userList);
                    //console.log(userList[0].userHeadIcon);
                }
            })
        },
        //根据用户输入的用户名搜索用户详细信息
        searchUser() {
            let searchedUsername = this.searchValue;  //v-model  数据绑定 搜索框中的输入值
            this.$http.post('/adminSearchUserDetailsInfo',
                {
                    searchedUsername: searchedUsername,
                },
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body.length < 1) {
                        alert("用户名不存在，请重新输入！");
                        this.searchValue = "";
                        return;
                    }
                    this.allUserList = result.body;
                    this.searchValue = "";
                } else {
                    alert('wrong!!!');
                    this.searchValue = "";
                }
            })
        },
        //查看用户信息页面跳转
        queryUserInfoPageSkip() {
            var maxPage = this.all;
            var skipPage = Number(document.getElementById("input-queryUserInfo").value);
            if (!skipPage) {
                alert("请输入跳转页码");
            } else if (skipPage < 1 || skipPage > maxPage) {
                alert("您输入的页码超过页数范围了！");
            } else {
                if (skipPage !== this.cur) {
                    this.cur = skipPage;
                    this.pageNum = skipPage;
                }
                this.selectUserInfo();
            }
        },

        /*查看用户信息JS结束*/




        /**
         * 数据分析相关功能
         */
        //echarts
        selectInfo_eCharts() {
            //用户信息
            let userInfo_eCharts = echarts.init(document.getElementById('main2'));
            this.$http.post('/adminSelectUserInfo_eCharts',
                {},
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body.length < 1) {
                        alert("未找到相关数据!!!");
                        return;
                    }
                    //this.userInfo_eCharts = result.body;
                    //console.log(result.bodyText);
                    //console.log(result.body);
                    JSON.stringify(result.bodyText);
                    //echarts.init(document.getElementById('main2')).setOption({
                    userInfo_eCharts.setOption({
                        title: {
                            text: '会员比例',
                            subtext: '',
                            left: 'center'
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend: {
                            // orient: 'vertical',
                            // top: 'middle',
                            bottom: 10,
                            left: 'center',
                        },
                        series: {
                            type: 'pie',
                            radius: '50%',
                            data: result.body,
                        }
                    });
                } else {
                    alert('会员图表加载失败!!!');
                }
            });

            //视频分类
            let videoInfo_eCharts = echarts.init(document.getElementById('main'));
            this.$http.post('/adminSelectVideoInfo_eCharts',
                {},
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body.length < 1) {
                        alert("未找到相关数据!!!");
                        return;
                    }
                    //console.log(result);
                    JSON.stringify(result.bodyText);
                    //console.log(result.body);
                    videoInfo_eCharts.setOption({
                        title: {
                            text: '各类视频数量统计'
                        },
                        tooltip: {},
                        legend: {
                            data: ['总量']
                        },
                        grid: {
                            x: 20,
                            y: 45,
                            x2: 10,
                            y2: 20,
                            borderWidth: 1
                        },
                        xAxis: {
                            //data: result.bodyText.videoType
                            data: ["其他", "动画", "国创", "娱乐", "广告", "影视", "时尚", "游戏", "生活", "番剧", "科技", "舞蹈", "音乐", "鬼畜"]
                        },
                        yAxis: {},
                        series: [{
                            name: '数量',
                            type: 'bar',
                            data: result.body
                        }]
                    });
                } else {
                    alert('视频数量图表加载失败!!!');
                }
            });

            //弹幕 评论 点赞 分享 收藏
            let videoInfoData_eCharts = echarts.init(document.getElementById('main5'));
            this.$http.post('/adminSelectVideoInfoData_eCharts',
                {},
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body.length < 1) {
                        alert("未找到相关数据!!!");
                        return;
                    }
                    //console.log(result);
                    console.log(result.body);
                    option = {
                        legend: {},
                        tooltip: {},
                        dataset: {
                            /*   "其他", "动画", "国创", "娱乐", "广告", "影视", "时尚", "游戏", "生活", "番剧",
                               "科技", "舞蹈", "音乐", "鬼畜"*/

                            source: [
                                ['product', '点赞量', '投币量', '收藏量', '分享量', '播放量', '弹幕量'],
                                // ['其他', 43.3, 85.8, 93.7, 43.3, 85.8, 93.7],
                                // ['动画', 83.1, 73.4, 55.1, 43.3, 85.8, 93.7],
                                // ['国创', 86.4, 65.2, 82.5, 43.3, 85.8, 93.7],
                                // ['娱乐', 72.4, 53.9, 39.1, 43.3, 85.8, 93.7],
                                // ['广告', 83.1, 73.4, 55.1, 43.3, 85.8, 93.7],
                                // ['影视', 86.4, 65.2, 82.5, 43.3, 85.8, 93.7],
                                // ['时尚', 72.4, 53.9, 39.1, 43.3, 85.8, 93.7],
                                result.body[0],
                                result.body[1],
                                result.body[2],
                                result.body[3],
                                result.body[4],
                                result.body[5],
                                result.body[6],
                                result.body[7],
                                result.body[8],
                                result.body[9],
                                result.body[10],
                                result.body[11],
                                result.body[12],
                                result.body[13],
                            ]
                        },
                        xAxis: {type: 'category'},
                        yAxis: {},
                        // Declare several bar series, each will be mapped
                        // to a column of dataset.source by default.
                        series: [
                            {type: 'bar'},
                            {type: 'bar'},
                            {type: 'bar'},
                            {type: 'bar'},
                            {type: 'bar'},
                            {type: 'bar'},
                        ]
                    };
                    videoInfoData_eCharts.setOption(option);
                } else {
                    alert('视频相关信息图表加载失败!!!');
                }
            });

            //地图
            let userIpInfo = echarts.init(document.getElementById('main7'));
            this.$http.post('/adminSelectUserIp_eCharts',
                {},
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body.length < 1) {
                        alert("3333333");
                        return;
                    }
                    // console.log(result.body);
                    JSON.stringify(result.bodyText);
                    //console.log(result);
                    this.userIpList = result.body;
                    //console.log(this.userIpList);
                    this.mapList = [
                        {name: "南海诸岛", value: 0},
                        {name: '北京', value: this.randomValue('北京')},
                        {name: '天津', value: this.randomValue('天津')},
                        {name: '上海', value: this.randomValue('上海')},
                        {name: '重庆', value: this.randomValue('重庆')},
                        {name: '河北', value: this.randomValue('河北')},
                        {name: '河南', value: this.randomValue('河南')},
                        {name: '云南', value: this.randomValue('云南')},
                        {name: '辽宁', value: this.randomValue('辽宁')},
                        {name: '黑龙江', value: this.randomValue('黑龙江')},
                        {name: '湖南', value: this.randomValue('湖南')},
                        {name: '安徽', value: this.randomValue('安徽')},
                        {name: '山东', value: this.randomValue('山东')},
                        {name: '新疆', value: this.randomValue('新疆')},
                        {name: '江苏', value: this.randomValue('江苏')},
                        {name: '浙江', value: this.randomValue('浙江')},
                        {name: '江西', value: this.randomValue('江西')},
                        {name: '湖北', value: this.randomValue('湖北')},
                        {name: '广西', value: this.randomValue('广西')},
                        {name: '甘肃', value: this.randomValue('甘肃')},
                        {name: '山西', value: this.randomValue('山西')},
                        {name: '内蒙古', value: this.randomValue('内蒙古')},
                        {name: '陕西', value: this.randomValue('陕西')},
                        {name: '吉林', value: this.randomValue('吉林')},
                        {name: '福建', value: this.randomValue('福建')},
                        {name: '贵州', value: this.randomValue('贵州')},
                        {name: '广东', value: this.randomValue('广东')},
                        {name: '青海', value: this.randomValue('青海')},
                        {name: '西藏', value: this.randomValue('西藏')},
                        {name: '四川', value: this.randomValue('四川')},
                        {name: '宁夏', value: this.randomValue('宁夏')},
                        {name: '海南', value: this.randomValue('海南')},
                        {name: '台湾', value: this.randomValue('台湾')},
                        {name: '香港', value: this.randomValue('香港')},
                        {name: '澳门', value: this.randomValue('澳门')}
                    ];
                    option = {
                        title: {
                            text: '今日登录用户分布图',
                            subtext: 'data from "kaciry.com".in',
                            //sublink: 'http://www.pm25.in',
                            x: 'center',
                            textStyle: {
                                color: '#90d4ff'
                            }
                        },
                        tooltip: {
                            formatter: function (params, ticket, callback) {
                                return params.seriesName + '<br />' + params.name + '：' + params.value
                            }//数据格式化
                        },
                        visualMap: {
                            min: 0,
                            max: 15,
                            left: 'left',
                            top: 'bottom',
                            text: ['高', '低'],//取值范围的文字
                            inRange: {
                                color: ['#93ff89', '#dd3715']//取值范围的颜色
                            },
                            show: true//图注
                        },
                        geo: {
                            map: 'china',
                            roam: false,//不开启缩放和平移
                            zoom: 1.23,//视角缩放比例
                            label: {
                                normal: {
                                    show: true,
                                    fontSize: '10',
                                    color: 'rgba(0,0,0,0.7)'
                                }
                            },
                            itemStyle: {
                                normal: {
                                    borderColor: 'rgba(0, 0, 0, 0.2)'
                                },
                                emphasis: {
                                    areaColor: '#F3B329',//鼠标选择区域颜色
                                    shadowOffsetX: 0,
                                    shadowOffsetY: 0,
                                    shadowBlur: 20,
                                    borderWidth: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        },
                        series: [
                            {
                                name: '今日登录用户量',
                                type: 'map',
                                geoIndex: 0,
                                data: this.mapList
                            }
                        ]
                    };
                    userIpInfo.setOption(option);
                } else {
                    alert('111111111');
                }
            });
        },
        randomValue(province) {
            let num = 0;
            //console.log(province);
            //console.log(this.userIpList);
            for (let i = 0; i < this.userIpList.length; i++) {
                // console.log(888);
                // console.log(this.userIpList[i]);
                if (this.userIpList[i] === province) {
                    num++;
                }
            }
            //console.log(num);
            return num;
        },
        //计算总用户量 会员量 视频 音乐
        selectTotal() {
            this.$http.post('/selectTotal',
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    this.totalList = result.body;
                    //console.log(this.userTotal);
                } else {
                    alert('wrong!!!')
                }
            })
        },


        /**
         * 视频信息相关功能
         */
        //查看视频信息页面总条数
        selectVideoNum() {
            this.$http.post('/selectVideoNum',
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    this.all = Math.ceil(result.body / 8);
                    //console.log(result);
                } else {
                    alert('视频信息加载失败!!!')
                }
            })
        },
        //查看视频信息
        selectVideoInfo() {
            let pn = JSON.parse(JSON.stringify(this.pageNum));
            if (pn !== 0) {
                pn--;
            }
            this.$http.post('/adminSelectVideoInfo',
                {
                    star: pn * 8,
                    pageSize: 8
                },
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    this.allVideoList = result.body;
                } else {
                    alert('wrong!!!')
                }
            });
            this.selectVideoNum();
        },
        //查看视频信息页面跳转
        queryVideoInfoPageSkip() {
            var maxPage = this.all;
            var skipPage = Number(document.getElementById("input-queryVideoInfo").value);
            if (!skipPage) {
                alert("请输入跳转页码");
            } else if (skipPage < 1 || skipPage > maxPage) {
                alert("您输入的页码超过页数范围了！");
            } else {
                if (skipPage !== this.cur) {
                    this.cur = skipPage;
                    this.pageNum = skipPage;
                }
                this.selectVideoInfo();
            }
        },
        //从缓存（videoIdentityDocument）中获取点击查看的视频详细信息
        queryVideoDetailsInfo(videoIdentityDocument) {
            this.allVideoList.forEach(videoList => {
                if (videoList.videoIdentityDocument === videoIdentityDocument) {
                    this.videoList = videoList;
                    //根据路径找视频
                    this.$refs.video2.src = "/files/video/" + this.videoList.videoFilename;
                    //console.log(this.videoFilename);
                }
            })
        },
        //根据用户输入的用户名搜索用户全部视频信息
        searchUserVideos() {
            let username = this.searchValue;  //v-model  数据绑定 搜索框中的输入值
            this.$http.post('/adminSearchUserVideos',
                {
                    username: username,
                },
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body.length < 1) {
                        alert("用户名不存在，请重新输入！");
                        this.searchValue = "";
                        return;
                    }
                    this.allVideoList = result.body;
                    this.searchValue = "";
                } else {
                    alert('wrong!!!');
                    this.searchValue = "";
                }
            })
        },

        /**
         * 音乐信息
         */
        //查看视频信息页面总条数
        selectMusicNum() {
            this.$http.post('/selectMusicNum',
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    this.all = Math.ceil(result.body / 8);
                    //console.log(result);
                } else {
                    alert('wrong!!!')
                }
            })
        },
        //查看音乐信息
        selectMusicInfo() {
            let pn = JSON.parse(JSON.stringify(this.pageNum));
            if (pn !== 0) {
                pn--;
            }
            this.$http.post('/adminSelectMusicInfo',
                {
                    star: pn * 8,
                    pageSize: 8
                },
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    this.allMusicList = result.body;
                    console.log(this.allMusicList);
                } else {
                    alert('wrong!!!')
                }
            });
            this.selectMusicNum();
        },
        //查看音乐信息页面跳转
        queryMusicInfoPageSkip() {
            var maxPage = this.all;
            var skipPage = Number(document.getElementById("input-queryMusicInfo").value);
            if (!skipPage) {
                alert("请输入跳转页码");
            } else if (skipPage < 1 || skipPage > maxPage) {
                alert("您输入的页码超过页数范围了！");
            } else {
                if (skipPage !== this.cur) {
                    this.cur = skipPage;
                    this.pageNum = skipPage;
                }
                this.selectMusicInfo();
            }
        },
        //从缓存（musicFilename）中获取点击查看的音乐详细信息
        queryMusicDetailsInfo(musicFilename) {
            //console.log(this.allMusicList);
            this.allMusicList.forEach(musicList => {
                if (musicList.musicFilename === musicFilename) {
                    this.musicList = musicList;
//console.log(this.musicList);
                    let musicFilename = this.musicList.musicFilename;
                    let musicAuthor = this.musicList.musicAuthor;
                    let musicTittle = this.musicList.musicTittle;
                    let musicCover = this.musicList.musicCover;
                    let musicLrc = this.musicList.musicLrc;
                    let ap = new APlayer
                    ({
                        element: document.getElementById('player1'),
                        lrcType: 3,
                        autoplay: true,
                        showlrc: true,
                        audio: {
                            title: musicTittle,
                            author: musicAuthor,
                            url: "/files/music/" + musicFilename,
                            cover: "/files/musicCover/" + musicCover,
                            lrc: '/files/lrc/' + musicLrc,
                        }
                    });
                    // ap.init();


                }
            })
        },
        //根据用户输入的用户名搜索用户全部音乐信息
        searchUserMusics() {
            let username = this.searchValue;  //v-model  数据绑定 搜索框中的输入值
            this.$http.post('/adminSearchUserMusics',
                {
                    username: username,
                },
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body.length < 1) {
                        alert("用户名不存在，请重新输入！");
                        this.searchValue = "";
                        return;
                    }
                    this.allMusicList = result.body;
                    this.searchValue = "";
                } else {
                    alert('wrong!!!');
                    this.searchValue = "";
                }
            })
        },

        /**
         * 审核视频相关功能
         */
        //审核视频页面总条数
        selectUncheckVideoNum() {
            this.$http.post('/selectUncheckVideoNum',
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    this.all = Math.ceil(result.body / 8);

                } else {
                    alert('wrong!!!')
                }
            });
        },
        //审核视频
        selectUncheckVideoInfo() {
            let pn = JSON.parse(JSON.stringify(this.pageNum));
            if (pn !== 0) {
                pn--;
            }
            this.$http.post('/adminSelectUncheckVideoInfo',
                {
                    star: pn * 8,
                    pageSize: 8
                },
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    this.allUncheckedVideoList = result.body;
                } else {
                    alert('wrong!!!')
                }
            });
            this.selectUncheckVideoNum();


        },
        //审核视频页面跳转
        queryUncheckVideoInfoPageSkip() {
            var maxPage = this.all;
            var skipPage = Number(document.getElementById("input-queryUncheckVideoInfo").value);
            if (!skipPage) {
                alert("请输入跳转页码");
            } else if (skipPage < 1 || skipPage > maxPage) {
                alert("您输入的页码超过页数范围了！");
            } else {
                if (skipPage !== this.cur) {
                    this.cur = skipPage;
                    this.pageNum = skipPage;
                }
                this.selectUncheckVideoInfo();
            }
        },
        //从缓存（videoIdentityDocument）中获取点击查看的视频详细信息
        queryUncheckVideoDetailsInfo(videoIdentityDocument) {
            this.allUncheckedVideoList.forEach(uncheckVideoList => {
                if (uncheckVideoList.videoIdentityDocument === videoIdentityDocument) {
                    this.uncheckVideoList = uncheckVideoList;
                    //根据路径找视频
                    this.$refs.video5.src = "/files/video/" + this.uncheckVideoList.videoFilename;
                }
            })
        },
        //通过审核
        passedCheck(videoIdentityDocument) {
            this.$http.post('/passedCheck',
                {
                    videoIdentityDocument: videoIdentityDocument
                },
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    alert("审核通过")
                } else {
                    alert('wrong!!!')
                }
            });
            var index = this.allUncheckedVideoList.findIndex(item => {
                if (item.videoIdentityDocument === videoIdentityDocument) {
                    // item.videoState =1;
                    return true
                }
            });
            //this.allUncheckedVideoList.splice(index,1);
            //this.changeColor = ! this.changeColor;
            console.log(index);
            document.getElementsByClassName('changeColor')[index].style.color = '#129613';
            document.getElementsByClassName('changeColor')[index].innerHTML = '已通过';
        },
    },

    /*分页页面数字监听绑定
    注：（  * watch和computed都是以Vue的依赖追踪机制为基础的，它们都试图处理这样一件事情：当某一个数据（称它为依赖数据）发生变化的时候，
           * 所有依赖这个数据的“相关”数据“自动”发生变化，也就是自动调用相关的函数去实现数据的变动
           * methods里面是用来定义函数的，很显然，它需要手动调用才能执行）
   */
    computed: {
        //分页
        indexs() {
            var left = 1;
            var right = this.all;
            var arr = [];
            if (this.all >= 7) {
                if (this.cur > 4 && this.cur < this.all - 3) {
                    left = this.cur - 3;
                    right = this.cur + 3;
                } else if (this.cur <= 4) {
                    left = 1;
                    right = 7;
                } else {
                    left = this.all - 6;
                    right = this.all;
                }
            }
            while (left <= right) {
                arr.push(left);
                left++;
            }
            return arr;
        }
    },
    mounted() {
        //console.log(5656);
        this.selectInfo_eCharts()
    },
    /*    mounted: function() {
            this.selectInfo_eCharts();

        },*/
});

