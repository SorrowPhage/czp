<template>
    <div class="num_content">
        <div class="circle">
            <svg class="svg" width="100" height="100">
                <circle class="circle-bg" cx="50" cy="50" r="40"></circle>
                <circle class="circle-progress" cx="50" cy="50" r="40"></circle>
                <text x="50%" y="50%" class="circle-text">{{ num }}</text>
            </svg>
        </div>
        <span>{{title}}</span>
    </div>
</template>

<script>
export default {
    name: "GroupNumber",
    props: ['title', 'num'],
    
}
</script>

<style scoped>
.num_content {
    display: flex;
    align-items: center;
    flex-direction: column;
}

.circle {
    position: relative;
}

.svg {
    /*transform: rotate(-90deg);*/
}

.circle-bg {
    fill: none;
    stroke: #f0f0f0;
    stroke-width: 10;
}

.circle-progress {
    fill: none;
    stroke: #00a4ff;
    stroke-width: 10;
    /*stroke-dasharray: 251.2;*/
    /*stroke-dashoffset: calc(251.2 - (251.2 * 15) / 100);*/
    transition: stroke-dashoffset 0.5s;
}

.circle-text {
    fill: #00a4ff;
    font-size: 24px;
    text-anchor: middle;
    dominant-baseline: middle;
    font-weight: bold;
}
</style>