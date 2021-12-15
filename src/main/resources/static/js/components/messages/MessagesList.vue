<template>
    <v-layout align-space-around justify-start column>
        <message-form :messageAttr="message" :redact="redact"/>
        <message-row v-for="message in sortedMessages"
                     :key="message.id"
                     :message="message"
                     :editMessage="editMessage"/>
    </v-layout>
</template>

<script>
    import MessageRow from 'components/messages/MessageRow.vue'
    import MessageForm from 'components/messages/MessageForm.vue'
    import {mapGetters} from 'vuex'

    export default {
        components: {
            MessageRow, MessageForm
        },
        data() {
            return {
                message: null,
                isRedact: null,
                redact: {}
            }
        },
        computed: mapGetters(['sortedMessages']),
        methods: {
            editMessage(message) {
                this.message = message
            },

        }
    }

</script>

<style>

</style>