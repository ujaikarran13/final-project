<template>
  <div id="message-bam">
    {{ $store.state.message }}!
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
#message-bam {
  font-size: 1.4rem;
  font-weight: bold;
  position: absolute;
  color: #000;
  width: 650px;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  z-index: 1;
  opacity: 1;
  padding: 250px 125px;
  background-image: url("../assets/explosion.png");
  background-position: center;
  background-repeat: no-repeat;
  background-color: #f1faee;
  border-radius: 50%;
}
</style>