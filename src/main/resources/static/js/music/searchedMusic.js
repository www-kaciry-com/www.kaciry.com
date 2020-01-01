let router1 = new VueRouter({
    routes: [
        //{path:'/playMusic'}
    ],
    mode: 'history',
});
let vm2 = new Vue({
    el: '#moreMusic',
    data: {
        searchedMusicList: [],
        /**
         * 分页相关数据
         */
        cur: 1,
        all: 0,
        index: '',
        pageNum: 0,
        star: 0,
    },
    created() {  //页面加载即执行
        this.searchedMusic();
    },
    methods: {
        searchedMusic() {
            //接收参数
            let musicName = this.$route.query.musicName;
            // console.log(musicName);
            this.$http.post('/searchedMusic',
                {
                    musicName: musicName
                },
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body.length < 1) {
                        alert("你查找的音乐不存在!!!");
                    }
                    //console.log(result);
                    this.searchedMusicList = result.body;
                    // this.all = Math.ceil(result.body.length / 24);

                } else {
                    alert('音乐加载失败!!!');
                }
            })
        },
        playMusic(musicFilename) {
            this.$router.push({
                path: '/playMusic',
                query: {musicId: musicFilename},
            });
            this.addPlayNum(musicFilename);
        },
        addPlayNum(musicFilename) {
            alert(555);
            this.$http.post('/addPlayNum',
                {
                    musicFilename: musicFilename
                },
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body.length < 1) {
                        alert("3333333");
                    }
                    console.log("播放量添加成功");
                } else {
                    alert('111111111');
                }
            })
        },

    },
    computed: {
        //分页
        /*indexs() {
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
        }*/
    },
    router: router1,
});
