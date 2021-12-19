import Vue from "vue";
import VueRouter from "vue-router";
import MessagesList from 'pages/MessagesList.vue'
import Auth from 'pages/Auth.vue'
import Profile from 'pages/Profile.vue'
import Registration from 'pages/Registration.vue'
import Admin from 'pages/Admin.vue'

Vue.use(VueRouter)

const routes = [
    {path: '/', component: MessagesList},
    {path: '/auth', component: Auth},
    {path: '/reg', component: Registration},
    {path: '/profile', component: Profile},
    {path: '/admin', component: Admin},
    {path: '/*', component: MessagesList},
]

export default new VueRouter({
    mode: "history",
    routes
})