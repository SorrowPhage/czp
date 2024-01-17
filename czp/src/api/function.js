import router from "@/router/router";

export function chat(id) {
    router.push({
        name:"chat",
        query:{
            id: id,
        }
    })
}