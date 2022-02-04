<template>
  <v-content fill-height>
      <v-layout row>
        <v-flex>
          <v-card max-width="700">
            <v-card-text style="font-size: 17px">
              InKontakte - это "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.""Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.""Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            </v-card-text>
          </v-card>
        </v-flex>
        <v-flex>
              <v-layout row justify-space-around>
                <v-flex></v-flex>
                <v-flex fill-height>
                  <v-layout column justify-space-around>
                    <v-flex>
                      <v-card class="px-2" style="margin: auto" max-width="500">
                        <v-card-title>Регистрация</v-card-title>
                        <v-form
                            max-width="350px"
                            action="/registration"
                            method="post"
                            justify="center"
                            align="center"
                            ref="form"
                        >
                          <v-text-field
                              v-model="value_l"
                              class="ma-1"
                              name="username"
                              :type="'text'"
                              :rules="[rules_l.required, rules_l.min]"
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
                              class="ma-1"
                              name="email"
                              label="Email"
                              placeholder="Введите email"
                              v-model="value_e"
                              :rules="rules_e"
                              required
                          >

                          </v-text-field>
                          <v-text-field
                              :append-icon="show ? 'visibility' : 'visibility_off'"
                              :type="show ? 'text' : 'password'"
                              @click:append="show = !show"
                              :rules="[rules_p.required, rules_p.min]"

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
                          <v-btn @click="validate" depressed color="primary" class="mb-4">Зарегистрироваться</v-btn>
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
      </v-layout>
  </v-content>
</template>

<script>
export default {
  name: "Main",
  data() {
    return {
      isValid: false,
      value_l: '',
      value_p: '',
      value_e: '',
      show: false,
      rules_p: {
        required: value => !!value || 'Обязательное поле',
        min: v => v.length >= 8 || 'Минимум 8 символов',
      },
      rules_l: {
        required: value => !!value || 'Обязательное поле',
        min: v => v.length >= 3 || 'Минимум 3 символа',
      },
      rules_e: [
        value => !!value || 'Обязательное поле',
        value => (value || '').length <= 50 || 'Максимум 50 символов',
        value => {
          const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
          return pattern.test(value) || 'Некорректный e-mail.'
        },
      ]
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
  methods: {
    validate () {
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