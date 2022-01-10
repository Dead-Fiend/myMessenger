<template>
    <v-card class="my-2">
        <v-card-text class="text--primary">
            <div>
                <v-avatar size="36px">
                    <v-icon>account_circle</v-icon>
                </v-avatar>
            </div>
            <div>
                {{user}}
            </div>
            <v-card-actions>
                <v-btn icon @click="del" small>
                    <v-icon>close</v-icon>
                </v-btn>
            </v-card-actions>
        </v-card-text>
    </v-card>
</template>

<script>
    export default {
        name: 'userRow',
        props: ['user', 'userList'],
        methods: {
            del() {
                this.$resource('/adminAccess{/id}').remove({id: this.user.id}).then(result => {
                    if (result.ok) {
                        this.userList.splice(this.userList.findIndex(i => i.id === this.user.id), 1)
                    }
                })
            }
        }
    }
</script>

<style>

</style>