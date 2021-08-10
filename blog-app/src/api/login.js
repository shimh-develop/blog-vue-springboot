import request from '@/request'

export function login(account, password) {
  const data = {
    account,
    password
  }
  return request({
    url: '/login',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'get'
  })
}

export function getUserInfo() {
  return request({
    url: '/users/currentUser',
    method: 'get'
  })
}

export function register(name, password,code) {
  const data = {
    name,
    password
  }
  return request({
    url: `/register/${code}`,
    method: 'post',
    data
  })
}

export default{
sendsms(mobilephonenumber){
  return request({
    url: `/sendsms/${mobilephonenumber}`,
    method: 'post',
  })
},
register(userForm,code) {
  return request({
    url: `/register/${code}`,
    method: 'post',
    data: userForm
  })
}
}
