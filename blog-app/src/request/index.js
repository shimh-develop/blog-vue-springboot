import axios from 'axios'
import { Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/request/token'

const service = axios.create({
  baseURL: process.env.BASE_API, 
  timeout: 5000 
})

//request拦截器
service.interceptors.request.use(config => {
  
  if (store.getters.token) {
    config.headers['Oauth-Token'] = getToken() 
  }
  return config
}, error => {
	
  console.log(error) 
  Promise.reject(error)
})

// respone拦截器
service.interceptors.response.use(
  response => {
  	console.info('拦截器start..' )
  	console.info(response )
  	console.info('拦截器end..' )
    const res = response.data;
    //0 为成功状态
   	if (res.code !== 0) {
   		
     //20001 用户未登录
     if (res.code === 20001) {
       MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
         confirmButtonText: '重新登录',
         cancelButtonText: '取消',
         type: 'warning'
       }).then(() => {
         store.dispatch('FedLogOut').then(() => {
           location.reload();// 为了重新实例化vue-router对象 避免bug
         });
       })
     }
     
     //70001 无访问权限
     if(res.code === 70001){
     	Message({
	      message: res.msg,
	      type: 'error',
	      duration: 5 * 1000
	    })
     }
     
     return Promise.reject(res.msg);
   } else {
     return response.data;
   }
  },
  error => {
    console.log('err' + error)
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  })

export default service
