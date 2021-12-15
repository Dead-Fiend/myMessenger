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
            <v-btn icon href="/login" v-if="!profile">
                <v-icon>login</v-icon>
            </v-btn>
        </v-app-bar>

        <v-main class="mt-4">
            <div v-if="!profile">
                <v-container>
                    Добро пожаловать
                </v-container>
                <v-container>
                    Необходимо
                    <a href="/login">Войти</a>
                    или
                    <a href="/registration">Зарегестрироваться</a>
                </v-container>

            </div>
            <div v-if="profile">
                <v-container>
                    <div>Привет, {{profile.username}}</div>
                </v-container>
                <v-container>
                    <messages-list :redact="redact"/>
                </v-container>
            </div>
        </v-main>
    </v-app>
</template>

<script>
    import MessagesList from 'components/messages/MessagesList.vue'
    import { addHandler } from "util/ws";
    import { mapState, mapMutations } from 'vuex'


    export default {
        components: {
            MessagesList
        },
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
        }
    }
</script>

<style>
a {
    text-decoration: none;
}
</style>