<template>
    <div class="container">
        <el-row class="full-height">
            <el-col :span="12" class="full-height" style="padding: 15px">
                <el-descriptions class="sp-des" title="族群信息" style="font-size: 14px" direction="horizontal" :column="1" >
                    <el-descriptions-item label="族群id" >{{groupInfo.id}}</el-descriptions-item>
                    <el-descriptions-item label="族群名称" >{{groupInfo.groupName}}</el-descriptions-item>
                    <el-descriptions-item label="族群位置" >{{groupInfo.area}}</el-descriptions-item>
                    <el-descriptions-item label="现存人数" >{{groupInfo.livingPeopleTotal}}</el-descriptions-item>
                    <el-descriptions-item label="总人数" >{{groupInfo.peopleTotal}}</el-descriptions-item>
                    <el-descriptions-item label="来源描述" >{{groupInfo.des}}</el-descriptions-item>
                </el-descriptions>
            </el-col>
            <el-col :span="12" class="full-height" style="padding: 15px">
                <el-row style="height: 50%;max-height: 300px">
                    <el-col :span="12" style="height: 100%">
                        <div id="ageChart"  style="width: 100%;height: 100%;background: white"></div>
                    </el-col>
                    <el-col :span="12" style="height: 100%">
                        <div id="maleFemaleChart"  style="width: 100%;height: 100%;background: white"></div>
                    </el-col>
                </el-row>
                <el-row style="height: 50%">
                    <div id="birthChart"  style="width: 100%;height: 100%;background: white"></div>
                </el-row>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import {postRequest} from "@/api/api";
import {hideLoading, hideLoadingAndNotify, showLoading} from "@/api/loading";
import * as echarts from "echarts";

export default {
    name: "GroupInfoCard",
    props: {
        groupInfo: {
            id: '',
            groupName: '',
            area: '',
            livingPeopleTotal: '',
            peopleTotal: '',
            des: '',
            ageData: [], // 存放接口返回的数据
            chartInstance: null, // 存放ECharts实例
            birthAndDeathData: [], // 存放接口返回的数据
            birthInstance: null, // 存放 ECharts 实例
            maleToFemaleInstance: null,
            maleToFemaleData: [],
        },
        
    },
    data() {
        return{
        
        }
    },
    mounted() {
        this.loadData();
    },
    methods: {
        loadData() {
            showLoading();
            postRequest("/group/gdata", {id: this.$route.query.gid}).then(res => {
                if (res.code === 200) {
                    hideLoading();
                    this.ageData = res.data.agePieChartsList;
                    this.birthAndDeathData = res.data.birthAndDeathYearList;
                    this.maleToFemaleData = res.data.maleToFemaleList;
                    this.initChart();
                    this.initBirthChart();
                    this.initMaleToFemale();
                }
            }).catch(err => {
                    hideLoadingAndNotify(err)
                }
            );
        },
        initChart() {
            this.chartInstance = echarts.init(document.getElementById('ageChart'));
            let option = {
                title: {
                    text: '现存人数年龄分布',
                    textStyle: {
                        // color: 'red', // 标题颜色
                        fontSize: 14, // 标题字体大小
                        fontStyle: 'italic' // 标题字体样式
                    }
                },
                tooltip: {
                    show: true // 设置 tooltip 不显示
                },
                legend: {
                    data: this.ageData.map(item => item.birthDecade), // 设置图例数据
                    orient: 'vertical', // 设置图例垂直显示
                    right: 10, // 设置图例距离右侧的距离
                    top: 20, // 设置图例距离顶部的距禯
                    itemWidth: 10, // 设置图例项的宽度
                    itemHeight: 10, // 设置图例项的高度
                },
                series: [{
                    type: 'pie',
                    label: {
                        show: false // 设置 label 不显示
                    },
                    center: ['40%', '50%'],
                    radius: ['0', '50%'], // 设置内半径为50%，外半径为70%
                    data: this.ageData.map(item => ({name: item.birthDecade, value: item.num})),
                    color: ['#2f98aa', '#3366ad', '#2b3346', '#1488fa', '#78f066'], // 设置饼图颜色
                }]
            }
            this.chartInstance.setOption(option)
        },
        initMaleToFemale() {
            this.maleToFemaleInstance = echarts.init(document.getElementById('maleFemaleChart'));
            let option = {
                title: {
                    text: '男女比例',
                    textStyle: {
                        // color: 'red', // 标题颜色
                        fontSize: 14, // 标题字体大小
                        fontStyle: 'italic' // 标题字体样式
                    }
                },
                tooltip: {
                    show: true // 设置 tooltip 不显示
                },
                legend: {
                    data: this.maleToFemaleData.map(item => item.birthDecade), // 设置图例数据
                    orient: 'vertical', // 设置图例垂直显示
                    right: 10, // 设置图例距离右侧的距离
                    top: 20, // 设置图例距离顶部的距禯
                    itemWidth: 10, // 设置图例项的宽度
                    itemHeight: 10, // 设置图例项的高度
                },
                series: [{
                    type: 'pie',
                    label: {
                        show: false // 设置 label 不显示
                    },
                    center: ['40%', '50%'],
                    radius: ['0', '50%'], // 设置内半径为50%，外半径为70%
                    data: this.maleToFemaleData.map(item => ({name: item.birthDecade, value: item.num})),
                    color: ['#f25326', '#07a6f0'], // 设置饼图颜色
                }]
            }
            this.maleToFemaleInstance.setOption(option)
        },
        initBirthChart() {
            this.birthInstance = echarts.init(document.getElementById('birthChart'));
            let option = {
                title: {
                    text: '出生/逝去',
                    textStyle: {
                        // color: 'red', // 标题颜色
                        fontSize: 14, // 标题字体大小
                        // fontStyle: 'italic' // 标题字体样式
                    }
                },
                xAxis: {
                    type: 'category',
                    data: this.birthAndDeathData.map(item => item.year)
                },
                yAxis: {
                    type: 'value'
                },
                legend: {
                    enabled: true, // 设置为true显示图例
                    layout: 'horizontal', // 设置图例的布局方式，可以是horizontal（水平）或vertical（垂直）
                    align: 'right', // 设置图例在图表中的水平位置，可以是left、center、right
                    verticalAlign: 'bottom' // 设置图例在图表中的垂直位置，可以是top、middle、bottom
                },
                tooltip: {
                    trigger: 'axis',
                    formatter: function (params) {
                        let tooltipContent = params[0].name + '年<br>';
                        params.forEach(function (item) {
                            tooltipContent += item.seriesName + ': ' + item.value + '人<br>';
                        });
                        return tooltipContent;
                    }
                },
                series: [{
                    name: '出生人数',
                    data: this.birthAndDeathData.map(item => item.numBirth),
                    type: 'line',
                    color: '#72dcc6' // 设置第一个数据系列的颜色为红色
                }, {
                    name: '死亡人数',
                    data: this.birthAndDeathData.map(item => item.numDeath),
                    type: 'line',
                    color: '#ef553b' // 设置第一个数据系列的颜色为红色
                }]
            }
            this.birthInstance.setOption(option)
        },
    },
    
}
</script>

<style scoped lang="scss">
.sp-des {
    padding: 5px;
    background: white;
}
.container {
    height: calc(100vh - 170px); /* 设置容器高度为视口高度 */
    display: flex; /* 使用Flex布局 */
    justify-content: center; /* 水平居中 */
    align-items: center; /* 垂直居中 */
}
.full-height {
    height: 100%; /* 子元素高度占满父元素高度 */
}
</style>