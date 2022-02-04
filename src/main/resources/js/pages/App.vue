<template>
  <v-app :dark="setTheme">
    <v-app-bar app elevation="12">
      <v-toolbar-title>
        <a v-if="profile" href="/wall">
          InKontakte
        </a>
        <a v-if="!profile" href="/">
          InKontakte
        </a>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <span v-if="profile">
            <span>
                <a href="/user" style="font-weight: 401">
                    {{profile.username}}
                </a>
            </span>
            <v-btn icon href="/user">
                <v-icon>account_circle</v-icon>
            </v-btn>
            <v-btn icon href="/mssgs">
                <v-icon>chat</v-icon>
            </v-btn>
            <v-btn icon href="/logout">
                <v-icon>logout</v-icon>
            </v-btn>
            </span>
      <v-btn icon href="/auth" v-if="!profile">
        <v-icon>login</v-icon>
      </v-btn>
      <v-btn @click="changeTheme" icon>
        <v-icon v-if="!$vuetify.theme.dark">light_mode</v-icon>
        <v-icon v-if="$vuetify.theme.dark">nightlight_round</v-icon>
      </v-btn>
    </v-app-bar>

    <v-main class="mt-4">
      <router-view></router-view>
    </v-main>

    <v-footer flat height="50">
      <v-layout style="margin: auto" column>
        <v-flex></v-flex>
        <v-flex>

          <v-layout justify-space-around row>
            <v-flex></v-flex>
            <v-flex>Правила</v-flex>
            <v-flex>О нас</v-flex>
            <v-flex>Поддержать разработчика</v-flex>
          </v-layout>
        </v-flex>

        <v-flex></v-flex>
      </v-layout>
    </v-footer>
  </v-app>
</template>

<script>
    import { addHandler } from "util/ws";
    import { mapState, mapMutations } from 'vuex'

    export default {
      computed: {
        setTheme() {
          if ((this.drkMode.isDrk === true) || (window.localStorage.drk === "true")) {
            return (this.$vuetify.theme.dark = true);
          } else {
            return (this.$vuetify.theme.dark = false);
          }
        },
        ...mapState(['profile', 'posts', 'redact', 'drkMode', ]),
      },
      methods: {
        changeTheme() {
          switch (window.localStorage.drk) {
            case "true":
              window.localStorage.drk = "false"
              this.drkMode.isDrk = false
              break;
            case "false":
              window.localStorage.drk = "true"
              this.drkMode.isDrk = true
              break;
            default:
              console.log("Error in local storage!")
              console.log(window.localStorage.drk)
              console.log(this.drkMode.isDrk)
              break;
          }
        },
        ...mapMutations([
                                 'addPostMutation',
                                 'updatePostMutation',
                                 'removePostMutation',
                                 'addPostMutation'
                               ])
      }
          ,
      created() {
        addHandler(data => {
          if (data.objectType === 'POST') {
            let index = this.posts.findIndex(item => item.id === data.body.id)
            switch (data.eventType) {
              case 'CREATE':
                this.addPostMutation(data.body)
                break;
              case 'UPDATE':
                this.updatePostMutation(data.body)
                break;
              case 'REMOVE':
                this.removePostMutation(data.body)
                break;
              default:
                console.error(`Looks like the event type is unknown | "${data.eventType}"`)

            }
          } else if (data.objectType === 'COMMENT') {
            let index = this.posts.findIndex(item => item.id === data.body.id)
            switch (data.eventType) {
              case 'CREATE':
                this.addCommentMutation(data.body)
                break
              default:
                console.error(`Looks like the event type is unknown | "${data.eventType}"`)

            }
          } else {
            console.error(`Looks like the object type is unknown | "${data.objectType}"`)
          }

        })
      },
    }
</script>

<style>
a {
    text-decoration: none;
}
</style>