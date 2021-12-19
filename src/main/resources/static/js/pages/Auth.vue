<template>
    <v-container fill-height>
        <v-layout column justify-space-around>
            <v-flex></v-flex>
            <v-flex>
                <v-layout justify-space-around>
                    <v-flex></v-flex>
                    <v-flex fill-height>
                        <v-layout column justify-space-around>
                            <v-flex>
                                <v-card class="px-2" style="margin: auto" width="500">
                                    <v-card-title>Авторизация</v-card-title>
                                    <v-form width="350px" action="/auth" method="post" justify="center" align="center">
                                        <v-text-field class="ma-1" v-model="username" name="username" :type="text" label="Логин" placeholder="Введите логин"  required autofocus/>
                                        <v-text-field class="ma-1"v-model="password" name="password" :type="'password'" label="Пароль" @keyup.enter="submit" required/>
                                        <v-btn type="submit" depressed color="primary" class="mb-4">Войти</v-btn>
                                    </v-form>
                                </v-card>
                            </v-flex>
                            <v-flex fill-height style="margin: auto" class="mt-3">
                                <span class="pa-1">Нет аккаунта? <v-btn href="/reg" small depressed>Зарегистрируйся</v-btn></span>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex></v-flex>
                </v-layout>
            </v-flex>
            <v-flex></v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    export default {
        name: 'Auth',
    }

</script>

<style>

</style>