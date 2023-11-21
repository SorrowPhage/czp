<template>
    <div class="dialog">
        <div class="title">
            <span style="font-size: 14px;">{{id}}</span>
        </div>
        <div class="message-list">
            <div class="message-list-content min_h_100">
                asd
            </div>
        </div>
        <div class="send-box">
                asd
        </div>
    </div>
</template>

<script>
export default {
    name: "ChatContent",
    props: ['id'],
}
</script>

<style scoped lang="less">
.dialog{
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    -ms-flex-direction: column;
    flex-direction: column;
    -webkit-box-align: stretch;
    -ms-flex-align: stretch;
    align-items: stretch;
    height: 100%;
    .title{
        height: 36px;
        -ms-flex-negative: 0;
        flex-shrink: 0;
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        justify-content: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        align-items: center;
        border-bottom: 1px solid #e9eaec;
        position: relative;
        text-align: center;
        color: #333;
    }
    .message-list {
        -webkit-box-flex: 1;
        -ms-flex: 1;
        flex: 1;
        position: relative;
        overflow-x: hidden;
        overflow-y: scroll;
        background-color: #f4f5f7;
        .message-list-content{
            overflow: auto;
        }
        .min_h_100 {
            min-height: 100%!important;
        }
    }
    .send-box {
        height: 162px;
        border-top: 1px solid #d8d8d8;
        border-bottom-right-radius: 4px;
        background-color: #f4f5f7;
        -ms-flex-negative: 0;
        flex-shrink: 0;
        padding: 0 16px;
        position: relative;
        z-index: 2;
    }
}
</style>