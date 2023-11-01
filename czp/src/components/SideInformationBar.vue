<template>
    <div style="display: inline-block;margin-top: 15px">
        <div class="side-info-box" style="margin-right: 5px;height: 100%" @click="loadInformation"><i
            class="el-icon-menu"></i></div>
        <el-drawer
            :before-close="handleClose"
            :direction="direction"
            :visible.sync="drawer"
            :with-header="false"
            append-to-body
        >
            <div>
            
            </div>
        </el-drawer>
    </div>
</template>

<script>
export default {
    name: "SideInformationBar",
    data() {
        return{
            drawer: false,
            direction: 'ltr',
        }
    },
    methods:{
        handleClose(done) {
            done();
        },
        loadInformation() {
            this.drawer = true;
        },
    }
}
</script>

<style scoped>
.side-info-box {
    display: inline;
    margin-left: 10px;
    font-weight: bold;
    font-family: ui-monospace, SFMono-Regular, SF Mono, Menlo, Consolas, Liberation Mono, monospace !important;
    color: #409EFF;
    font-size: 16px !important;
}
</style>