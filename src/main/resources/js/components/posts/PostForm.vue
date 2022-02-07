<template>
<!--  <v-layout row>
    <v-text-field v-if="!isRedact" label="Новый пост" placeholder="Напишите что-нибудь" v-model="text" @keyup.enter="save" />
    <v-text-field v-if="isRedact" label="Редактировать пост" placeholder="Напишите что-нибудь" v-model="text" @keyup.enter="save" />
    <v-btn class="ma-2" @click="save" style="color: darkslategray" icon><v-icon class="material-icons-round">send</v-icon></v-btn>
  </v-layout>-->

  <v-form ref="form" @submit.prevent="validate">
    <v-layout row class="px-3" style="margin-bottom: 2px">
      <v-text-field
          v-if="!isRedact"
          label="Новый пост"
          placeholder="Напишите что-нибудь"
          v-model="text"
          :rules="rules"
          :type="'text'"
          required
      >
      </v-text-field>
      <v-text-field
          v-if="isRedact"
          label="Редактировать пост"
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
  props: ['postAttr', 'redact'],
  data() {
    return {
      text: '',
      id: null,
      isRedact: null,
      rules: [
        value => !!value || 'Минимум 1 символ',
        value => (value || '').length <= 1500 || 'Максимум 1500 символов',
      ]
    }

  },
  watch: {
    postAttr(newVal) {
      this.text = newVal.text
      this.id = newVal.id
      this.isRedact = true
    },

  },
  methods: {
    ...mapActions(['addPostAction', 'updatePostAction']),
    save() {
      const post = {
        id: this.id,
        text: this.text
      }

      if (this.id) {
        this.updatePostAction(post)
      } else {
        this.addPostAction(post)


      }
      //
      this.text = ''
      this.id = null
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

<style>

</style>