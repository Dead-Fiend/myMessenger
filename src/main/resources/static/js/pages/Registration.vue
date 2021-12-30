<template>
  <v-container fill-height>
    <v-layout column justify-space-around>
      <v-flex></v-flex>
      <v-flex>
        <v-layout raw justify-space-around>
          <v-flex></v-flex>
          <v-flex fill-height>
            <v-layout column justify-space-around>
              <v-flex>
                <v-card class="px-2" style="margin: auto" width="500">
                  <v-card-title>Регистрация</v-card-title>
                  <v-form width="350px" action="/registration" method="post" justify="center" align="center">
                    <v-text-field
                        v-model="value_l"
                        class="ma-1"
                        name="username"
                        :type="'text'"
                        label="Логин"
                        placeholder="Введите логин"
                        required autofocus loading
                    >
                      <template v-slot:progress>
                        <v-progress-linear
                            :value="progress_l"
                            :color="color_l"
                            absolute
                            height="7"
                        ></v-progress-linear>
                      </template>
                    </v-text-field>
                    <v-text-field
                        :append-icon="show ? 'visibility' : 'visibility_off'"
                        :type="show ? 'text' : 'password'"
                        @click:append="show = !show"
                        :rules="[rules.required, rules.min]"

                        v-model="value_p"
                        class="ma-1"
                        name="password"

                        label="Пароль"
                        @keyup.enter="submit"
                        required loading
                    >
                      <template v-slot:progress>
                        <v-progress-linear
                            :value="progress_p"
                            :color="color_p"
                            absolute
                            height="7"
                        ></v-progress-linear>
                      </template>
                    </v-text-field>
                    <v-btn type="submit" depressed color="primary" class="mb-4">Зарегистрироваться</v-btn>
                  </v-form>
                </v-card>
              </v-flex>
              <v-flex fill-height style="margin: auto" class="mt-3">
                <span class="pa-1">Есть аккаунт? <v-btn href="/auth" small depressed>Войти</v-btn></span>
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
  name: 'Registration',
  data() {
    return {
      value_l: '',
      value_p: '',
      show: false,
      rules: {
        required: value => !!value || 'Обязательное поле',
        min: v => v.length >= 8 || 'Минимум 8 символов',
      }
    }
  },
  computed: {
    progress_l () {
      return Math.min(100, this.value_l.length * 33.3)
    },
    color_l () {
      return ['error', 'error', 'success'][Math.floor(this.progress_l / 40)]
    },
    progress_p () {
      return Math.min(100, this.value_p.length * 5)
    },
    color_p () {
      return ['error', 'warning', 'success'][Math.floor(this.progress_p / 40)]
    },
  },
}
</script>

<style>
.v-text-field .v-input__append-inner, .v-text-field .v-input__prepend-inner {
  transform: scale(0.9);
}
</style>