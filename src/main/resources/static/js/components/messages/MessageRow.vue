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
    <v-card class="my-2" v-if="">
        <v-card-text class="text--primary">
            <user-link :user="message.author" size="48"></user-link>
            <div class="pt-3">{{message.text}}</div>

        </v-card-text>
        <v-card-actions>
            <v-btn icon @click="edit" small>
                <v-icon>edit</v-icon>
            </v-btn>
            <v-btn icon @click="del" small>
                <v-icon>close</v-icon>
            </v-btn>
        </v-card-actions>
        <comment-list :comments="message.comments" :message-id="message.id" width="40%"></comment-list>
    </v-card>
</template>

<script>
    import { mapActions } from 'vuex'
    import CommentList from 'components/comment/CommentList.vue'
    import UserLink from "components/UserLink.vue";

    export default {
        components: {
          UserLink,
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