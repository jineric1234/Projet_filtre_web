<template>
    <div class="slider">
        <button @click="prev" class="btn btn-left">
            <img src="../assets/chevron_left.svg" class="icon">
        </button>
        <div class="container-slides" v-bind:style="{transform: 'translateX(${index}px)', transition}">
            <img id="0" src="" class="img-slider">
            <img id="1" src="" class="img-slider">
        </div>
        <button @click="next" class="btn btn-right">
            <img src="../assets/chevron_right.svg" class="icon">
        </button>
    </div>
</template>

<script>

export default {
    name: 'Slider',
    data: function(){
        return {
            index: 0,
            transition: "transform 0,2s ease"
        }
    },
    methods: {
        next(){
            console.log(this.index);
            if(this.index === -1500){
                this.index = 0;
            }else{
                this.index -= 500;
            }
        }
    }
}
</script>

<style scoped>
    .slider {
        width: 500px;
        height: 300px;
        margin: 100px auto 0;
        /*background: #000;*/
        overflow: hidden;
        position: relative;

    }
    .container-slides{
        display: 100%;
        height: auto;
    }
    .btn{
        outline: none;
        border: none;
        width: 50px;
        height: 50px;
        border-radius: 50%;
        display: block;
        position: absolute;
        z-index: 1000;
        cursor: pointer;
        font-size: 20px;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .icon{
        width: 15px;
    }
    .btn-left{
        top: 50%;
        left: 5px;
        transform: translateY(-50);
    }
    .btn-right{
        top: 50%;
        right: 5px;
        transform: translateY(-50);
    }
</style>
