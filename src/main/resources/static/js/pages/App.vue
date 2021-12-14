<template>
    <div style="position: absolute; top: 0%;">
        <div v-if="!profile" style="transform: translate(0, 150%)">
            Необходимо
            <a href="/login">Войти</a>
            или
            <a href="/registration">Зарегестрироваться</a>
        </div>
        <div v-else>
            <div style="transform: translate(45%, 0)">{{profile.username}}</div>
            <messages-list :messages="messages" />
        </div>
    </div>
</template>

<script>
    import MessagesList from 'components/messages/MessagesList.vue'
    import { addHandler } from "util/ws";
    import { getIndex } from "../util/collections";

    export default {
        components: {
            MessagesList
        },
        data() {
            return{
                messages: frontendData.messages,
                profile: frontendData.profile
            }
        },
        created() {
            addHandler(data => {
                let index = getIndex(this.messages, data.id)
                if (index > -1) {
                    this.messages.splice(index, 1, data)
                } else {
                    this.messages.push(data)
                }
            })
        }
    }
</script>

<style>

</style>