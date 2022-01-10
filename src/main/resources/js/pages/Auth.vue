<template>
  <v-container fill-height>
    <v-layout column justify-space-around>
      <v-flex></v-flex>
      <v-flex>
        <v-layout justify-space-around>
          <v-flex></v-flex>
          <v-flex fill-height>
            <v-layout column justify-space-around>
              <v-flex>
                <v-card class="px-2" style="margin: auto" width="500">
                  <v-card-title>Авторизация</v-card-title>
                  <v-form
                      width="350px"
                      action="/auth"
                      method="post"
                      justify="center"
                      align="center"
                      ref="form"
                  >
                    <v-text-field
                        v-model="value_l"
                        class="ma-1"
                        name="username"
                        :type="'email'"
                        :rules="[rules_l.required, rules_l.min]"
                        label="Логин"
                        placeholder="Введите логин"
                        required autofocus
                    >
                    </v-text-field>
                    <v-text-field
                        v-model="value_p"
                        :append-icon="show ? 'visibility' : 'visibility_off'"
                        :type="show ? 'text' : 'password'"
                        @click:append="show = !show"
                        :rules="[rules_p.required, rules_p.min]"
                        class="ma-1"
                        name="password"
                        label="Пароль"
                        @keyup.enter="validate"
                        required
                    >
                    </v-text-field>
                    <v-btn @click="validate" depressed color="primary" class="mb-4">Войти</v-btn>
                  </v-form>
                </v-card>
              </v-flex>
              <v-flex fill-height style="margin: auto" class="mt-3">
                <span class="pa-1">Нет аккаунта? <v-btn href="/reg" small depressed>Зарегистрируйся</v-btn></span>
              </v-flex>
            </v-layout>
          </v-flex>
          <v-flex></v-flex>
        </v-layout>
      </v-flex>
      <v-flex></v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name: 'Auth',
  data() {
    return {
      value_p: '',
      value_l: '',
      show: false,

      rules_p: {
        required: value => !!value || 'Обязательное поле',
        min: v => v.length >= 8 || 'Минимум 8 символов',
      },
      rules_l: {
        required: value => !!value || 'Обязательное поле',
        min: v => v.length >= 3 || 'Минимум 3 символа',
      },
    }
  },
  methods: {
    validate() {
      this.$refs.form.validate()
      if (this.$refs.form.validate()) {
        this.$refs.form.$el.submit()
      }
    },
  }
}

</script>

<style>
.v-text-field .v-input__append-inner, .v-text-field .v-input__prepend-inner {
  transform: scale(0.9);
}
</style>