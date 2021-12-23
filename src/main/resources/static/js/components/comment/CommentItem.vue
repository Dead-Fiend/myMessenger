<template>
    <v-list-item>
<!--        <v-list-item-content color="indigo" v-if="!(comment.author && comment.author.avatar)" size="24px">
            <v-icon>account_circle</v-icon>
        </v-list-item-content>
        <v-list-item-content color="indigo" v-if="comment.author && comment.author.avatar   " size="24px">
            <v-icon>account_circle</v-icon>
        </v-list-item-content>-->

        <v-list-item-avatar color="indigo" size="24px">
            <v-icon>account_circle</v-icon>
        </v-list-item-avatar>
        <v-list-item-content>
            <v-list-item-title>{{comment.text}}</v-list-item-title>
            <v-list-item-subtitle>{{comment.author.username}}</v-list-item-subtitle>
        </v-list-item-content>
    </v-list-item>
</template>

<script>
    export default {
        name: 'CommentItem',
        props: ['comment'],
    }
</script>

<style>

</style>