import Vuex from 'vuex'
import Vue from 'vue'
import { getToken, setToken, removeToken } from '@/request/token'
import { login, getUserInfo, logout } from '@/api/login'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        account: '',
    	name: '',
		avatar: '',
        token: getToken(),
    },
    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token;
        },
        SET_ACCOUNT: (state, account) => {
            state.account = account
        },
        SET_NAME: (state, name) => {
            state.name = name
        },
        SET_AVATAR: (state, avatar) => {
            state.avatar = avatar
        }
    },
    actions: {
    	login({ commit }, user) {
    		return new Promise((resolve, reject) => {
		        login(user.account, user.password).then(data => {
		          if(data.code === 0){
		          	commit('SET_TOKEN', data.data['Oauth-Token'])
			      	setToken(data.data['Oauth-Token'])
			      	resolve()
		          }else{
		          	reject(data.msg)
		          }
		          
		        }).catch(error => {
		          reject(error)
		        })
		      })
    	},
    	// 获取用户信息
	    getUserInfo({ commit, state }) {
	      return new Promise((resolve, reject) => {
	      	getUserInfo().then(response => {
	        	
	          if (!response.data.code != 0) { 
	          	reject('error')
	          }
	          
	          const data = response.data
	          commit('SET_ACCOUNT', data.account)
	          commit('SET_NAME', data.name)
	          commit('SET_AVATAR', data.avatar)
	          resolve(response)
	        }).catch(error => {
	        	reject(error)
	        })
	      })
	    },
	    // 退出
	    logout({ commit, state }) {
	      return new Promise((resolve, reject) => {
	        logout().then(() => {
	          commit('SET_TOKEN', '')
	          commit('SET_ACCOUNT', '')
	          commit('SET_NAME', '')
	          commit('SET_AVATAR', '')
	          removeToken()
	          resolve()
	        }).catch(error => {
	          reject(error)
	        })
	      })
	    }
    }
})