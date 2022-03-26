<template>
  <v-card class="my-2">
    <v-card-text class="text--primary">
      <user-link :user="post.author" size="48"></user-link>
      <div class="pt-3">{{post.text}}</div>

    </v-card-text>
    <v-card-actions>
      <v-btn icon @click="edit" small>
        <v-icon>edit</v-icon>
      </v-btn>
      <v-btn icon @click="del" small>
        <v-icon>close</v-icon>
      </v-btn>
    </v-card-actions>
    <comment-list :comments="post.comments" :post-id="post.id" width="40%"></comment-list>
  </v-card>
</template>

<script>
import { mapActions } from 'vuex'
import CommentList from 'components/comment/CommentList.vue'
import UserLink from "components/UserLink.vue";

export default {
  name: "PostRow",
  components: {
    UserLink,
    CommentList
  },
  props: ['post', 'editPost', 'redact'],
  methods: {
    ...mapActions(['removePostAction']),
    edit() {
      this.editPost(this.post)
    },
    del() {
      this.removePostAction(this.post)
    },
  }
}
</script>

<style>

</style>