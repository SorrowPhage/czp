<template>
    <div class="container">
        <div style="overflow: hidden;flex: 1;margin-top: 15px;height: 100%">
            <div class="BaseDragResize" style="height: 100%;width: 100%;">
                    <vue-draggable-resizable  class="BaseDragResize-drag" ref="draggableResizable"  w="auto" h="auto" :x="0" :y="0" :resizable="false"  parentLimitation
                                          @resizing="onResize"
                >
                    <div ref="czpTree" @wheel.prevent="handleTableWheel($event)">
                        <TreeChat :json="treeData"   ></TreeChat>
                    </div>
                </vue-draggable-resizable>
            </div>
        </div>
    </div>
</template>

<script>
import {hideLoading, hideLoadingAndNotify, showLoading} from "@/api/loading";
import {getRequest} from "@/api/api";
import 'vue2-org-tree/dist/style.css';
import TreeChat from "@/views/group/TreeChat";
import 'vue-draggable-resizable/dist/VueDraggableResizable.css'

export default {
    name: "GroupTreeDetails",
    components: {TreeChat},
    data() {
        return{
            treeData: {},
        }
    },
    mounted() {
        this.loadData();
    },
    methods: {
        loadData() {
            showLoading();
            getRequest("/group/tree", {id: this.$route.query.gid}).then(res => {
                hideLoading()
                if (res.code === 200) {
                    this.treeData = res.data;
                }
            }).catch(err => {
                    hideLoadingAndNotify(err)
                }
            );
        },
        onResize: function (x, y, width, height) {
            this.x = x
            this.y = y
            this.width = width
            this.height = height
        },
        
        handleTableWheel(event) {
            let obj = this.$refs.czpTree;
            return this.tableZoom(obj, event);
        },
        tableZoom(obj, event) {
            // 一开始默认是100%
            let zoom = parseInt(obj.style.zoom, 10) || 100;
            // 滚轮滚一下wheelDelta的值增加或减少120
            zoom += event.wheelDelta / 12;
            if (zoom > 0) {
                obj.style.zoom = zoom + "%";
            }
            return false;
        },
    },
    
    
};
</script>

<style scoped lang="scss">
.container {
    height: calc(100vh - 170px); /* 设置容器高度为视口高度 */
    /*display: flex; !* 使用Flex布局 *!*/
    /*justify-content: center; !* 水平居中 *!*/
    /*align-items: center; !* 垂直居中 *!*/
}
.full-height {
    height: 100%; /* 子元素高度占满父元素高度 */
}
.BaseDragResize {
    position: relative;
    width: 100%;
    height: 100%;
    //border: 1px solid #2c3e50;
    box-sizing: border-box;
    background-color: white;
    
    //居中
    display: flex;
    justify-content: center;
    //align-items: center;
    //background: linear-gradient(-90deg, rgba(0, 0, 0, 0.1) 1px, transparent 1px) 0% 0% / 10px 10px, linear-gradient(rgba(0, 0, 0, 0.1) 1px, transparent 1px) 0% 0% / 10px 10px;
    &-drag {
        &.vdr {
            border: none;
        }
    }
}
</style>