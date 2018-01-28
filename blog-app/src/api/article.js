import request from '@/request'


export function getArticles() {
  return request({
    url: '/articles',
    method: 'get'
  })
}

export function getArticle(id) {
  return request({
    url: `/articles/${id}`,
    method: 'get'
  })
}

export function getArticlesByCategory(id) {
  return request({
    url: `/articles/category/${id}`,
    method: 'get'
  })
}

export function getArticlesByTag(id) {
  return request({
    url: `/articles/tag/${id}`,
    method: 'get'
  })
}



export function publishArticle(article) {
  return request({
    url: '/articles/create',
    method: 'post',
    data: article
  })
}