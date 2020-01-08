let vm1 = new Vue({
    el: '#column',
    data: {
        b: 0,
        a: 5,
        num: 0,
        page: 1,
        page_count: '',
        columnList: [],
    },
    mounted() {
        this.selectColumn();
    },
    methods: {
        loadMore() {
            this.a += 5;
            this.b += 5;
            this.selectColumn();
        },
        resetNum(a) {
            this.a = 5;
            this.b = 0;
            this.num = a;
            this.selectColumn()
        },
        selectColumn() {
            if (this.num === 0) {
                this.$http.post('/selectColumn',
                    {},
                    {emulateJSON: true}).then(result => {
                    if (result.status === 200) {
                        if (result.body.length < 1) {
                            alert("未查询到专栏信息");
                        }
                        console.log(result);
                        this.columnList = result.body.concat(result.body);

                    } else {
                        alert('加载失败');
                    }
                })
            } else if (this.num === 1) {
                this.$http.post('/selectTodayColumn',
                    {},
                    {emulateJSON: true}).then(result => {
                    if (result.status === 200) {
                        if (result.body.length < 1) {
                            alert("今日没有用户发布专栏信息");
                        }
                        console.log(result);
                        this.columnList = result.body.concat(result.body);
                    } else {
                        alert('加载失败');
                    }
                })
            } else if (this.num === 2) {
                this.$http.post('/selectThreeDaysColumn',
                    {},
                    {emulateJSON: true}).then(result => {
                    if (result.status === 200) {
                        if (result.body.length < 1) {
                            alert("最近三天没有用户专栏信息");
                        }
                        console.log(result);
                        this.columnList = result.body.concat(result.body);
                    } else {
                        alert('加载失败');
                    }
                })
            }

        },


    },
});
