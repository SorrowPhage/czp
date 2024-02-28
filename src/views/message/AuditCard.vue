<template>
    <div class="card">
        <div>
            <span class="title">申请加入族群{{groupName}}</span>
            <span class="time">&nbsp;&nbsp;&nbsp;{{updateTime}}</span>
        </div>
        <div>
            <span class="text">您于{{createTime}}发起的申请加入{{groupName}}已被该族群管理{{operateId}}{{result}}</span>
        </div>
    </div>
</template>

<script>
export default {
    name: "AuditCard",
    props: ['groupName', 'updateTime', 'createTime', 'operateId', 'status'],
    computed:{
        result() {
            return status === "1" ? "通过" : "拒绝";
        }
    }
}
</script>

<style scoped>
.card{
    padding: 24px 16px;
    background-color: #fff;
    -webkit-box-shadow: 0 2px 4px 0 rgba(121,146,185,0.54);
    box-shadow: 0 2px 4px 0 rgba(121,146,185,0.54);
    margin-bottom: 10px;
    border-radius: 4px;
}
.title {
    color: #333;
    font-weight: 700;
}
.time {
    color: #999;
    font-size: 12px;
    line-height: 22px;
    margin: 0 10px;
}
.text {
    word-break: break-word;
}
</style>