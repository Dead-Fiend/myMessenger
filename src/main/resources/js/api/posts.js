import Vue from "vue";

const posts = Vue.resource('/post{/id}')

export default {
    add: post => posts.save({}, post),
    update: post => posts.update({id: post.id}, post),
    remove: id => posts.remove({id}),
    page: page => Vue.http.get('/post', { params: {page} })
}