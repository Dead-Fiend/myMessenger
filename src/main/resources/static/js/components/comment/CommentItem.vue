<template>
    <v-list-item>
<!--        <v-list-item-content color="indigo" v-if="!(comment.author && comment.author.avatar)" size="24px">
            <v-icon>account_circle</v-icon>
        </v-list-item-content>
        <v-list-item-content color="indigo" v-if="comment.author && comment.author.avatar   " size="24px">
            <v-icon>account_circle</v-icon>
        </v-list-item-content>-->

       <user-link :user="comment.author"></user-link>
        <v-list-item-content>
            <v-list-item-title>{{comment.text}}</v-list-item-title>
        </v-list-item-content>
    </v-list-item>
</template>

<script>
    import UserLink from "components/UserLink.vue";
    export default {
        name: 'CommentItem',
      components: {UserLink},
      props: ['comment'],
    }
</script>

<style>

</style>