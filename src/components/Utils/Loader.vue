<template>
  <vue-button-spinner
    class="btn btn-round btn-block btn-primary"
    :is-loading="isLoading" 
    :disabled="isLoading"
    :status="status"
    @click.native="onSubmit">
    <span>{{ text }}</span>
</vue-button-spinner>
</template>
 
<script>
import VueButtonSpinner from "vue-button-spinner";
import { truncate } from 'fs';

export default {
  props: {
    text: {
      required: true,
      type: String
    },
    isLoading: {
      required: true,
      type: Boolean
    },
    status: {
      required: true
    }
  },
  components: {
    VueButtonSpinner
  },
  methods: {
    onSubmit() {
      this.$emit('on-submit')
      // console.log("on-submit")
    }
  }
}
</script>

<style scoped>
.btn-round {
  border-radius: 45px !important;
}
.vue-btn{
    background-color: #f96332 !important;
    color: #FFFFFF !important;
    border: 0 !important;
    font-size: .90rem !important;
}
</style>
