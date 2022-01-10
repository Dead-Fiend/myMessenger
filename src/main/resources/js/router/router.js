import Vue from "vue";
import VueRouter from "vue-router";
import PostsList from 'pages/PostsList.vue'
import Auth from 'pages/Auth.vue'
import Profile from 'pages/Profile.vue'
import Registration from 'pages/Registration.vue'
import Admin from 'pages/Admin.vue'
import Main from 'pages/Main.vue'
import Subscriptions from "pages/Subscriptions.vue";

Vue.use(VueRouter)

const routes = [
    {path: '/', component: Main},
    {path: '/wall', component: PostsList},
    {path: '/auth', component: Auth},
    {path: '/reg', component: Registration},
    {path: '/user/:id?', component: Profile},
    {path:  '/subscriptions/:id', component: Subscriptions},
    {path: '/admin', component: Admin},
    {path: '*', component: PostsList},
]

export default new VueRouter({
    mode: "history",
    routes
})