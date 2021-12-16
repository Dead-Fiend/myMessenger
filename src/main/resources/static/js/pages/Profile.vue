<template>
        <v-container>
            <v-layout column justify-space-around>
                <v-flex>
                    {{profile.username}}
                </v-flex>
                <!--<profile-form :profile="profile" :active="active" :roles="roles" :id="id" :username="username"/>-->

                <v-flex>
                    <v-layout column justify-space-between>
                        <v-flex>
                            <span>Ваш уровень доступа:</span>
                            <i>
                                <span>{{profile.authorities[0].authority}}</span>
                            </i>
                        </v-flex>
                        <v-flex>
                            <span>Ваш аккаунт:</span>
                            <i>
                                <span v-if="profile.active">Включён</span>
                                <span v-else>Выключён</span>
                            </i>
                        </v-flex>
                    </v-layout>
                </v-flex>
            </v-layout>
        </v-container>
</template>

<script>
    //import ProfileForm from 'components/profile/ProfileForm.vue'
    import {mapState} from "vuex";
    //document.getElementById("").innerHTML = ""
    export default {
/*        components: {
            ProfileForm
        },*/
/*        data() {
            return{
                profile: frontendData.profile,
                active: frontendData.profile.active,
                roles: frontendData.profile.roles,
                id: frontendData.profile.id,
                username: frontendData.profile.username,
            }
        },*/
        computed: mapState(['profile']),
    }
</script>

<style scoped>

</style>