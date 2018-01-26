import request from '@/request'

export function getAllTags() {
  return request({
    url: '/tags',
    method: 'get',
  })
}