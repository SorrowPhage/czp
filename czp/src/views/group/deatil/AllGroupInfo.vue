<template>
    <div class="gd_body">
        <div ref="group_chart" style="width: 600px; height: 400px;"></div>
        <div>
            <group-number title="族群总数" :num="groupNum"/>
            <group-number title="族群总人数" :num="groupPeopleNum"/>
        </div>
    </div>
</template>

<script>
import {hideLoading, hideLoadingAndNotify, showLoading} from "@/api/loading";
import {postRequest} from "@/api/api";
import * as echarts from 'echarts';
import GroupNumber from "@/views/group/deatil/GroupNumber";
export default {
    name: "AllGroupInfo",
    components:{GroupNumber},
    data() {
        return{
            groupPeopleNumerList: [
                { groupName: '观木岩', peopleTotal: 10, livingPeopleTotal: 9 },
                { groupName: '芦坝', peopleTotal: 2, livingPeopleTotal: 2 }
            ],
            groupNum: 5,
            groupPeopleNum: 100,
            
        }
    },
    mounted() {
        this.loadData();
    },
    methods:{
        loadData() {
            showLoading();
            postRequest("/group/large/gdata", {id: this.$route.query.gid}).then(res => {
                if (res.code === 200) {
                    hideLoading();
                    this.groupPeopleNumerList = res.data.groupPeopleNumerList;
                    this.groupNum = res.data.groupNum;
                    this.groupPeopleNum = res.data.groupPeopleNum;
                    this.initGroupPeopleNumber();
                }
            }).catch(err => {
                    hideLoadingAndNotify(err)
                }
            );
        },
        initGroupPeopleNumber() {
            const chart = echarts.init(this.$refs.group_chart);
            const options = {
                title: {
                    text: 'Group People Number'
                },
                tooltip: {},
                xAxis: {
                    data: this.groupPeopleNumerList.map(item => item.groupName)
                },
                yAxis: {},
                series: [{
                    name: 'People Total',
                    type: 'bar',
                    data: this.groupPeopleNumerList.map(item => item.peopleTotal)
                }, {
                    name: 'Living People Total',
                    type: 'bar',
                    data: this.groupPeopleNumerList.map(item => item.livingPeopleTotal)
                }]
            };
            chart.setOption(options);
        },
        
    }
}
</script>

<style scoped>

</style>