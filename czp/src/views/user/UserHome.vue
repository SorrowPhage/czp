<template>
<!--    <div style="height:calc(100vh - 50px);">-->
    <div class="gd_body">
        <div class="watercolor-font">
<!--                        <p>平地干戈闹如蛙，洪军赶散各生涯。</p>-->
<!--            <p>-->
<!--                <span style="&#45;&#45;i: 1;">平</span>-->
<!--                <span style="&#45;&#45;i: 2;">地</span>-->
<!--                <span style="&#45;&#45;i: 3;">干</span>-->
<!--                <span style="&#45;&#45;i: 4;">戈</span>-->
<!--                <span style="&#45;&#45;i: 5;">闹</span>-->
<!--                <span style="&#45;&#45;i: 6;">如</span>-->
<!--                <span style="&#45;&#45;i: 7;">蛙</span>-->
<!--                <span style="&#45;&#45;i: 8;">，</span>-->
<!--                <span style="&#45;&#45;i: 9;">洪</span>-->
<!--                <span style="&#45;&#45;i: 10;">军</span>-->
<!--                <span style="&#45;&#45;i: 11;">赶</span>-->
<!--                <span style="&#45;&#45;i: 12;">散</span>-->
<!--                <span style="&#45;&#45;i: 13;">各</span>-->
<!--                <span style="&#45;&#45;i: 14;">生</span>-->
<!--                <span style="&#45;&#45;i: 15;">涯</span>-->
<!--                <span style="&#45;&#45;i: 16;">。</span>-->
<!--            </p>-->
            <!--            <p>五马太子如雷吼，永浙渠南江县巴。</p>-->
<!--            <p>-->
<!--                <span style="&#45;&#45;i: 17;">五</span>-->
<!--                <span style="&#45;&#45;i: 18;">马</span>-->
<!--                <span style="&#45;&#45;i: 19;">太</span>-->
<!--                <span style="&#45;&#45;i: 20;">子</span>-->
<!--                <span style="&#45;&#45;i: 21;">如</span>-->
<!--                <span style="&#45;&#45;i: 22;">雷</span>-->
<!--                <span style="&#45;&#45;i: 23;">吼</span>-->
<!--                <span style="&#45;&#45;i: 24;">，</span>-->
<!--                <span style="&#45;&#45;i: 25;">永</span>-->
<!--                <span style="&#45;&#45;i: 26;">浙</span>-->
<!--                <span style="&#45;&#45;i: 27;">渠</span>-->
<!--                <span style="&#45;&#45;i: 28;">南</span>-->
<!--                <span style="&#45;&#45;i: 29;">江</span>-->
<!--                <span style="&#45;&#45;i: 30;">县</span>-->
<!--                <span style="&#45;&#45;i: 31;">巴</span>-->
<!--                <span style="&#45;&#45;i: 32;">。</span>-->
<!--            </p>-->
            <!--            <p>有人记得诗八句，子子孙孙不得差。</p>-->
<!--            <p>-->
<!--                <span style="&#45;&#45;i: 33;">有</span>-->
<!--                <span style="&#45;&#45;i: 34;">人</span>-->
<!--                <span style="&#45;&#45;i: 35;">记</span>-->
<!--                <span style="&#45;&#45;i: 36;">得</span>-->
<!--                <span style="&#45;&#45;i: 37;">诗</span>-->
<!--                <span style="&#45;&#45;i: 38;">八</span>-->
<!--                <span style="&#45;&#45;i: 39;">句</span>-->
<!--                <span style="&#45;&#45;i: 40;">，</span>-->
<!--                <span style="&#45;&#45;i: 41;">子</span>-->
<!--                <span style="&#45;&#45;i: 42;">子</span>-->
<!--                <span style="&#45;&#45;i: 43;">孙</span>-->
<!--                <span style="&#45;&#45;i: 44;">孙</span>-->
<!--                <span style="&#45;&#45;i: 45;">不</span>-->
<!--                <span style="&#45;&#45;i: 46;">得</span>-->
<!--                <span style="&#45;&#45;i: 47;">差</span>-->
<!--                <span style="&#45;&#45;i: 48;">。</span>-->
<!--            </p>-->
            <!--            <p>倘有一子不认祖，世世代代丧黄沙。</p>-->
<!--            <p>-->
<!--                <span style="&#45;&#45;i: 49;">倘</span>-->
<!--                <span style="&#45;&#45;i: 50;">有</span>-->
<!--                <span style="&#45;&#45;i: 51;">一</span>-->
<!--                <span style="&#45;&#45;i: 52;">子</span>-->
<!--                <span style="&#45;&#45;i: 53;">不</span>-->
<!--                <span style="&#45;&#45;i: 54;">认</span>-->
<!--                <span style="&#45;&#45;i: 55;">祖</span>-->
<!--                <span style="&#45;&#45;i: 56;">，</span>-->
<!--                <span style="&#45;&#45;i: 57;">世</span>-->
<!--                <span style="&#45;&#45;i: 58;">世</span>-->
<!--                <span style="&#45;&#45;i: 59;">代</span>-->
<!--                <span style="&#45;&#45;i: 60;">代</span>-->
<!--                <span style="&#45;&#45;i: 61;">丧</span>-->
<!--                <span style="&#45;&#45;i: 62;">黄</span>-->
<!--                <span style="&#45;&#45;i: 63;">沙</span>-->
<!--                <span style="&#45;&#45;i: 64;">。</span>-->
<!--            </p>-->
        </div>
    </div>
</template>

<script>

export default {
    name: "UserHome",

    data() {
        return{

        }
    },
    mounted() {
 
    },
    methods:{
    
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
//.watercolor-font {
//    font-family: 'YourWatercolorFont', cursive;
//    font-size: 24px;
//    color: black;
//    text-align: center;
//    margin-top: 50px;
//    opacity: 0;
//    animation: fadeIn 2s forwards;
//}
//
//@keyframes fadeIn {
//    from {
//        opacity: 0;
//    }
//    to {
//        opacity: 1;
//    }
//}
//
//.watercolor-font p {
//    animation: fadeIn 2s forwards;
//    opacity: 0;
//}
//
//.watercolor-font p:nth-child(1) {
//    animation-delay: 2s;
//}
//
//.watercolor-font p:nth-child(2) {
//    animation-delay: 4s;
//}
//
//.watercolor-font p:nth-child(3) {
//    animation-delay: 6s;
//}
//
//.watercolor-font p:nth-child(4) {
//    animation-delay: 8s;
//}
.watercolor-font {
    font-family: 'YourWatercolorFont', cursive;
    font-size: 24px;
    color: black;
    text-align: center;
    margin-top: 50px;
}

.watercolor-font span {
    opacity: 0;
    animation: fadeIn 1s forwards;
    animation-delay: calc(0.1s * var(--i));
    display: inline-block;
}

@keyframes fadeIn {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}
</style>