<template>
<!--    <v-container fill-height>
        <v-layout column justify-space-around>
            <v-flex></v-flex>
            <v-flex>
                <v-layout justify-space-around>
                    <v-flex></v-flex>
                    <v-flex>
                        <v-layout column justify-space-around>
                            <v-flex></v-flex>
                            <v-flex style="margin: auto">
                                <v-card width="500" align="center">
                                    <v-card-text style="margin: auto">Admin</v-card-text>
                                </v-card>
                            </v-flex>
                            <v-flex></v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex></v-flex>
                </v-layout>
            </v-flex>
            <v-flex></v-flex>
        </v-layout>
    </v-container>-->

    <v-container column>
        <v-flex class="title mb-3">
            <div>Привет</div>
        </v-flex>
        <v-layout align-space-around justify-start column>
            <user-row v-for="user in userList"
                      :key="user.id"
                      :user="user"
                      :userList="userList"/>
        </v-layout>
        <div>
            <v-btn icon @click="get" small>get</v-btn>
        </div>
    </v-container>
</template>

<script>
    import userRow from 'components/admin/userRow.vue'

    export default {
        name: 'Admin',
        components: {
            userRow
        },
        data() {
            return {
                user: null,
                userList: null
            }

        },
        methods: {
            get() {
                const adminApi = this.$resource('/adminAccess')
                adminApi.get().then(response => {
                    this.userList = response.body
                })
            }
        }
    }
</script>

<style>

</style>