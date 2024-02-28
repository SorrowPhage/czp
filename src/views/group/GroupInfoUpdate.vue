<template>
    <div style="margin: 5px">
        <div class="gcard" @click="dialogVisible = true">
            <div class="gcard_top">
                <div style="display: inline-block">
                    <span class="gcard_text">{{ groupName }}</span>
                </div>
            </div>
            <div class="gcard_body">
                <div class="body_body text_color">
                    {{ des }}
                </div>
                <div style="height: 20px;margin-top: 5px;margin-bottom: 5px">
                    <create-card v-for="u in create" :key="u.id"
                                 :avatar="u.avatar" :name="u.name"
                    />
                </div>
            </div>
            <div class="gcard_foot text_color">
                <div>
                    <i class="el-icon-location-outline"></i>
                    <span>{{ area }}</span>
                </div>
                <div class="foot_card">
                    <i class="el-icon-alarm-clock"></i>
                    <span>{{ createTime }}</span>
                </div>
                <div v-if="parentName" class="foot_card">
                    <i class="el-icon-d-caret"></i>
                    <span>{{ parentName }}</span>
                </div>
            </div>
        </div>
        <el-dialog
            :before-close="handleClose"
            :visible.sync="dialogVisible"
            title="修改信息"
            width="50%">
            <div>
                <el-form ref="form" :model="group" :rules="rules" label-width="80px" size="medium" >
                    <el-form-item label="族群名称" prop="groupName">
                        <el-input v-model="group.groupName"></el-input>
                    </el-form-item>
                    <el-form-item label="地理位置" prop="area">
                        <el-input v-model="group.area"></el-input>
                    </el-form-item>
                    <el-form-item label="创建时间" prop="createTime">
                        <el-input v-model="group.createTime"></el-input>
                    </el-form-item>
                    <el-form-item label="父级id">
                        <el-input  v-model="group.parentId"></el-input>
                    </el-form-item>
                    <el-form-item label="族群描述">
                        <el-input type="textarea" v-model="group.des"></el-input>
                    </el-form-item>
                </el-form>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="updateGroupInfo">修 改</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import {getRequest,postRequest} from "@/api/api";
import CreateCard from "@/views/group/CreateCard";
import {showLoading,hideLoading} from "@/api/loading";
export default {
    name: "GroupInfoUpdate",
    props: ["id", "groupName", "des", "area", "parentId", "parentName", "createTime","clanElder"],
    components: {CreateCard},
    data() {
        return {
            create: [],
            dialogVisible: false,
            group: {
                id: this.id,
                groupName: this.groupName,
                des: this.des,
                area: this.area,
                parentId: this.parentId,
                parentName: this.parentName,
                createTime: this.createTime,
            },
            rules: {
                groupName: [
                    {required: true, message: "请输入族群名称", trigger: "blur"}
                ],
                area: [
                    {required: true, message: "请输入地区名称", trigger: "blur"}
                ],
                createTime: [
                    {required: true, message: "请输入族群创建时间", trigger: "blur"}
                ]
            },
        }
    },
    mounted() {
        this.loadCreateList();
    },
    methods: {
        loadCreateList() {
            getRequest("/group/createList", {id: this.id}).then(res => {
                console.log("GroupCard", res);
                if (res.code === 200) {
                    this.create = res.data;
                }
            })
        },
        handleClose(done) {
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => {
                });
        },
        updateGroupInfo() {
            showLoading()
            postRequest("/group/update",this.group).then(res=>{
                if (res.code === 200) {
                    this.$message({
                        message: res.message,
                        type: "success",
                    });
                    hideLoading()
                    this.dialogVisible = false;
                } else {
                    hideLoading()
                    this.$message({
                        message: res.message,
                        type: "warning",
                    });
                }
            })
        },
    }
}
</script>

<style scoped>
.gcard {
    /*background-color: white;*/
    background-image: linear-gradient(#409EFF, #7ce6f1);
    padding: 15px;
    display: flex;
    flex-wrap: wrap;
    width: 120px;
    margin-left: 5px;
}

.gcard:hover {
    cursor: pointer;
}

.gcard_text {
    font-size: 20px;
    font-weight: bold;
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    color: #5a8da1;
}

.name_text {
    color: #5a8da1;
}

.gcard_top {
    width: 100%;
    height: 20px;
    /*display: flex;*/
}

.gcard_btn {
    /*width: 100px;*/
    float: right;
    /*margin-right: 10px;*/
    /*display: inline-block;*/
}

.gcard_body {
    width: 100%;
    display: flex;
    margin-top: 10px;
    flex-wrap: wrap;
    height: 120px;
}

.body_body {
    width: 100%;
    height: 100px;
    overflow: auto;
}

.text_color {
    color: #5a8da1;
}

.gcard_foot {
    width: 100%;
    display: flex;
    margin-top: 10px;
    height: 20px;
}

.foot_card {
    margin-left: 15px;
}
</style>