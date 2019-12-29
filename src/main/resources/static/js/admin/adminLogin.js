let router = new VueRouter({
    routes: [],
    mode: 'history',
});

let vm1 = new Vue({
    el: '#adminLogin',
    data: {
        adminAccount: '',
        password: ''
    },
    created() {

    },
    methods: {
        adminLogin() {
            let adminAccount = this.adminAccount;
            let password = this.password;
            this.$http.post('/adminLogin',
                {
                    adminAccount: adminAccount,
                    password: password
                },
                {emulateJSON: true}).then(result => {
                if (result.status === 200) {
                    if (result.body > 0) {
                        this.$router.push({
                            path: '/admin',
                        });
                        this.$router.go(0);

                    } else {
                        alert("账号或密码错误!!!");
                    }


                } else {
                    alert('管理员系统连接失败!!!');
                }
            })
        },


    },
    router: router,
    mounted() {

    },
    destroyed() {

    },
});
