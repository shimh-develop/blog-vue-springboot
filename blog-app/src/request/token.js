export function getToken() {
  const storeObj = _CLIENT_ ? localStorage: {}
  return storeObj.token
}

export function setToken(token) {
  const storeObj = _CLIENT_ ? localStorage: {}
  storeObj.token = token
}

export function removeToken() {
  const storeObj = _CLIENT_ ? localStorage: {}
  return storeObj.removeItem('token')
}
