<template>
  <v-container>
<!--    <iframe name="myIFR" style="display: none"></iframe>
    <v-form ref="form" target="myIFR">-->
    <v-form ref="form" @submit.prevent="validate">
      <v-layout row class="px-3">
        <v-text-field
            label="Новый комментарий"
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
  </v-container>
</template>

<script>
import {mapActions} from "vuex";

export default {
  name: 'CommentForm',
  props: ['postId'],
  data() {
    return {
      text: '',
      rules: [
        value => !!value || 'Обязательное поле',
        value => (value || '').length <= 250 || 'Максимум 250 символов',
      ]
    }
  },
  watch: {
    postId(newVal) {
      this.postId = newVal
    }
  },
  methods: {
    ...mapActions(['addCommentAction']),
    save() {
      this.addCommentAction({
        text: this.text,
        post: {
          id: this.postId
        },
      })
      this.text = ''
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