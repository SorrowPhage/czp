<template>
    <table>
        <tr>
            <td :class="{parentLevel: treeData.children, extend: treeData.children && treeData.extend}"
                :colspan="treeData.children ? treeData.children.length * 2 : 1">
                <div :class="{node: true}" >
                    <div v-if="treeData.name" class="person" @click="$emit('click-node', treeData)">
                        <div class="avat" style="border: none">
                            <img :src="avatarUrl" :title="treeData.name"
                                 style="border-radius: 2em;border-width: 2px;"/>
                        </div>
                        <div :class="{bcg_color_male:treeData.sex==='男',bcg_color_female:treeData.sex==='女'}"
                             class="name">
<!--                            <span v-if="treeData.generationRank">-->
<!--                                {{ treeData.generationRank }}:-->
<!--                            </span>-->
                            <span>
                                {{ treeData.name }}
                            </span>
<!--                            <span v-if="treeData.familyRank">-->
<!--                                ({{ treeData.familyRank }})-->
<!--                            </span>-->
                        </div>
                        <el-button  @click="apply(treeData)">加入</el-button>
                    </div>
                    <div v-if="treeData.groupName" class="person">
                        <div class="name group_text">{{ treeData.groupName }}</div>
                        <el-button  @click="apply(treeData)">加入</el-button>
                    </div>
                </div>
                <div v-if="treeData.children" class="extend_handle" @click="toggleExtend(treeData)"></div>
            </td>
        
        </tr>
        <tr v-if="treeData.children && treeData.extend">
            <td v-for="(children, index) in treeData.children" :key="index" class="childLevel" colspan="2">
                <TreeSelect :json="children" :getSelectData="getSelectData" @click-node="$emit('click-node', $event)"/>
            </td>
        </tr>
    </table>
</template>

<script>
export default {
    name: "TreeSelect",
    props: ["json","getSelectData"],
    data() {
        return {
            treeData: {},
            staticAvatarUrl:  require('@/assets/img/czp.jpg') // 静态图片的URL
        }
    },
    computed:{
        avatarUrl: function() {
            return this.treeData.avatar ? this.treeData.avatar : this.staticAvatarUrl;
        }
    },
    watch: {
        json: {
            handler: function (Props) {
                let extendKey = function (jsonData) {
                    jsonData.extend = (jsonData.extend === void 0 ? true : !!jsonData.extend);
                    if (Array.isArray(jsonData.children)) {
                        jsonData.children.forEach(c => {
                            extendKey(c)
                        })
                    }
                    return jsonData;
                }
                if (Props) {
                    this.treeData = extendKey(Props);
                }
            },
            immediate: true
        }
    },
    methods: {
        toggleExtend: function (treeData) {
            treeData.extend = !treeData.extend;
            this.$forceUpdate();
        },
        apply: function (treeData) {
            this.getSelectData(treeData);
        },
    }
}
</script>

<style scoped>
table {
    border-collapse: separate !important;
    border-spacing: 0 !important;
}

td {
    position: relative;
    vertical-align: top;
    padding: 0 0 20px 0;
    text-align: center;
}

.content {
    transform: scale(0.8); /* 缩小内容，可以根据需要调整缩小比例 */
}

.extend_handle {
    position: absolute;
    left: 50%;
    bottom: 10px;
    width: 10px;
    height: 10px;
    padding: 10px;
    transform: translate3d(-15px, 0, 0);
    cursor: pointer;
}

.extend_handle:before {
    content: "";
    display: block;
    width: 100%;
    height: 100%;
    box-sizing: border-box;
    border: 2px solid;
    border-color: #ccc #ccc transparent transparent;
    transform: rotateZ(135deg);
    transform-origin: 50% 50% 0;
    transition: transform ease 300ms;
}

.extend_handle:hover:before {
    border-color: #333 #333 transparent transparent;
}

.extend .extend_handle:before {
    transform: rotateZ(-45deg);
}

.extend::after {
    content: "";
    position: absolute;
    left: 50%;
    bottom: 15px;
    height: 15px;
    border-left: 2px solid #ccc;
    transform: translate3d(-1px, 0, 0)
}

.extend:last-child:after {
    height: 0px
}

.childLevel::before {
    content: "";
    position: absolute;
    left: 50%;
    bottom: 100%;
    height: 15px;
    border-left: 2px solid #ccc;
    transform: translate3d(-1px, 0, 0)
}

.childLevel::after {
    content: "";
    position: absolute;
    left: 0;
    right: 0;
    top: -15px;
    border-top: 2px solid #ccc;
}

.childLevel:first-child:before, .childLevel:last-child:before {
    display: none;
}

.childLevel:first-child:after {
    left: 50%;
    height: 15px;
    border: 2px solid;
    border-color: #ccc transparent transparent #ccc;
    border-radius: 6px 0 0 0;
    transform: translate3d(1px, 0, 0)
}

.childLevel:last-child:after {
    right: 50%;
    height: 15px;
    border: 2px solid;
    border-color: #ccc #ccc transparent transparent;
    border-radius: 0 6px 0 0;
    transform: translate3d(-1px, 0, 0)
}

.childLevel:first-child.childLevel:last-child::after {
    left: auto;
    border-radius: 0;
    border-color: transparent #ccc transparent transparent;
    transform: translate3d(1px, 0, 0)
}

.node .person {
    position: relative;
    display: inline-block;
    z-index: 2;
    overflow: hidden;
}

.node {
    position: relative;
    display: inline-block;
    width: 13em;
    box-sizing: border-box;
    text-align: center;
}

.node .person .avat {
    display: block;
    width: 4em;
    height: 4em;
    margin: auto;
    overflow: hidden;
    background: #fff;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

.node .person .avat img {
    width: 100%;
    height: 100%;
}

.node .person .name {
    height: 2em;
    line-height: 2em;
    overflow: hidden;
    width: 100%;
}

.node.hasMate::after {
    content: "";
    position: absolute;
    left: 2em;
    right: 2em;
    top: 2em;
    border-top: 2px solid #ccc;
    z-index: 1;
}

.node.hasMate .person:last-child {
    margin-left: 1em;
}

.landscape {
    transform: rotate(-90deg);
    padding: 0 4em;
}

.landscape .node {
    text-align: left;
    height: 8em;
    width: 8em;
}

.landscape .person {
    position: relative;
    transform: rotate(90deg);
    padding-left: 4.5em;
    height: 4em;
    top: 4em;
    left: -1em;
}

.landscape .person .avat {
    position: absolute;
    left: 0;
}

.landscape .person .name {
    height: 4em;
    line-height: 4em;
}

.landscape .hasMate {
    position: relative;
}

.landscape .hasMate .person {
    position: absolute;
}

.landscape .hasMate .person:first-child {
    left: auto;
    right: -4em;
}

.landscape .hasMate .person:last-child {
    left: -4em;
    margin-left: 0;
}

.bcg_color_male {
    color: #227cf9;
}

.bcg_color_female {
    color: #f922f5;
}

.group_text {
    color: #545c64;
}

</style>