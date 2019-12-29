let router = new VueRouter({
    routes: [
        //{path:'/playMusic'}
    ],
    mode: 'history',
});
let vm = new Vue({
    el: '#player1',
    data: {
        musicInfo: [],
        musicFilename: '',
    },
    created() {  //页面加载即执行
        this.ssss();

        //this.getParams();

    },
    methods: {
        ssss() {
            this.getParams();
            //console.log(this.musicFilename);
            this.$http.post('/playMusic',
                {
                    musicFilename:this.musicFilename
                },
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body.length < 1) {
                        alert("音乐加载错误!!!");
                    }
                    // console.log(result);
                    this.musicInfo = result.body[0];
                    this.ddd();
                } else {
                    alert('音乐播放失败!!!');
                }
            })
        },
        ddd() {
            //let musicFilename = 'a865927b67d231a1ae4050466c5f9545.mp3';
            let musicFilename = this.musicInfo.musicFilename;
            let musicAuthor = this.musicInfo.musicAuthor;
            let musicTittle = this.musicInfo.musicTittle;
            let musicCover = this.musicInfo.musicCover;
            let musicLrc = this.musicInfo.musicLrc;
            //let lrc ='戴羽彤 - 那女孩对我说 (Live)-22088445644020f1eb061f5d74dd3b24-41617347-00000000.lrc';
            // console.log(lrc);
            /*lrctype
                 file = 3, // 表示 audio.lrc 的值是 lrc 文件地址，将通过 `fetch` 获取 lrc 歌词文本
                 html = 2, // 不支持 html 用法
                 string = 1, // 表示 audio.lrc 的值是 lrc 格式的字符串，将直接通过它解析歌词
                 disabled = 0, // 禁用 lrc 歌词*/
            //console.log(musicCover);
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
                    //url:'/static/a865927b67d231a1ae4050466c5f9545.mp3',
                    //url: 'http://music.163.com/song/media/outer/url?id=287035.mp3',
                    cover: "/files/musicCover/" + musicCover,
                    //cover: "/files/musicCover/" + "灌篮高手.jpg",
                    //unicode格式保存 -------->lrc
                    lrc: '/files/lrc/' + musicLrc,
                    //lrc: '/files/lrc/' + "qqq.lrc",
                    //lrc: 'https://cn-south-17-aplayer-46154810.oss.dogecdn.com/yourname.lrc'
                }
            });
            this.addPlayNum(musicFilename);
            //ap.init();

        },
        getParams() {
            // 取到路由带过来的参数
            //const routerParams = this.$route.query.musicId;
            this.musicFilename = this.$route.query.musicId;
            // 将数据放在当前组件的数据内
            //this.mallInfo.searchMap.mallCode = routerParams;
            //this.keyupMallName()
            //console.log(routerParams1);
            //console.log(routerParams);
        },
        addPlayNum(musicFilename) {
            //alert(555);
            this.$http.post('/addPlayNum',
                {
                    musicFilename:musicFilename
                },
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body.length < 1) {
                        alert("3333333");
                    }
                    console.log("播放量添加成功");
                } else {
                    alert('播放量添加失败!!!');
                }
            })
        },

    },
    router: router

});

//let url5 = 'a865927b67d231a1ae4050466c5f9545.mp3';
//


/*$(document).ready(function () {

    $.ajax({
        url: '/playMusic',//请求的地址
        type: 'post', //请求的方式
        dateType: "json", //请求的数据格式
        data: {
           /!* videoType: videoType,
            length: length,*!/
        },
        error: function () {
            alert("服务器未响应，加载视频信息失败！");
        },
        success: function (result) {
            // console.log("success!");
           // let json = eval(result);
           // let imgTag = $('.Music-card img');
           // let spanTag = $('.Music-card span');
            //let videoTag1 = $('.Music-card a');
            $.each(json, function (i, element) {
              //$(…)[0] 返回的是一个dom对象
                // 而 attr() 方法 只能被JQuery对象所使用
                // 所以，可以用$(…).eq()
              //  imgTag.eq(i).attr("src", "/files/videoCover/" + element.videoCover);
                //添加文字描述
              //  spanTag.eq(i).text(element.videoName);
                // videoTag.eq(i).attr("value",element.videoFileName);
              //  videoTag1.eq(i).attr("href", "/video?videoid=" + element.videoFilename);
            });
            // console.log(videoTag.eq(0).val());
        }
    })
});*/


