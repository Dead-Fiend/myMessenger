<template>
  <v-card class="my-2">
    <v-card-text class="text--primary">
      <user-link :user="message.author" size="48"></user-link>
      <div class="pt-3">{{message.text}}</div>
    </v-card-text>
    <v-card-actions>
      <v-btn icon @click="edit" small>
        <v-icon>edit</v-icon>
      </v-btn>
      <v-btn icon @click="del" small>
        <v-icon>close</v-icon>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import UserLink from "components/UserLink.vue";
import {mapActions} from "vuex";

export default {
  name: "MessageRow",
  components: {
    UserLink
  },
  props: ['message', 'editMessage', 'redact'],
  methods: {
    ...mapActions(['removeMessageAction']),
    edit() {
      this.editMessage(this.message)
    },
    del() {
      this.removeMessageAction(this.message)
    },
  },
}
</script>

<style scoped>

</style>