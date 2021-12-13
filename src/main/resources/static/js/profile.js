import Vue from "vue";
import VueResource from 'vue-resource';
import App from "pages/Profile.vue";

Vue.use(VueResource)

new Vue({
    el: '#profile',
    render: a => a(App)
})
