import Vue from "vue";
import Vuex from 'vuex';
import postsApi from "api/posts";
import messagesApi from "api/messages";
import commentApi from "api/comment";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        posts,
        profile,
        messages,
        messagesToChat,
        ...frontendData,
        redact: redact,
        drkMode: drkMode,
    },
    getters: {
        sortedPosts: state => (state.posts || []).sort((a, b) => -(a.id - b.id)),
        sortedMessages: state => (state.messages || []).sort((a, b) => -(a.id - b.id)),
        chats: state => {
            let mssgs = (state.messages || [])

            mssgs.forEach(function (currentValue, index, array) {
                let {interlocutor, ...newObj} = currentValue
                state.messagesToChat[state.messagesToChat.length] = interlocutor
            });
            mssgs.forEach(function (currentValue, index, array) {
                let {author, ...newObj} = currentValue
                state.messagesToChat[state.messagesToChat.length] = author
            });

            (state.messagesToChat || []).forEach(function (currentValue, index, array) {
                if (currentValue.id == state.profile.id) {
                    delete state.messagesToChat[index]
                }
            });

            state.messagesToChat = state.messagesToChat.filter((n) => n != null);
            state.messagesToChat = state.messagesToChat.filter((thing, index, self) => self.findIndex(t => t.id === thing.id && t.username === thing.username) === index)
            return state.messagesToChat;
        }
    },
    mutations: {
        addPostMutation(state, post) {
            state.posts = [
                ...state.posts,
                post
            ]
        },
        updatePostMutation(state, post) {
            const updateIndex = state.posts.findIndex(item => item.id === post.id)
            state.posts = [
                ...state.posts.slice(0, updateIndex),
                post,
                ...state.posts.slice(updateIndex+1)
            ]
        },
        removePostMutation(state, post) {
            const deleteIndex = state.posts.findIndex(item => item.id === post.id)
            if (deleteIndex > -1) {
                state.posts = [
                    ...state.posts.slice(0, deleteIndex),
                    ...state.posts.slice(deleteIndex+1)
                ]
            }
        },
        addMessageMutation(state, message) {
            state.messages = [
                ...state.messages,
                message
            ]
        },
        updateMessageMutation(state, message) {
            const updateIndex = state.messages.findIndex(item => item.id === message.id)
            state.messages = [
                ...state.messages.slice(0, updateIndex),
                message,
                ...state.messages.slice(updateIndex+1)
            ]
        },
        removeMessageMutation(state, message) {
            const deleteIndex = state.messages.findIndex(item => item.id === message.id)
            if (deleteIndex > -1) {
                state.messages = [
                    ...state.messages.slice(0, deleteIndex),
                    ...state.messages.slice(deleteIndex+1)
                ]
            }
        },
        addCommentMutation(state, comment) {
            const postIndex = state.posts.findIndex(item => item.id === comment.post.id)
            const post = state.posts[postIndex]

            if ((!post.comments) || (!post.comments.find(it => it.id === comment.id))) {
                state.posts = [
                    ...state.posts.slice(0, postIndex),
                    {
                        ...post,
                        comments: [
                            ...post.comments,
                            comment
                        ]
                    },
                    ...state.posts.slice(postIndex + 1)
                ]
            } else {
                console.log('why')
            }


        },
        addPostPageMutation (state, posts) {
            const targetPosts = state.posts
                .concat(posts)
                .reduce((res, val) => {
                    res[val.id] = val
                    return res
                }, {})
            state.posts = Object.values(targetPosts)
        },
        updateTotalPagesMutation (state, totalPages) {
            state.totalPages = totalPages
        },
        updateCurrentPageMutation (state, currentPage) {
            state.currentPage = currentPage
        },
    },
    actions: {
        async addPostAction({commit, state}, post) {
            const result = await postsApi.add(post)
            const data = await result.json()
            const index = state.posts.findIndex(item => item.id === data.id)

            if (index > -1) {
                commit('updatePostMutation', data)
            } else {
                commit('addPostMutation', data)
            }
        },
        async updatePostAction({commit}, post) {
            const result = await postsApi.update(post)
            const data = await result.json()
            commit('updatePostMutation', data)
        },
        async removePostAction({commit}, post) {
            const result = await postsApi.remove(post.id)
            if (result.ok) {
                commit('removePostMutation', post)
            }
        },
        async addMessageAction({commit, state}, message) {
            const result = await messagesApi.add(message)
            const data = await result.json()
            const index = state.messages.findIndex(item => item.id === data.id)

            if (index > -1) {
                commit('updateMessageMutation', data)
            } else {
                commit('addMessageMutation', data)
            }
        },
        async updateMessageAction({commit}, message) {
            const result = await messagesApi.update(message)
            const data = await result.json()
            commit('updateMessageMutation', data)
        },
        async removeMessageAction({commit}, message) {
            const result = await messagesApi.remove(message.id)
            if (result.ok) {
                commit('removeMessageMutation', message)
            }
        },

        async addCommentAction({commit, state}, comment) {
            const response = await commentApi.add(comment)
            const data = await response.json()
            commit('addCommentMutation', data)
        },
        async loadPageAction ({commit, state}) {
            const response = await postsApi.page(state.currentPage + 1)
            const data = await response.json()
            commit('addPostPageMutation', data.posts)
            commit('updateTotalPagesMutation', data.totalPages)
            commit('updateCurrentPageMutation', Math.min(data.currentPage, data.totalPages - 1))
        },
    }
})