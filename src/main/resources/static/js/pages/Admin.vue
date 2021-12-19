<template>
    <v-container fill-height>
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
    </v-container>
</template>

<script>
    export default {
        name: 'Admin'
    }
</script>

<style>

</style>