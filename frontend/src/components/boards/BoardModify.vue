<script setup>
import BoardFormItem from "./item/BoardFormItem.vue";
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2>글 수정하기</h2>
      </div>
      <div class="col-lg-10 text-start">
        <BoardFormItem type="modify" />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
