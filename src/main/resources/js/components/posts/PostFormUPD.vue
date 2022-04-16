<template>
  <div class="my-2">
    <v-card class="postwithoutpic232-postwithoutpic232">
            <v-form ref="form" @submit.prevent="validate" style="width: 378px">
              <v-layout row class="px-3" style="margin-bottom: 2px">
                <v-textarea
                    auto-grow
                    v-if="!isRedact"
                    label="Новый пост"
                    placeholder="Напишите что-нибудь"
                    v-model="text"
                    :rules="rules"
                    :type="'text'"
                    required
                >
                </v-textarea>
                <v-textarea
                    auto-grow
                    v-if="isRedact"
                    label="Редактировать пост"
                    placeholder="Напишите что-нибудь"
                    v-model="text"
                    :rules="rules"
                    :type="'text'"
                    required
                >
                </v-textarea>
                <v-btn class="ma-2" type="submit" style="color: darkslategray" icon><v-icon>send</v-icon></v-btn>
              </v-layout>
            </v-form>
    </v-card>
  </div>
</template>

<script>
import {mapActions} from "vuex";

export default {
  name: "PostFormUPD",
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

<style scoped>
.postwithoutpic232-postwithoutpic232 {
  width: 388px;
  display: flex;
  padding: 20px 19px;
  overflow: hidden;
  position: relative;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
  border-radius: 10px;
  flex-direction: column;
  /*background-color: rgba(22, 24, 27, 1);*/
}
.postwithoutpic232-header234 {
  margin: 0 0 20px 0;
  display: flex;
  overflow: hidden;
  position: relative;
  box-sizing: border-box;
  align-items: center;
  flex-shrink: 1;
  border-color: transparent;
}
.postwithoutpic232-header235 {
  width: 345px;
  height: 50px;
  display: flex;
  position: relative;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.postwithoutpic232-group236 {
  top: 0px;
  left: 0px;
  width: 157px;
  height: 50px;
  display: flex;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.postwithoutpic232-avatar237 {
  top: 0px;
  left: 0px;
  width: 50px;
  height: 50px;
  display: flex;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
  /*background-color: rgba(0, 0, 0, 1);*/
}
.postwithoutpic232-text {
  top: 18.000011444091797px;
  left: 13.999998092651367px;
  /*color: rgba(255, 255, 255, 1);*/
  height: auto;
  position: absolute;
  font-size: 9px;
  align-self: auto;
  font-style: normal;
  text-align: left;
  font-family: Abril Fatface;
  font-weight: 400px;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
}
.postwithoutpic232-text1 {
  /*color: rgba(255, 255, 255, 1);*/
  height: auto;
  align-self: auto;
  font-style: normal;
  text-align: left;
  font-weight: 400px;
  font-stretch: normal;
  text-decoration: none;
}
.postwithoutpic232-group8202433 {
  top: 6px;
  left: 62px;
  width: 95px;
  height: 38px;
  display: flex;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.postwithoutpic232-data2315 {
  top: 25px;
  left: 0px;
  width: 67px;
  height: 13px;
  display: flex;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.postwithoutpic232-text2 {
  top: 0px;
  left: 0px;
  color: var(--dl-color-default-textmutedececb);
  width: 67px;
  height: auto;
  position: absolute;
  font-size: 12px;
  align-self: auto;
  font-style: normal;
  text-align: left;
  font-family: Roboto;
  font-weight: 400px;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
}
.postwithoutpic232-text3 {
  /*color: rgba(156, 160, 172, 1);*/
  width: 67px;
  height: auto;
  align-self: auto;
  font-style: normal;
  text-align: left;
  font-weight: 400px;
  font-stretch: normal;
  text-decoration: none;
}
.postwithoutpic232-nickdata2310 {
  top: 0px;
  left: 0px;
  display: flex;
  overflow: hidden;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.postwithoutpic232-nick2313 {
  height: 21px;
  margin: 0 4px 0 0;
  display: flex;
  position: relative;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;


  /*color: rgba(255, 255, 255, 1);*/
  align-self: auto;
  font-style: normal;
  text-align: left;
  font-weight: 700px;
  font-stretch: normal;
  text-decoration: none;
  font-size: 18px;
  font-family: Roboto;
  line-height: normal;
}
.postwithoutpic232-text4 {
  top: 0px;
  left: 0px;
  /*color: rgba(255, 255, 255, 1);*/
  position: absolute;
  font-size: 18px;
  align-self: auto;
  font-style: normal;
  text-align: left;
  font-family: Roboto;
  font-weight: 700px;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
}
.postwithoutpic232-text5 {
  /*color: rgba(255, 255, 255, 1);*/
  align-self: auto;
  font-style: normal;
  text-align: left;
  font-weight: 700px;
  font-stretch: normal;
  text-decoration: none;
}
.postwithoutpic232-verify2317 {
  width: 16px;
  height: 16px;
  display: flex;
  position: relative;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.postwithoutpic232-svg {
  width: 16;
  height: 16;
  position: absolute;
}
.postwithoutpic232-group2319 {
  top: 5.00006103515625px;
  left: 5.14013671875px;
  width: 6px;
  height: 5.999958038330078px;
  display: flex;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.postwithoutpic232-group2320 {
  top: 0px;
  left: 0px;
  width: 6px;
  height: 5.999958038330078px;
  display: flex;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.postwithoutpic232-image {
  top: 0px;
  left: 0px;
  width: 6px;
  height: 6px;
  position: absolute;
  object-fit: cover;
  border-color: transparent;
}
.postwithoutpic232-svg1 {
  top: 6px;
  left: 321px;
  width: 24;
  height: 5;
  position: absolute;
}
.postwithoutpic232-text6 {
  /*color: rgba(255, 255, 255, 1);*/
  width: 350px;
  height: auto;
  font-size: 14px;
  align-self: auto;
  font-style: normal;
  text-align: left;
  font-family: Roboto;
  font-weight: 400px;
  line-height: 22px;
  font-stretch: normal;
  text-decoration: none;
}
.postwithoutpic232-text7 {
  /*color: rgba(255, 255, 255, 1);*/
  width: 350px;
  height: auto;
  align-self: auto;
  font-style: normal;
  text-align: left;
  font-weight: 400px;
  font-stretch: normal;
  text-decoration: none;
}
</style>