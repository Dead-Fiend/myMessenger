<!--
<template>
    <v-card class="my-2" max-width="45%">
        <v-card-text class="text--primary">
            <v-layout align-space-around justify-start row>
            <span>
                <i>({{ message.id }})</i> {{ message.text }}
            </span>
                <v-spacer></v-spacer>
                <v-btn icon @click="edit" small>
                    <v-icon>edit</v-icon>
                </v-btn>
                <v-btn icon @click="del" small>
                    <v-icon>close</v-icon>
                </v-btn>
            </v-layout>
        </v-card-text>

    </v-card>
</template>
-->

<template>
    <v-card class="my-2">
        <v-card-text class="text--primary">
            <div>
                <v-avatar color="indigo" size="36px">
                    <v-icon>account_circle</v-icon>
                </v-avatar>
<!--                <v-avatar color="indigo" v-if="!(message.author && message.author.avatar)" size="36px">
                    <v-icon>account_circle</v-icon>
                </v-avatar>
                <v-avatar v-if="message.author && message.author.avatar" size="36px">
                    <img src="" alt="">
                </v-avatar>-->
                {{message.author.username}}
            </div>
            <div>
                {{message.text}}
            </div>

        </v-card-text>
        <v-card-actions>
            <v-btn icon @click="edit" small>
                <v-icon>edit</v-icon>
            </v-btn>
            <v-btn icon @click="del" small>
                <v-icon>close</v-icon>
            </v-btn>
        </v-card-actions>
        <comment-list :comments="message.comments" :message-id="message.id"></comment-list>
    </v-card>
</template>

<script>
    import { mapActions } from 'vuex'
    import CommentList from '../comment/CommentList.vue'

    export default {
        components: {
            CommentList
        },
        props: ['message', 'editMessage', 'redact'],
        methods: {
            ...mapActions(['removeMessageAction']),
            edit() {
                this.editMessage(this.message)
            },
            del() {
                this.removeMessageAction(this.message)
            },
        }
    }
</script>

<style>

</style>