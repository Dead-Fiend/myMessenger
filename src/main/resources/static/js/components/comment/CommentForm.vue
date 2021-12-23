<template>
    <v-container>
        <v-layout row class="px-3">
            <v-text-field label="Новый комментарий" placeholder="Напишите что-нибудь" v-model="text" @keyup.enter="save" />
            <v-btn @click="save">Отправить</v-btn>
        </v-layout>
    </v-container>
</template>

<script>
    import {mapActions} from "vuex";

    export default {
        name: 'CommentForm',
        props: ['messageId'],
        data() {
            return {
                text: '',
            }
        },
        watch: {
            messageId(newVal) {
                this.messageId = newVal
            }
        },
        methods: {
            ...mapActions(['addCommentAction']),
            save() {
                this.addCommentAction({
                    text: this.text,
                    message: {
                        id: this.messageId
                    },
                })
                this.text = ''
            }
        }
    }
</script>

<style>

</style>