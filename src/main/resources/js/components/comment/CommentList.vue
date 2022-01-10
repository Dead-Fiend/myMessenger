<template>
    <v-container style="padding-left: 3%; padding-right: 5%">
        <v-list two-line>
            <v-subheader>Комментарии</v-subheader>

            <template v-for="(item, index) in comments">
                <v-divider v-if="index > 0" :key="index"></v-divider>
                <comment-item :comment="item" :key="'item' + index"></comment-item>
            </template>
            <comment-form :post-id="postId"></comment-form>
        </v-list>
    </v-container>
</template>

<script>
    import CommentItem from 'components/comment/CommentItem.vue'
    import CommentForm from 'components/comment/CommentForm.vue'
    export default {
        name: 'CommentList',
        components: {
            CommentItem,
            CommentForm
        },
        props: ['comments', 'postId']
    }
</script>

<style>

</style>