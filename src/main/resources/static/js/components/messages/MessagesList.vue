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
    export default {
        props: ['messages'],
        data: function () {
            return {
                message: null
            }
        },
        components: {
            MessageRow, MessageForm
        },
        methods: {
            editMessage: function (message) {
                this.message = message;
            },
            deleteMessage: function (message) {
                this.$resource('/message{/id}').remove({id: message.id}).then(result => {
                    if (result.ok) {
                        this.messages.splice(this.messages.indexOf(this.message), 1)
                    }
                })
            }
        }
    }

</script>

<style>

</style>