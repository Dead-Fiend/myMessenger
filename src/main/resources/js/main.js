import Vue from "vue";
import Vuetify from "vuetify";
import '@babel/polyfill'
import 'api/resource'
import router from "router/router";
import App from "pages/App.vue";
import store from "store/store";
import { connect } from "util/ws";
import 'vuetify/dist/vuetify.min.css'
import colors from 'vuetify/lib/util/colors'
import * as Sentry from "@sentry/vue";
import { Integrations } from "@sentry/tracing";

import dots from 'assets/dots.svg';
import group from 'assets/group.svg';
import ellipse from 'assets/ellipse.svg';

Sentry.init({
    Vue,
    dsn: "https://adcfcdd1e4d245cdbcf06c62a4c839f1@o1112425.ingest.sentry.io/6142022",
    integrations: [
        new Integrations.BrowserTracing({
            routingInstrumentation: Sentry.vueRouterInstrumentation(router),
            tracingOrigins: ["localhost", "my-site-url.com", /^\//],
        }),
    ],
    // Set tracesSampleRate to 1.0 to capture 100%
    // of transactions for performance monitoring.
    // We recommend adjusting this value in production
    tracesSampleRate: 1.0,
});

Sentry.configureScope(scope => {
    scope.setUser({
        id: profile && profile.id,
        username: profile && profile.username,
    })
})

if (profile) {
    connect()
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    vuetify: new Vuetify({}),
    store,
    router,
    render: a => a(App),

})
