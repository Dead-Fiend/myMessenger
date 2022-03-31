<template>
  <v-container column>
      <div style="display:flex;justify-content:center;align-items:center;">
        <div>
          <post-u-p-d
                      :post="defaultPost"
                      :isDefault="true"/>
          <!--      <post-form :postAttr="post" :redact="redact"/>-->
          <post-form-u-p-d :postAttr="post" :redact="redact"/>
          <!--      <post-row v-for="post in sortedPosts"
                             :key="post.id"
                             :post="post"
                             :editPost="editPost"
                             :redact="redact"/>-->
          <post-u-p-d v-for="post in sortedPosts"
                      :key="post.id"
                      :post="post"
                      :editPost="editPost"
                      :redact="redact"
                      :isDefault="false"/>
          <lazy-loader></lazy-loader>
        </div>
      </div>
  </v-container>

</template>

<script>
import PostRow from 'components/posts/PostRow.vue'
import PostUPD from 'components/posts/PostUPD.vue'
import PostForm from 'components/posts/PostForm.vue'
import PostFormUPD from 'components/posts/PostFormUPD.vue'
import {mapGetters, mapState} from 'vuex'
import LazyLoader from "components/LazyLoader.vue";

export default {
  components: {
    LazyLoader,
    PostRow, PostForm, PostUPD, PostFormUPD
  },
  data() {
    return {
      post: null,
      isRedact: null,
      defaultPost: null
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