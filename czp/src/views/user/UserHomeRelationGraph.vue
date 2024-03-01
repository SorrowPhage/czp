<template>
    <div style="height:calc(100vh - 50px);">
        <div class="body_home">
            <div class="g_toolbar">
                <el-select v-model="value" placeholder="请选择" size="mini" :popper-append-to-body="false">
                    <el-option
                        v-for="item in options"
                        :key="item.id"
                        :label="item.groupName"
                        :value="item.id">
                    </el-option>
                </el-select>
            </div>
            <RelationGraph ref="graphRef" :options="graphOptions" :on-node-click="onNodeClick" :on-line-click="onLineClick" >
                <template #node="{node}">
                    <slot>
                        <div v-if="node.data!==null">
                            <div style="height:80px;line-height: 80px;border-radius: 32px;">
                                <img :src="node.data.avatar" style="border-radius: 50%;width: 100%" alt=""/>
                            </div>
                            <div style="font-size: 16px;position: absolute;width: 160px;height:25px;line-height: 25px;margin-top:5px;margin-left:-48px;text-align: center;"
                                 :class="{bcg_color_male:node.data.sex==='男',bcg_color_female:node.data.sex==='女'}"
                            >
                                {{ node.text }}
                            </div>
                        </div>
                        <div v-else>
                            <div style="height:80px;line-height: 80px;border-radius: 32px;" >
<!--                                <img src="http://localhost:8088/czp/avatar/4617eed715b44530b86e2a84aa0c0ed3file.png" style="border-radius: 50%;width: 100%" alt=""/>-->
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
            options: [],
            value: '',
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
                        'layoutClassName': 'seeks-layout-force',
                    }
                ],
            }
        }
    },
    watch:{
        value:{
            handler() {
                this.showGraph();
            },
        }
    },
    mounted() {
        this.loadData();
    },
    methods:{
        loadData() {
            getRequest("/group/userGroups",{id:this.$store.state.CzpUser.id}).then(res=>{
                console.log(res);
                if (res.code === 200) {
                    this.options = res.data;
                    this.value = this.options[0].id;
                }
            })
        },
        showGraph() {
            // getRequest("/group/home",{id:this.$store.state.CzpUser.id}).then(res=>{
            //     console.log(res);
            //     if (res.code === 200) {
            //         this.$refs.graphRef.setJsonData(res.data, (graphInstance) => {
            //
            //         });
            //     }
            // })
            getRequest("/group/groupRG",{id:this.value}).then(res=>{
                console.log(res);
                if (res.code === 200) {
                    res.data.rootId = this.$store.state.CzpUser.id;
                    this.$refs.graphRef.setJsonData(res.data, (graphInstance) => {
                
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
            #255877,
            #287263
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
.g_toolbar {
    top: 60px;
    left: 16px;
    //height: 45px;
    max-width: 270px;
    position: absolute;
    z-index: 300;
    background-color: #dcdcdc33;
    color: #999;
    box-shadow: 0 0 2px #ccc;
    box-sizing: border-box;
    border-radius: 5px;
    padding: 3px;
}
/deep/ .el-select-dropdown {
    //background-color: #dcdcdc33;
    border: none;
    font-family: 华文行楷,serif;
}
///deep/ .el-select-dropdown__item.hover {
//    background-color: #545c64;
//}
/deep/ .el-input__inner {
    background-color: rgba(148, 20, 20, 0);
    border: none;
    font-family: 华文行楷,serif;
}
///deep/ .popper__arrow::after {
//    border-bottom-color: rgba(225, 24, 24, 0.9);
//}
.g_button {
    height: 40px;
    margin-top: 0;
    opacity: 1;
    text-align: center;
    padding-top: 3px;
    cursor: pointer;
    font-size: 18px;
    box-sizing: border-box;
    line-height: 21px;
    display: flex;
    align-items: center;
    border-radius: 3px;
}
.g_icon {
    width: 16px;
    height: 16px;
    vertical-align: -3px;
    fill: currentColor;
    overflow: hidden;
}
</style>