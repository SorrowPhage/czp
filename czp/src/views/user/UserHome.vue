<template>
    <div style="height:calc(100vh - 50px);">
        <div class="body_home">
            <RelationGraph ref="graphRef" :options="graphOptions" :on-node-click="onNodeClick" :on-line-click="onLineClick" >
                <template #node="{node}">
                    <slot>
                        <div v-if="node.data!==null">
                            <div style="height:80px;line-height: 80px;border-radius: 32px;cursor: pointer;">
                                <img :src="node.data.avatar" style="border-radius: 50%;width: 100%"/>
                            </div>
                            <div style="font-size: 16px;position: absolute;width: 160px;height:25px;line-height: 25px;margin-top:5px;margin-left:-48px;text-align: center;"
                                 :class="{bcg_color_male:node.data.sex==='男',bcg_color_female:node.data.sex==='女'}"
                            >
                                {{ node.text }}
                            </div>
                        </div>
                        <div v-else>
                            <div style="height:80px;line-height: 80px;border-radius: 32px;cursor: pointer;" >
                                <img src="http://localhost:8088/czp/avatar/4617eed715b44530b86e2a84aa0c0ed3file.png" style="border-radius: 50%;width: 100%"/>
                            </div>
                            <div style="font-size: 16px;position: absolute;width: 160px;height:25px;line-height: 25px;margin-top:5px;margin-left:-48px;text-align: center;">
                                {{ node.text }}
                            </div>
                        </div>
                    </slot>
                </template>
            </RelationGraph>>
        </div>
    </div>
</template>

<script>
import {getRequest} from "@/api/api";
import RelationGraph from 'relation-graph'
export default {
    name: "UserHome",
    components:{RelationGraph},
    data() {
        return{
            treeData: {},
            graphOptions: {
                allowSwitchLineShape: true,
                allowSwitchJunctionPoint: true,
                defaultJunctionPoint: 'border',
                defaultNodeColor: 'rgba(238, 178, 94, 1)',
                defaultNodeBorderColor: "#90EE90",
                defaultNodeShape: 0,
                defaultNodeBorderWidth: 5,
                'layouts': [
                    {
                        'label': '自动布局',
                        'layoutName': 'force',
                        'layoutClassName': 'seeks-layout-force'
                    }
                ],
            }
        }
    },
    mounted() {
        this.showGraph()
    },
    methods:{
        loadData() {
            getRequest("/group/home",{id:this.$store.state.CzpUser.id}).then(res=>{
                console.log(res);
                if (res.code === 200) {
                    this.treeData = res.data;
                }
            })
        },
        showGraph() {
            getRequest("/group/home",{id:this.$store.state.CzpUser.id}).then(res=>{
                console.log(res);
                if (res.code === 200) {
                    // 以上数据中的node和link可以参考"Node节点"和"Link关系"中的参数进行配置
                    this.$refs.graphRef.setJsonData(res.data, (graphInstance) => {
                        // Called when the relation-graph is completed
                    });
                }
            })
        },
        onNodeClick(nodeObject, $event) {
            console.log('onNodeClick:', nodeObject)
        },
        onLineClick(lineObject, $event) {
            console.log('onLineClick:', lineObject)
        }
    }
}
</script>

<style scoped lang="scss">
.body_home {
    height: 100%;
    overflow: hidden;
    width: 100%;
    margin: 0 auto;
}
.body_home /deep/ .rel-map {
    background-image: linear-gradient(
            125deg,
            #2c3e50,
            #27ae60,
            #2980b9,
            #eb4d4b,
            #2c3e50
    );
    background-size: 400%;
    animation: bganimation 15s infinite;
    position: fixed;
}

@keyframes bganimation {
    0% {
        background-position: 0 50%;
    }
    
    50% {
        background-position: 100% 50%;
    }
    
    100% {
        background-position: 0 50%;
    }
}
.el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
}

.el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
}

.el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
}

body > .el-container {
    margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
    line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
    line-height: 320px;
}


.bcg_color_male {
    color: #227cf9;
}

.bcg_color_female {
    color: #f922f5;
}

</style>