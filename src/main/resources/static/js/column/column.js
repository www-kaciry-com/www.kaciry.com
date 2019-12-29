let vm1 = new Vue({
    el: '#column',
    data: {
        columnList: [],
    },
    created() {
        this.selectColumn();
    },
    methods: {
        selectColumn() {
            this.$http.post('/selectColumn',
                {},
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body.length < 1) {
                        alert("3333333");
                    }
                    console.log(result);
                    this.columnList = result.body;

                } else {
                    alert('111111111');
                }
            })
        },


        /* handleScroll() {
             //变量scrollTop是滚动条滚动时，距离顶部的距离
             var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
             //变量windowHeight是可视区的高度
             var windowHeight = document.documentElement.clientHeight || document.body.clientHeight;
             //变量scrollHeight是滚动条的总高度
             var scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
             //滚动条到底部的条件
             if (scrollTop + windowHeight == scrollHeight && this.list.length !== 0) {
                 this.loadMore() // 加载的列表数据
             }
         }*/

    },
    mounted() {
        //window.addEventListener('scroll', this.handleScroll)
    },
    destroyed() {
        //window.removeEventListener('scroll', this.handleScroll)
    },
});
