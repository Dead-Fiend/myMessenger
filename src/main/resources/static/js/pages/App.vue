<template>
    <v-app>
        <v-app-bar app elevation="12">
            <v-toolbar-title>
                <a href="/" style="color: black">
                    InKontakte
                </a>
            </v-toolbar-title>
            <v-spacer></v-spacer>
            <span v-if="profile">
            <span>
                <a href="/profile" style="color: black; font-weight: 401">
                    {{profile.username}}
                </a>
            </span>
            <v-btn icon href="/profile">
                <v-icon>account_circle</v-icon>
            </v-btn>
            <v-btn icon href="/logout">
                <v-icon>logout</v-icon>
            </v-btn>
            </span>
            <v-btn icon href="/auth" v-if="!profile">
                <v-icon>login</v-icon>
            </v-btn>
        </v-app-bar>
        <v-main class="mt-4">
            <router-view></router-view>
        </v-main>
    </v-app>
    <!--Необходимо
<a href="/auth">Войти</a>
или
<a href="/reg">Зарегестрироваться</a>-->

</template>

<script>
    import { addHandler } from "util/ws";
    import { mapState, mapMutations } from 'vuex'

    export default {
        computed: mapState(['profile', 'messages', 'redact']),
        methods: mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
        created() {
            addHandler(data => {
                if (data.objectType === 'MESSAGE') {
                    let index = this.messages.findIndex(item => item.id === data.body.id)
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addMessageMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updateMessageMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removeMessageMutation(data.body)
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