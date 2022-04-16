<template>
  <v-container>
    <v-layout column justify-space-around>
      <v-flex>
        {{profile.username}}
      </v-flex>
      <v-flex>
        <v-layout column justify-space-between>
          <v-flex>
            <span>Ваш уровень доступа:</span>
            <i>
              <span>{{profile.roles.toString()}}</span>
            </i>
          </v-flex>
          <v-flex>
            <span>Aккаунт:</span>
            <i>
              <span v-if="profile.active">Включён</span>
              <span v-else>Выключён</span>
            </i>
          </v-flex>
          <v-flex>
            <span>Количество подписок:</span>
            <i>
              <span>{{profile.subscriptions && profile.subscriptions.length}}</span>
            </i>
          </v-flex>
          <router-link v-if="isMyProfile" :to="`/subscriptions/${profile.id}`">
            <span>Количество подписчиков:</span>
            <i>
              <span>{{profile.subscribers && profile.subscribers.length}}</span>
            </i>
          </router-link>
          <v-flex v-if="!isMyProfile">
            <span>Количество подписчиков:</span>
            <i>
              <span>{{profile.subscribers && profile.subscribers.length}}</span>
            </i>
          </v-flex>
        </v-layout>
        <v-btn v-if="!isMyProfile" @click="changeSubscription">{{isImSubscribed ? 'Отписаться' : 'Подписаться'}}</v-btn>
      </v-flex>

      <v-flex>
        <v-content>Смена пароля</v-content>
      </v-flex>
      <v-flex>
        <v-form
            width="350px"
            :action="`/profile/${profile.id}`"
            method="post"
            justify="center"
            align="center"
            ref="form">
          <v-text-field
              v-model="value_op"
              :append-icon="show_op ? 'visibility' : 'visibility_off'"
              :type="show_op ? 'text' : 'password'"
              @click:append="show_op = !show_op"
              :rules="[rules_p.required, rules_p.min]"
              class="ma-1"
              name="oldPassword"
              label="Пароль"
              @keyup.enter="validate"
              required
          >
          </v-text-field>
          <v-text-field
              :append-icon="show_p ? 'visibility' : 'visibility_off'"
              :type="show_p ? 'text' : 'password'"
              @click:append="show_p = !show_p"
              :rules="[rules_p.required, rules_p.min]"

              v-model="value_p"
              class="ma-1"
              name="password"

              label="Новый пароль"
              @keyup.enter="validate"
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
          <v-btn @click="validate" depressed color="primary" class="mb-4">Сменить пароль</v-btn>
        </v-form>


      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import profileApi from 'api/profile'

export default {
  name: "Profile",
  data() {
    return {
      profile: {},

      isValid: false,
      value_p: '',
      value_op: '',
      show_op: false,
      show_p: false,
      rules_p: {
        required: value => !!value || 'Обязательное поле',
        min: v => v.length >= 8 || 'Минимум 8 символов',
      },
    }
  } ,
  computed: {
    isMyProfile() {
      return !this.$route.params.id || this.$route.params.id == this.$store.state.profile.id
    },
    isImSubscribed() {
      return this.profile.subscribers && this.profile.subscribers.find(subscription => {
        return subscription.subscriber === this.$store.state.profile.id
      })
    },


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
  watch: {
    '$route'() {
      this.updateProfile()
    }
  },
  methods: {
    async changeSubscription() {
      const data = await profileApi.changeSubscription(this.profile.id)
      this.profile = await data.json()
    },
    async updateProfile() {
      const id = this.$route.params.id || this.$store.state.profile.id

      const data = await profileApi.get(id)
      this.profile = await data.json()

      this.$forceUpdate()
    },



    validate () {
      this.$refs.form.validate()
      if (this.$refs.form.validate()) {
        this.$refs.form.$el.submit()
      }
    },
  },
  beforeMount() {
    this.updateProfile()
  }
}
</script>

<style scoped>
.v-text-field .v-input__append-inner, .v-text-field .v-input__prepend-inner {
  transform: scale(0.9);
}
</style>