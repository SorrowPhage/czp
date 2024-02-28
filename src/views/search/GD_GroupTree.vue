<template>
    <div style="overflow: hidden;height: 100%;">
        <div class="BaseDragResize" style="height: 100%;width: 100%;">
            <vue-draggable-resizable  class="BaseDragResize-drag" w="auto" h="auto" :x="0" :y="0" :resizable="false" :key="groupId" parentLimitation
                                      @resizing="onResize"
            >
                <TreeChat :json="treeData"
                          ref="czpTree" @wheel.prevent="handleTableWheel($event)" ></TreeChat>
            </vue-draggable-resizable>
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
    name: "GD_GroupTree",
    components:{TreeChat},
    data() {
        return{
            treeData: {},
        }
    },
    computed:{
        groupId() {
            return this.treeData.id;
        },
    },
    mounted() {
        this.loadGroupTreeData();
    },
    methods:{
        loadGroupTreeData() {
            showLoading();
            getRequest("/group/tree", {id: this.$route.query.id}).then(res => {
                console.log("GroupTree", res)
                // hideLoadingAndNotify(res);
                hideLoading()
                if (res.code === 200) {
                    this.treeData = res.data;
                }
            }).catch(err => {
                    hideLoadingAndNotify(err)
                    // hideLoading();
                }
            );
        },
        handleTableWheel(event) {
            let obj = this.$refs.czpTree;
            return this.tableZoom(obj, event);
        },
        onResize: function (x, y, width, height) {
            this.x = x
            this.y = y
            this.width = width
            this.height = height
        },
    }
}
</script>

<style scoped lang="scss">
.BaseDragResize {
    position: relative;
    width: 100%;
    height: 100%;
    border: 1px solid #2c3e50;
    box-sizing: border-box;
    background-color: white;

//居中
display: flex;
    justify-content: center;
    align-items: center;
//background: linear-gradient(-90deg, rgba(0, 0, 0, 0.1) 1px, transparent 1px) 0% 0% / 10px 10px, linear-gradient(rgba(0, 0, 0, 0.1) 1px, transparent 1px) 0% 0% / 10px 10px;
&-drag {
&.vdr {
     border: none;
 }
}
}
</style>