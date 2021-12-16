<template>
    <v-container fill-height>
        <v-layout column justify-space-around>
            <v-flex></v-flex>
            <v-flex>
                <v-layout raw justify-space-around>
                    <v-flex></v-flex>
                    <v-flex fill-height>
                        <v-card class="ps-2" style="margin-inside: 4px" width="100%">
                            <v-card-title>Регистрация</v-card-title>
                            <v-form width="350px" action="/registration" method="post" justify="center" align="center">
                                <v-text-field class="ma-1" v-model="username" name="username" :type="text" label="Логин" placeholder="Введите логин"  required autofocus/>
                                <v-text-field class="ma-1"v-model="password" name="password" :type="'password'" label="Пароль" required/>
                                <v-btn type="submit" depressed color="primary" class="mb-4">Зарегистрироваться</v-btn>
                            </v-form>
                        </v-card>
                        <div class="my-2">
                            Есть аккаунт? <a href="/auth">Войти</a>
                        </div>
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
        name: 'Registration',
    }
</script>

<style>

</style>