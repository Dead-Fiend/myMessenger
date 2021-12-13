<template>
        <span>
            <span style="transform: translate(45%, 0)">{{profile.username}}</span>
            <profile-form :profile="profile" :active="active" :roles="roles" :id="id" :username="username"/>
            <div></div>
            <span>Ваш уровень доступа:</span>
            <i>
                <span>{{profile.roles[0]}}</span>
            </i>
            <div></div>
            <span>Ваш аккаунт:</span>
            <i>
                <span v-if="profile.active">Включён</span>
                <span v-else>Выключён</span>
            </i>
        </span>
</template>

<script>
    import ProfileForm from 'components/profile/ProfileForm.vue'
    //document.getElementById("data").innerHTML =
    export default {
        components: {
            ProfileForm
        },
        data: function () {
            return{
                profile: frontendData.profile,
                active: frontendData.profile.active,
                roles: frontendData.profile.roles,
                id: frontendData.profile.id,
                username: frontendData.profile.username,
            }
        },
    }
</script>

<style>

</style>