import Vue from "vue";
import VueResource from 'vue-resource';
import App from "pages/Profile.vue";
import Vuetify from "vuetify";

Vue.use(VueResource)
Vue.use(Vuetify)

new Vue({
    el: '#profile',
    render: a => a(App),
    vuetify: new Vuetify({})
})
