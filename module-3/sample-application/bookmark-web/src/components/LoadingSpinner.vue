<template>
    <div id="spinner">
        <img v-bind:src="image"/>
    </div>
</template>
    
<script>
    import spinner from "@/assets/spinner.gif"
    
    export default {
        data: function () {
            return {
                image: spinner
            }
        }
    }
</script>
    
<style scoped>
#spinner {
    display: flex;
    align-items: center;
    justify-content: center;
}
</style>