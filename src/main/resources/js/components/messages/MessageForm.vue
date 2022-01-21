<template>
  <div>
    <v-layout row v-if="isRedact">
      <v-text-field label="Редактировать сообщение" placeholder="Напишите что-нибудь" v-model="text" @keyup.enter="save" />
      <v-btn class="ma-2" @click="save">Отправить</v-btn>
    </v-layout>
    <v-layout row v-if="!isRedact">
      <v-text-field label="Новое сообщение" placeholder="Напишите что-нибудь" v-model="text" @keyup.enter="save" />
      <v-btn class="ma-2" @click="save" style="color: darkslategray" icon><v-icon class="material-icons-round">send</v-icon></v-btn>
    </v-layout>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "MessageForm",
  props: ['messageAttr', 'redact'],
  data() {
    return {
      text: '',
      id: null,
      interlocutor: null,
      isRedact: null
    }

  },
  watch: {
    messageAttr(newVal) {
      this.text = newVal.text
      this.id = newVal.id
      this.interlocutor = newVal.interlocutor
      this.isRedact = true
    },

  },
  methods: {
    ...mapActions(['addMessageAction', 'updateMessageAction']),
    save() {
      const message = {
        id: this.id,
        text: this.text,
        interlocutor: {id: this.$route.params.id}
      }

      if (this.id) {
        this.updateMessageAction(message)
      } else {
        this.addMessageAction(message)


      }
      //
      this.text = ''
      this.id = null
      this.interlocutor = null
      this.isRedact = null
    }
  }
}
</script>

<style scoped>

</style>