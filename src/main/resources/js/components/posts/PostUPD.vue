<template>
  <div class="my-2">
      <v-card rounded="lg" class="postwithoutpic-postwithoutpic232">
        <div class="postwithoutpic-header234" v-if="!isDefault">
          <router-link :to="`/user/${user.id}`">
          <div class="postwithoutpic-header235" >
            <div class="postwithoutpic-group236">
              <div class="postwithoutpic-avatar237" style="display:flex;justify-content:center;align-items:center;">
                <div style="display:flex;justify-content:center;align-items:center;">
                  <v-icon>perm_identity</v-icon>
                </div>
              </div>
              <div class="postwithoutpic-group8202433">
                <div class="postwithoutpic-nickdata2310">
                  <div class="postwithoutpic-nick2313">
                    {{ post.author.username }}
                  </div>
                  <div class="postwithoutpic-verify2317">
                    <svg class="postwithoutpic-svg" width='16' height='16' viewBox='0 0 16 16' fill='none' xmlns='http://www.w3.org/2000/svg'>
                      <circle cx='8' cy='8' r='8' fill='#0081FF'/>
                    </svg>
                    <div class="postwithoutpic-group2319">
                      <div class="postwithoutpic-group2320">
                        <svg class="postwithoutpic-image" width='6' height='6' viewBox='0 0 6 6' fill='none' xmlns='http://www.w3.org/2000/svg'>
                          <path d='M2.26915 6.00002C2.08263 6.00002 1.90623 5.91276 1.79283 5.7631L0.261292 3.7405C0.0620916 3.47745 0.113949 3.10288 0.377006 2.90359C0.640492 2.70422 1.01481 2.75642 1.21409 3.0193L2.22141 4.34942L4.75486 0.281591C4.92929 0.00173343 5.29761 -0.0838951 5.57806 0.0902763C5.85818 0.264619 5.94372 0.633191 5.76921 0.913305L2.77641 5.71845C2.67243 5.8855 2.49295 5.99059 2.29641 5.9995C2.28723 5.99985 2.27823 6.00002 2.26915 6.00002Z' fill='white'/>
                        </svg>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="postwithoutpic-data2315">
                <span class="postwithoutpic-text2">
                  <span class="postwithoutpic-text3">{{post.createdAt.slice(0, 10)}}</span>
                </span>
                </div>
              </div>
            </div>
            <v-menu rounded="pill" class="rounded-pill" top offset-x offset- v-if="!isDefault">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                    class="postwithoutpic-svg1"
                    icon

                    v-bind="attrs"
                    v-on="on"
                >
                  <svg  width='24' height='5' viewBox='0 0 24 5' fill='none' xmlns='http://www.w3.org/2000/svg'>
                    <path d='M2.76923 5C2.03479 5 1.33042 4.73661 0.811089 4.26777C0.291757 3.79893 0 3.16304 0 2.5C0 1.83696 0.291757 1.20107 0.811089 0.732233C1.33042 0.263392 2.03479 0 2.76923 0C3.50368 0 4.20804 0.263392 4.72737 0.732233C5.2467 1.20107 5.53846 1.83696 5.53846 2.5C5.53846 3.16304 5.2467 3.79893 4.72737 4.26777C4.20804 4.73661 3.50368 5 2.76923 5ZM12 5C11.2656 5 10.5612 4.73661 10.0419 4.26777C9.52253 3.79893 9.23077 3.16304 9.23077 2.5C9.23077 1.83696 9.52253 1.20107 10.0419 0.732233C10.5612 0.263392 11.2656 0 12 0C12.7344 0 13.4388 0.263392 13.9581 0.732233C14.4775 1.20107 14.7692 1.83696 14.7692 2.5C14.7692 3.16304 14.4775 3.79893 13.9581 4.26777C13.4388 4.73661 12.7344 5 12 5ZM21.2308 5C20.4963 5 19.792 4.73661 19.2726 4.26777C18.7533 3.79893 18.4615 3.16304 18.4615 2.5C18.4615 1.83696 18.7533 1.20107 19.2726 0.732233C19.792 0.263392 20.4963 0 21.2308 0C21.9652 0 22.6696 0.263392 23.1889 0.732233C23.7082 1.20107 24 1.83696 24 2.5C24 3.16304 23.7082 3.79893 23.1889 4.26777C22.6696 4.73661 21.9652 5 21.2308 5Z' fill="grey"/>
                  </svg>
                </v-btn>
              </template>
              <v-list class="rounded-pill">
                  <v-btn icon @click="edit" small class="ml-2" >
                    <v-icon>edit</v-icon>
                  </v-btn>
                  <v-btn icon @click="del" small class="mr-2">
                    <v-icon>close</v-icon>
                  </v-btn>
              </v-list>
            </v-menu>
          </div>
          </router-link>
        </div>
        <span class="postwithoutpic-text6" v-if="isDefault" style="text-align: center">
          <span class="postwithoutpic-text7">
            Добрый день, {{profile.username}}
          </span>
        </span>
        <span class="postwithoutpic-text6" v-if="!isDefault">
          <span class="postwithoutpic-text7">
            {{post.text}}
          </span>
        </span>
      </v-card>
  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";
import CommentList from 'components/comment/CommentList.vue'
import UserLink from "components/UserLink.vue";

export default {
  name: "PostUPD",
  components: {
    UserLink,
    CommentList
  },
  computed: {
    ...mapState(['profile']),
  },
  props: ['post', 'editPost', 'redact', 'isDefault'],
  methods: {
    ...mapActions(['removePostAction']),
    edit() {
      this.editPost(this.post)
    },
    del() {
      this.removePostAction(this.post)
    },
  },
}
</script>

<style scoped>
.postwithoutpic-postwithoutpic232 {
  width: 388px;
  display: flex;
  padding: 20px 19px;
  overflow: hidden;
  position: relative;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
  border-radius: 10px;
  flex-direction: column;
  /*background-color: rgba(22, 24, 27, 1);*/
}
.postwithoutpic-header234 {
  margin: 0 0 20px 0;
  display: flex;
  overflow: hidden;
  position: relative;
  box-sizing: border-box;
  align-items: center;
  flex-shrink: 1;
  border-color: transparent;
}
.postwithoutpic-header235 {
  width: 345px;
  height: 50px;
  display: flex;
  position: relative;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.postwithoutpic-group236 {
  top: 0px;
  left: 0px;
  width: 157px;
  height: 50px;
  display: flex;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.postwithoutpic-avatar237 {
  top: 0px;
  left: 0px;
  width: 50px;
  height: 50px;
  display: flex;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
  /*background-color: rgba(0, 0, 0, 1);*/
}
.postwithoutpic-text {
  top: 18.000011444091797px;
  left: 13.999998092651367px;
  /*color: rgba(255, 255, 255, 1);*/
  height: auto;
  position: absolute;
  font-size: 9px;
  align-self: auto;
  font-style: normal;

  font-family: Abril Fatface;
  font-weight: 400px;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
}
.postwithoutpic-text1 {
  /*color: rgba(255, 255, 255, 1);*/
  height: auto;
  align-self: auto;

  font-weight: 400px;
  font-stretch: normal;
  text-decoration: none;
}
.postwithoutpic-group8202433 {
  top: 6px;
  left: 62px;
  width: 95px;
  height: 38px;
  display: flex;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.postwithoutpic-data2315 {
  top: 25px;
  left: 0px;
  width: 67px;
  height: 13px;
  display: flex;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.postwithoutpic-text2 {
  top: 0px;
  left: 0px;
  color: var(--dl-color-default-textmutedececb);
  width: 67px;
  height: auto;
  position: absolute;
  font-size: 12px;
  align-self: auto;
  font-style: normal;
  font-family: Roboto;
  font-weight: 400px;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
}
.postwithoutpic-text3 {
  /*color: rgba(156, 160, 172, 1);*/
  width: 67px;
  height: auto;
  align-self: auto;
  font-style: normal;

  font-weight: 400px;
  font-stretch: normal;
  text-decoration: none;
}
.postwithoutpic-nickdata2310 {
  top: 0px;
  left: 0px;
  display: flex;
  overflow: hidden;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.postwithoutpic-nick2313 {
  height: 21px;
  margin: 0 4px 0 0;
  display: flex;
  position: relative;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;


  /*color: rgba(255, 255, 255, 1);*/
  align-self: auto;
  font-style: normal;

  font-weight: 700px;
  font-stretch: normal;
  text-decoration: none;
  font-size: 18px;
  font-family: Roboto;
  line-height: normal;
}
.postwithoutpic-verify2317 {
  width: 16px;
  height: 16px;
  display: flex;
  position: relative;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.postwithoutpic-svg {
  width: 16;
  height: 16;
  position: absolute;
}
.postwithoutpic-group2319 {
  top: 5.00006103515625px;
  left: 5.14013671875px;
  width: 6px;
  height: 5.999958038330078px;
  display: flex;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.postwithoutpic-group2320 {
  top: 0px;
  left: 0px;
  width: 6px;
  height: 5.999958038330078px;
  display: flex;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.postwithoutpic-image {
  top: 0px;
  left: 0px;
  width: 6px;
  height: 6px;
  position: absolute;
  object-fit: cover;
  border-color: transparent;
}
.postwithoutpic-svg1 {
  top: 6px;
  left: 310px;
  width: 24;
  height: 5;
  position: absolute;
}
.postwithoutpic-text6 {
  /*color: rgba(255, 255, 255, 1);*/
  width: 350px;
  height: auto;
  font-size: 14px;
  align-self: auto;
  font-style: normal;

  font-family: Roboto;
  font-weight: 400px;
  line-height: 22px;
  font-stretch: normal;
  text-decoration: none;
}
.postwithoutpic-text7 {
  /*color: rgba(255, 255, 255, 1);*/
  width: 350px;
  height: auto;
  align-self: auto;
  font-style: normal;

  font-weight: 400px;
  font-stretch: normal;
  text-decoration: none;
}
</style>