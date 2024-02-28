<template>
    <div class="sp-search-content-box">
        <div v-if="list.length">
            <div  class="d-flex flex-column flex-md-row flex-justify-between border-bottom pb-3 position-relative">
                <h3><span class="v-align-middle">{{total}} users </span></h3>
            </div>
            <UserList v-for="user in list"
                      :key="user.id" :id="user.id" :avatar="user.avatar" :name="user.name"
            ></UserList>
            <div class="page-box">
                <el-pagination
                    :page-size="pageSize"
                    :page-sizes="[5, 10, 15, 20]"
                    :total="total"
                    :page-count="pages"
                    background
                    layout="total, prev, pager, next, jumper"
                    next-text="下一页"
                    prev-text="上一页"
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange">
                </el-pagination>
            </div>
        </div>
        <div v-else>
            <el-empty image="http://localhost:8088/upload/sepim/state/empty.jpg"></el-empty>
        </div>
    </div>
</template>

<script>
import UserList from "@/views/search/UserList";
import {getRequest} from "@/api/api";
export default {
    name: "User",
    components: {UserList},
    data() {
        return {
            list: [],
            currentPage: 1,
            pageSize: 10,
            total: 0,
            pages: 0,
        };
    },
    watch: {
        '$route.query.q': {
            handler() {
                this.loadData();
            },
        },
    },
    mounted() {
        this.loadData();
    },
    methods: {
        handleSizeChange(val) {
            this.pagesize = val;
        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.loadData();
        },
        loadData() {
            getRequest("/czpUser/search", {
                id: this.$route.query.q,
                pageIndex: this.currentPage, pageSize: this.pageSize
            }).then(res => {
                console.log(res)
                if (res.code === 200) {
                    this.list = res.data.list;
                    this.pages = res.data.pages;
                    this.total = res.data.total;
                    this.pagesize = res.data.pagesize;
                }
            });
        },
    },
}
</script>

<style scoped>

.page-box {
    padding: 15px;
    text-align: center;
}
.d-flex {
    display: flex !important;
}
.pb-3 {
    padding-bottom: 5px !important;
}
.position-relative {
    position: relative !important;
}
.flex-md-row {
    flex-direction: row !important;
}
.flex-justify-between {
    justify-content: space-between !important;
}
.border-bottom {
    border-bottom: 1px solid #d0d7de !important;
}
.v-align-middle {
    vertical-align: middle !important;
}
h3 {
    font-size: 20px;
    font-weight: 600;
}
</style>