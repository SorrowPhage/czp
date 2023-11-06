<template>
    <div class="main">
        <div class="wrapper">
            <div class="row">
                <div class="col">
                    <section class="panel">
                        <div class="pane-body">
                            <section>
                                <el-form ref="ruleForm" :model="ruleForm" :rules="rules" class="demo-ruleForm"
                                         label-width="100px"
                                         status-icon>
                                    <el-form-item label="原密码" prop="old_pass">
                                        <el-input v-model="ruleForm.old_pass" autocomplete="off"
                                                  type="password"></el-input>
                                    </el-form-item>
                                    <el-form-item label="新密码" prop="pass">
                                        <el-input v-model="ruleForm.pass" autocomplete="off" type="password"></el-input>
                                    </el-form-item>
                                    <el-form-item label="确认密码" prop="checkPass">
                                        <el-input v-model="ruleForm.checkPass" autocomplete="off"
                                                  type="password"></el-input>
                                    </el-form-item>
                                    <el-form-item>
                                        <el-button type="primary" @click="submitForm('ruleForm')">修改密码</el-button>
                                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                                    </el-form-item>
                                </el-form>
                            </section>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {postRequest} from "@/api/api";
export default {
    name: "PasswordChange",
    data() {
        const validateOldPass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            }
            callback();
        }
        const validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.ruleForm.checkPass !== '') {
                    this.$refs.ruleForm.validateField('checkPass');
                }
                callback();
            }
        };
        const validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.ruleForm.pass) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        return {
            ruleForm: {
                old_pass: '',
                pass: '',
                checkPass: '',
            },
            rules: {
                old_pass: [
                    {validator: validateOldPass, trigger: 'blur'}
                ],
                pass: [
                    {validator: validatePass, trigger: 'blur'}
                ],
                checkPass: [
                    {validator: validatePass2, trigger: 'blur'}
                ],
            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    postRequest("czpUser/updatePassword",
                        {
                            id: this.$store.state.CzpUser.id,
                            password: this.ruleForm.pass,
                            oldPassword: this.ruleForm.old_pass
                        }).then(
                        res => {
                            if (res.code === 200) {
                                this.$message({
                                    message: '修改成功，请重新登录',
                                    showClose: true,
                                    type: 'success',
                                    center: true,
                                });
                                localStorage.removeItem("czpToken");
                                this.$router.push({
                                    name: "login"
                                });
                            } else {
                                this.$message({
                                    showClose: true,
                                    message: '原密码错误,修改失败',
                                    type: 'error',
                                    center: true,
                                });
                            }
                        })
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    }
};
</script>

<style scoped>
* {
    box-sizing: border-box;
}

.main {
    margin-top: 50px;
    height: 100%;
}

.page-heading {
    width: 100%;
    padding: 15px;
    position: relative;
}

.page-heading h3 {
    display: block;
    font-size: 1.17em;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    font-weight: bold;
}

.wrapper {
    padding: 15px;
}

.row {
    margin-left: -15px;
    margin-right: -15px;
}

.col {
    position: relative;
    min-height: 1px;
    padding-left: 15px;
    padding-right: 15px;
    width: 100%;
}

.panel {
    margin-bottom: 20px;
    background-color: white;
    border: 1px solid transparent;
    border-radius: 4px;
}

.table-heading {
    border-bottom: 1px dotted rgba(0, 0, 0, 0.2);
    padding: 15px;
    text-transform: uppercase;
    color: #535351;
    font-size: 14px;
    font-weight: bold;
}

.pane-body {
    display: flex;
    justify-content: center;
    padding: 15px;
}

.table {
    width: 100%;
    margin-bottom: 20px;
}

.table-bordered {
    border: 1px solid #ddd;
}

table {
    border-collapse: collapse;
    border-spacing: 0;
}

.table thead > tr > th,
.table tbody > tr > td {
    padding: 10px;
}

.table-bordered > thead > tr > th {
    border-bottom-width: 2px;
}

.table-bordered > thead > tr > th,
.table-bordered > tbody > tr > td {
    border: 1px solid #ddd;
}

.table-striped > tbody > tr:nth-child(odd) > td,
.table-striped > tbody > tr:nth-child(odd) > th {
    background-color: #f9f9f9e1;
}

th {
    text-align: left;
}
</style>