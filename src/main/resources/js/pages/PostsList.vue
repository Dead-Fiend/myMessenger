<template>
  <v-container column>
    <v-flex class="title mb-3">
      <div>Привет, {{profile.username}} </div>
    </v-flex>
    <v-layout align-space-around justify-start column>
      <post-form :postAttr="post" :redact="redact"/>
      <post-row v-for="post in sortedPosts"
                   :key="post.id"
                   :post="post"
                   :editPost="editPost"
                   :redact="redact"/>
      <lazy-loader></lazy-loader>
    </v-layout>
  </v-container>

</template>

<script>
import PostRow from 'components/posts/PostRow.vue'
import PostForm from 'components/posts/PostForm.vue'
import {mapGetters, mapState} from 'vuex'
import LazyLoader from "components/LazyLoader.vue";

export default {
  components: {
    LazyLoader,
    PostRow, PostForm
  },
  data() {
    return {
      post: null,
      isRedact: null,
    }
  },
  computed: {
    ...mapState(['profile', 'redact']),
    ...mapGetters(['sortedPosts'])
  },
  methods: {
    editPost(post) {
      this.post = post
    },
  }
}

</script>

<style>

</style>