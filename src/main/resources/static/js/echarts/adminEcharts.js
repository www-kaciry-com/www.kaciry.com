
// 绘制图表。
/*echarts.init(document.getElementById('main2')).setOption({
    series: {
        type: 'pie',
        data: [
            {name: 'A', value: 1212},
            {name: 'B', value: 2323},
            {name: 'C', value: 1919}
        ]
    }
});*/

/*
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

// 指定图表的配置项和数据
var option = {
    title: {
        text: '229 饭量展示'
    },
    tooltip: {},
    legend: {
        data: ['销量']
    },
    xAxis: {
        data: ["刘昊", "康羽", "二嘿", "戴戴", "涛涛", "苟碧川"]
    },
    yAxis: {},
    series: [{
        name: '人数/人',
        type: 'bar',
        data: [5, 20, 36, 10, 10, 20]
    }]
};

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);

var weatherIcons = echarts.init(document.getElementById('main3'));
var option = {
    title: {
        text: '天气情况统计',
        subtext: '虚构数据',
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
        data: ['西凉', '益州', '兖州', '荆州', '幽州']
    },
    series: [
        {
            type: 'pie',
            radius: '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
            data: [
                {
                    value: 1548,
                    name: '幽州',
                    label: {
                        normal: {
                            formatter: [
                                '{title|{b}}{abg|}',
                                '  {weatherHead|天气}{valueHead|天数}{rateHead|占比}',
                                '{hr|}',
                                '  {Sunny|}{value|202}{rate|55.3%}',
                                '  {Cloudy|}{value|142}{rate|38.9%}',
                                '  {Showers|}{value|21}{rate|5.8%}'
                            ].join('\n'),
                            backgroundColor: '#eee',
                            borderColor: '#777',
                            borderWidth: 1,
                            borderRadius: 4,
                            rich: {
                                title: {
                                    color: '#eee',
                                    align: 'center'
                                },
                                abg: {
                                    backgroundColor: '#333',
                                    width: '100%',
                                    align: 'right',
                                    height: 25,
                                    borderRadius: [4, 4, 0, 0]
                                },
                                Sunny: {
                                    height: 30,
                                    align: 'left',
                                    backgroundColor: {
                                        image: weatherIcons.Sunny
                                    }
                                },
                                Cloudy: {
                                    height: 30,
                                    align: 'left',
                                    backgroundColor: {
                                        image: weatherIcons.Cloudy
                                    }
                                },
                                Showers: {
                                    height: 30,
                                    align: 'left',
                                    backgroundColor: {
                                        image: weatherIcons.Showers
                                    }
                                },
                                weatherHead: {
                                    color: '#333',
                                    height: 24,
                                    align: 'left'
                                },
                                hr: {
                                    borderColor: '#777',
                                    width: '100%',
                                    borderWidth: 0.5,
                                    height: 0
                                },
                                value: {
                                    width: 20,
                                    padding: [0, 20, 0, 30],
                                    align: 'left'
                                },
                                valueHead: {
                                    color: '#333',
                                    width: 20,
                                    padding: [0, 20, 0, 30],
                                    align: 'center'
                                },
                                rate: {
                                    width: 40,
                                    align: 'right',
                                    padding: [0, 10, 0, 0]
                                },
                                rateHead: {
                                    color: '#333',
                                    width: 40,
                                    align: 'center',
                                    padding: [0, 10, 0, 0]
                                }
                            }
                        }
                    }
                },
                {value: 535, name: '荆州'},
                {value: 510, name: '兖州'},
                {value: 634, name: '益州'},
                {value: 735, name: '西凉'}
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
weatherIcons.setOption(option);
*/

var weatherIcons2 = echarts.init(document.getElementById('main4'));
weatherIcons2.title = '极坐标系下的堆叠柱状图';
var option = {
    angleAxis: {
        type: 'category',
        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
        z: 10
    },
    radiusAxis: {
    },
    polar: {
    },
    series: [{
        type: 'bar',
        data: [1, 2, 3, 4, 3, 5, 1],
        coordinateSystem: 'polar',
        name: 'A',
        stack: 'a'
    },
        {
        type: 'bar',
        data: [2, 4, 6, 1, 3, 2, 1],
        coordinateSystem: 'polar',
        name: 'B',
        stack: 'a'
    }, {
        type: 'bar',
        data: [1, 2, 3, 4, 1, 2, 5],
        coordinateSystem: 'polar',
        name: 'C',
        stack: 'a'
    }],
    legend: {
        show: true,
        data: ['A', 'B', 'C']
    }
};
// 使用刚指定的配置项和数据显示图表。
weatherIcons2.setOption(option);
