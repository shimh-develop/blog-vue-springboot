import request from '@/request'

export function getAllCategorys() {
  return request({
    url: '/categorys',
    method: 'get',
  })
}