<template>
  <div>
  <div class="frame826385-frame826385" v-if="!(profile.id == message.author.id)">
    <div class="frame826385-group821399">
        <span class="frame826385-text">
          <span class="frame826385-text1">{{message.text}}</span>
        </span>
    </div>
    <div class="frame826385-group820398">
        <span class="frame826385-text2">
          <span class="frame826385-text3">{{ message.createdAt.slice(11, 16) }}</span>
        </span>
    </div>
  </div>
  <div class="frame826385-frame826385 ml-auto" v-if="profile.id == message.author.id">
    <div class="frame826385-group821399">
        <span class="frame826385-text">
          <span class="frame826385-text1">{{message.text}}</span>
        </span>
    </div>
    <div class="frame826385-group820398">
        <span class="frame826385-text2">
          <span class="frame826385-text3">{{ message.createdAt.slice(11, 16) }}</span>
        </span>
    </div>
  </div>
  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";
import UserLink from "components/UserLink.vue";

export default {
  name: "MessageUPD",
  components: {
    UserLink
  },
  computed: {
    ...mapState(['profile']),
  },
  props: ['message', 'editMessage', 'redact'],
  methods: {
    ...mapActions(['removeMessageAction']),
    edit() {
      this.editMessage(this.message)
    },
    del() {
      this.removeMessageAction(this.message)
    },
  },
}
</script>

<style scoped>
.frame826385-frame826385 {
  max-width: 400px;
  display: flex;
  padding: 5px;
  overflow: hidden;
  position: relative;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
  border-radius: 10px;
  flex-direction: column;
  background-color: rgba(39,39,39, 0.8);
}
.frame826385-group821399 {
  width: 19px;
  height: 19px;
  margin: 0 0 5px 0;
  display: flex;
  position: relative;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.frame826385-text {
  top: 0px;
  left: 0px;
  color: rgba(255, 255, 255, 1);
  height: auto;
  position: absolute;
  font-size: 16px;
  align-self: auto;
  font-style: normal;
  text-align: left;
  font-family: Roboto;
  font-weight: 500px;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
}
.frame826385-text1 {
  color: rgba(255, 255, 255, 1);
  height: auto;
  align-self: auto;
  font-style: normal;
  text-align: left;
  font-weight: 500px;
  font-stretch: normal;
  text-decoration: none;
}
.frame826385-group820398 {
  width: 26px;
  height: 12px;
  display: flex;
  position: relative;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.frame826385-text2 {
  top: 0px;
  left: 0px;
  color: rgba(255, 255, 255, 1);
  height: auto;
  position: absolute;
  font-size: 10px;
  align-self: auto;
  font-style: normal;
  text-align: left;
  font-family: Roboto;
  font-weight: 500px;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
}
.frame826385-text3 {
  color: rgba(255, 255, 255, 1);
  height: auto;
  align-self: auto;
  font-style: normal;
  text-align: left;
  font-weight: 500px;
  font-stretch: normal;
  text-decoration: none;
}
</style>