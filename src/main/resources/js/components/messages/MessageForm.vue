<template>
<!--  <v-layout row>
    <v-text-field v-if="isRedact" label="Редактировать сообщение" placeholder="Напишите что-нибудь" v-model="text" @keyup.enter="save" />
    <v-text-field v-if="!isRedact" label="Новое сообщение" placeholder="Напишите что-нибудь" v-model="text" @keyup.enter="save" />
    <v-btn class="ma-2" @click="save" style="color: darkslategray" icon><v-icon class="material-icons-round">send</v-icon></v-btn>
  </v-layout>-->
  <v-form ref="form" @submit.prevent="validate">
<!--    <v-layout row class="px-3">-->
    <v-layout row>
      <v-text-field
          v-if="!isRedact"
          label="Новое сообщение"
          placeholder="Напишите что-нибудь"
          v-model="text"
          :rules="rules"
          :type="'text'"
          required
      >
      </v-text-field>
      <v-text-field
          v-if="isRedact"
          label="Редактировать сообщение"
          placeholder="Напишите что-нибудь"
          v-model="text"
          :rules="rules"
          :type="'text'"
          required
      >
      </v-text-field>

      <v-btn class="ma-2" type="submit" style="color: darkslategray" icon><v-icon>send</v-icon></v-btn>
    </v-layout>
  </v-form>
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
      isRedact: null,
      rules: [
        value => !!value || 'Минимум 1 символ',
        value => (value || '').length <= 1000 || 'Максимум 1000 символов',
      ]
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
    },
    validate (event) {
      event.preventDefault()
      this.$refs.form.validate()
      if (this.$refs.form.validate()) {
        this.save()
        this.$refs.form.resetValidation()
      }
    },
  }
}
</script>

<style scoped>

</style>