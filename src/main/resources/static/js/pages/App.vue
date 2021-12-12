<template>
    <div style="position: absolute; top: 0%;">
        <div v-if="!profile" style="transform: translate(0, 150%)">
            Необходимо
            <a href="/login">Войти</a>
            или
            <a href="/registration">Зарегестрироваться</a>
        </div>
        <div v-else>
            <div style="transform: translate(45%, 0)">{{profile.username}}</div>
            <!-- <messages-list :messages="messages" /> -->
        </div>
    </div>
</template>

<script>
    export default {
        data: {
            messages: frontendData.messages,
            profile: frontendData.profile
        },
    }
</script>

<style>

</style>