<template>
  <router-link :to="`/user/${user.id}`" class="px-3">
    <v-avatar :size="`${size || 36}px`" >
      <v-icon>account_circle</v-icon>
    </v-avatar>
    <!--                <v-avatar color="indigo" v-if="!(user && user.avatar)" :size="`${size || 36}px`">
                        <v-icon>account_circle</v-icon>
                    </v-avatar>
                    <v-avatar v-if="user && user.avatar" :size="`${size || 36}px`">
                        <img src="" alt="">
                    </v-avatar>-->
    <span>{{userName}}</span>
  </router-link>
</template>

<script>
export default {
  name: "UserLink",
  props: ['user', 'size'],
  computed: {
    userName() {
      return this.user ? this.user.username : 'unknown'
    }
  },
}
</script>

<style scoped>

</style>