<template>
    <div style="position: relative; width: 300px">
        <input type="text" placeholder="Write new username" v-model="username" />
        <input type="button" value="Save" @click="save" />
    </div>
</template>

<script>
    export default {
        props: ['profile', 'active', 'roles', 'id', 'username'],
        data: function () {
            return {
                active: this.active,
                roles: this.roles,
                username: this.username,
                id: this.id
            }
        },
        /*watch: {
            username: function(newVal) {
                this.username = newVal.username
            }
        },*/
        methods: {
            save: function () {
                this.$resource('/profile{/id}').update({id: this.id}, {active: this.active, roles: this.roles, username: this.username}).then(result =>
                        result.json().then(data => {

                            this.profile.username = data.username
                            this.username = ''
                            this.id = ''
                        })
                    )




            }
        }

    }
</script>

<style>

</style>