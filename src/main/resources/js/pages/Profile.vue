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
              <span>{{profile.roles}}</span>
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
    </v-layout>
  </v-container>
</template>

<script>
import profileApi from 'api/profile'

export default {
  name: "Profile",
  data() {
    return {
      profile: {}
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

  },
  beforeMount() {
    this.updateProfile()
  }
}
</script>

<style scoped>

</style>