<template>
        <v-layout row>
            <v-text-field label="Новый комментарий" placeholder="Напишите что-нибудь" v-model="text" @keyup.enter="save" />
            <v-btn class="ma-2" @click="save">Отправить</v-btn>
        </v-layout>
</template>

<script>
    import {mapActions} from "vuex";
    import comment from "../../api/comment";

    export default {
        name: 'CommentForm',
        props: ['messageId'],
        data() {
            return {
                text: '',
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