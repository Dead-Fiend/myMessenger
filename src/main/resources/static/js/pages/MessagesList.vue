<template>
    <v-container column>
        <v-flex class="title mb-3">
            <div>Привет, {{profile.username}} </div>
        </v-flex>
        <v-layout align-space-around justify-start column>
            <message-form :messageAttr="message" :redact="redact"/>
            <message-row v-for="message in sortedMessages"
                         :key="message.id"
                         :message="message"
                         :editMessage="editMessage"
                         :redact="redact" />
        </v-layout>
    </v-container>

</template>

<script>
    import MessageRow from 'components/messages/MessageRow.vue'
    import MessageForm from 'components/messages/MessageForm.vue'
    import {mapGetters, mapState} from 'vuex'

    export default {
        components: {
            MessageRow, MessageForm
        },
        data() {
            return {
                message: null,
                isRedact: null,
            }
        },
        computed: {
            ...mapState(['profile', 'redact']),
            ...mapGetters(['sortedMessages'])
        },
            //mapGetters(['sortedMessages']), mapState(['profile'])
        methods: {
            editMessage(message) {
                this.message = message
            },

        }
    }

</script>

<style>

</style>