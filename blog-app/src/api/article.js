import request from '@/request'

export function publishArticle(article) {
  return request({
    url: '/articles/create',
    method: 'post',
    data: article
  })
}