<template>
  <v-card class="my-2">
    <v-card-text class="text--primary">
      <user-link :user="chat" size="48"></user-link>
<!--      <div class="pt-3">{{message.text}}</div>-->
    </v-card-text>
    <v-card-actions>
      <v-btn icon :href="`/mssgs/${chat.id}`" small>
        <v-icon>mail</v-icon>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import UserLink from "components/UserLink.vue";

export default {
  name: "ChatRow",
  components: {
    UserLink
  },
  props: ['chat'],
}
</script>

<style scoped>

</style>