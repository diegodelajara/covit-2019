<template>
  <div class="card" :class="{'card-plain': plain}">
    <h6 class="card-category" v-if="$slots.category || category">{{category}}</h6>
    <div class="card-image" v-if="$slots.image">
      <slot name="image"></slot>
    </div>
    <div class="card-header" v-if="$slots.header || title">
      <slot name="header">
        <h1 v-if="title" class="card-title">{{title}}</h1>
        <h3 v-if="subTitle" class="card-category">{{subTitle}}</h3>
        <p v-if="description" class="card-description">{{description}}</p>
      </slot>
    </div>
    <slot name="raw-content"></slot>
    <div v-if="$slots.default" class="card-body" :class="cardBodyClasses">
      <slot></slot>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'card',
    props: {
      title: String,
      subTitle: String,
      category: String,
      description: String,
      noFooterLine: Boolean,
      plain: Boolean,
      cardBodyClasses: [String, Object, Array]
    }
  }
</script>
<style>
</style>
