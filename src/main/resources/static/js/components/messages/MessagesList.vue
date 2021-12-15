<template>
    <div style="position: relative; width: 300px">
        <message-form :messages="messages" :messageAttr="message"/>
        <message-row v-for="message in messages"
                     :key="message.id"
                     :message="message"
                     :messages="messages"
                     :editMessage="editMessage"
                     :deleteMessage="deleteMessage" />
    </div>
</template>

<script>
    import MessageRow from 'components/messages/MessageRow.vue'
    import MessageForm from 'components/messages/MessageForm.vue'
    import { removeMessage } from "../../util/ws";
    import { getIndex } from "../../util/collections";

    export default {
        props: ['messages'],
        components: {
            MessageRow, MessageForm
        },
        data() {
            return {
                message: null
            }
        },
        methods: {
            editMessage(message) {
                this.message = message
            },
            deleteMessage(message) {


                removeMessage(message)
                this.messages.splice(this.messages.indexOf(message), 1)
                console.log(this.messages)
                /*this.$resource('/message{/id}').remove({id: message.id}).then(result => {
                    if (result.ok) {
                        this.messages.splice(this.messages.indexOf(message), 1)
                    }
                })*/

            }
        }
    }

</script>

<style>

</style>