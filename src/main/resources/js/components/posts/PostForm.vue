<template>
    <div>
        <v-layout row v-if="isRedact">
            <v-text-field label="Редактировать пост" placeholder="Напишите что-нибудь" v-model="text" @keyup.enter="save" />
            <v-btn class="ma-2" @click="save">Отправить</v-btn>
        </v-layout>
        <v-layout row v-if="!isRedact">
            <v-text-field label="Новый пост" placeholder="Напишите что-нибудь" v-model="text" @keyup.enter="save" />
            <v-btn class="ma-2" @click="save" style="color: darkslategray" icon><v-icon class="material-icons-round">send</v-icon></v-btn>
        </v-layout>
    </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  props: ['postAttr', 'redact'],
  data() {
    return {
      text: '',
      id: null,
      isRedact: null
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
    }


  }

}
</script>

<style>

</style>