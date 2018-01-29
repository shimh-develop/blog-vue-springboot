import request from '@/request'


export function getArticles() {
  return request({
    url: '/articles',
    method: 'get'
  })
}

export function getHotArtices() {
  return request({
    url: '/articles/hot',
    method: 'get'
  })
}

export function getNewArtices() {
  return request({
    url: '/articles/new',
    method: 'get'
  })
}

export function viewArticle(id) {
  return request({
    url: `/articles/view/${id}`,
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