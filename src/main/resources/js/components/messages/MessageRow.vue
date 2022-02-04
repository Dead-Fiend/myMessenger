<template>
<v-layout row>
  <v-flex v-if="!(profile.id == message.author.id)">
    <v-card class="my-2" width="200">
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
  </v-flex>

  <v-flex v-if="profile.id == message.author.id">
    <v-card class="ml-auto" width="200">
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
  </v-flex>
</v-layout>
</template>

<script>
import UserLink from "components/UserLink.vue";
import {mapActions, mapGetters, mapState} from "vuex";

export default {
  name: "MessageRow",
  components: {
    UserLink
  },
  computed: {
    ...mapState(['profile']),
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