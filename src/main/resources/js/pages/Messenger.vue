<template>
  <v-container column class="mx-2">
    <v-flex class="title mb-3">
      <div>Привет, {{profile.username}} </div><div>{{chat}}</div>
    </v-flex>
    <v-flex v-if="(this.$route.params.id) === undefined">
      <chat-row v-for="chat in chats"
                :key="chat.id"
                :chat="chat"
      ></chat-row>
    </v-flex>
    <v-flex v-if="(this.$route.params.id) !== undefined">
      <v-layout align-space-around justify-start column>
        <message-form :messageAttr="message" :redact="redact"/>
        <message-row v-for="message in (sortedMessages.filter(n => n.interlocutor.id == this.$route.params.id).concat(sortedMessages.filter(n => n.author.id == this.$route.params.id)))"
                     :key="message.id"
                     :message="message"
                     :editMessage="editMessage"
                     :redact="redact"/>
      </v-layout>
    </v-flex>
  </v-container>
</template>

<script>
import MessageForm from "components/messages/MessageForm.vue";
import MessageRow from "components/messages/MessageRow.vue";
import ChatRow from "components/messages/ChatRow.vue";
import {mapGetters, mapState} from "vuex";

export default {
  name: "Messenger",
  components: {
    MessageRow,
    ChatRow,
    MessageForm,
  },
  data() {
    return {
      message: null,
        chat: null,
      isRedact: null,
    }
  },
  computed: {
    ...mapState(['profile', 'redact']),
    ...mapGetters(['sortedMessages', 'chats']),
  },
  methods: {
    editMessage(message) {
      this.message = message
    },
  },
}
</script>

<style scoped>

</style>