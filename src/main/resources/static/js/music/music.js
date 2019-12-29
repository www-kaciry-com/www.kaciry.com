let router = new VueRouter({
    routes: [
        //{path:'/playMusic'}
    ],
    mode: 'history',
});
let vm1 = new Vue({
    el: '#musicIndex',
    data: {
        hotMusicList: [],
        popMusicList: [],
        netMusicList: [],
        englishMusicList: [],
        oldMusicList: [],
        searchMusicValue: '',//v-model绑定搜索框中的输入值
    },
    created() {  //页面加载即执行
        this.selectPageMusics();
        /*this.selectPopMusic();
       this.selectNetMusic();
        this.selectEnglishMusic();
        this.selectOldMusic();*/
    },
    methods: {
        selectPageMusics() {
            this.$http.post('/selectPageMusics',
                {},
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body.length < 1) {
                        alert("3333333");
                    }
                    this.hotMusicList = result.body[0];
                    this.popMusicList = result.body[1];
                    this.netMusicList = result.body[2];
                    this.englishMusicList = result.body[3];
                    this.oldMusicList = result.body[4];
                    //this.imgSrc = "/files/musicCover/" + 'timg.jpg';
                    //this.imgSrc = "/files/musicCover/" + '20191122163017651.jpg';
                    //this.musicCover = this.hotMusicList[0].musicCover;
                    //this.$refs.hotMusic1.src = "/files/musicCover/" + 'timg.jpg';

                } else {
                    alert('111111111');
                }
            })
        },
/*        selectPopMusic() {
            this.$http.post('/selectPopMusic',
                {},
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body.length < 1) {
                        alert("3333333");
                    }
                    this.popMusicList = result.body;
                } else {
                    alert('111111111');
                }
            })
        },
        selectNetMusic() {
            this.$http.post('/selectNetMusic',
                {},
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body.length < 1) {
                        alert("3333333");
                    }
                    this.netMusicList = result.body;
                } else {
                    alert('111111111');
                }
            })
        },
        selectEnglishMusic() {
            this.$http.post('/selectEnglishMusic',
                {},
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body.length < 1) {
                        alert("3333333");
                    }
                    this.englishMusicList = result.body;
                    console.log(this.englishMusicList);
                } else {
                    alert('111111111');
                }
            })
        },
        selectOldMusic() {
            this.$http.post('/selectOldMusic',
                {},
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body.length < 1) {
                        alert("3333333");
                    }
                    this.oldMusicList = result.body;
                } else {
                    alert('111111111');
                }
            })
        },*/



        //锚链接定位
        locateAt(e) {
            /*  document.getElementById(e).scrollIntoView();
              alert(e)*/
            e = document.getElementById(e);/*以id命名的锚点*/
            let y = e.offsetTop;
            while (e === e.offsetParent) {
                y += e.offsetTop;
            }
            y -= 55;/*悬浮菜单的高度*/
            window.scrollTo(0, y);
        },


        playMusic(t, e) {
            //alert(e);
            //console.log(this.$router);
            //this.$router.path.split("/");
            let musicFilename;
            switch (t) {
                case 'hot':
                    musicFilename = this.hotMusicList[e].musicFilename;
                    break;
                case 'pop':
                    musicFilename = this.popMusicList[e].musicFilename;
                    break;
                case 'net':
                    musicFilename = this.netMusicList[e].musicFilename;
                    break;
                case 'english':
                    musicFilename = this.englishMusicList[e].musicFilename;
                    break;
                case 'old':
                    musicFilename = this.oldMusicList[e].musicFilename;
                    break;
            }
            this.$router.push({
                //path: '/music/playMusic',
                path: '/qwe',
                // component: resolve => require(['../jumpMusic/music/playMusic'],resolve),
                query: {musicId: musicFilename},
            });

        },


        searchMusic() {
             let musicName = this.searchMusicValue;  //v-model  数据绑定 搜索框中的输入值
         /*   if (musicName == null) {
                alert("请输入音乐名称!")
            }*/
            //console.log(musicName);
            this.$router.push({
                path: '/searchMusic',
                query: {musicName: musicName},
            });

        },
        playMoreMusic(t) {
            let musicType = t;
            alert(musicType);
            this.$router.push({
                path: '/moreMusic',
                query: {musicType: musicType},
            });
            //console.log(this.$router.path);
            //this.$router.push({name:'playMusic'})
            //this.$router.go(0);
        },
    },
    router: router,
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
});