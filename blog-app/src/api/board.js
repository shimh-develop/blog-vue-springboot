import request from '@/request'


export default{
 add(board){
   return request({
       url: `/board/add`,
       method: 'post',
       data: board
   })
 },
   getAllBoards(){
    return request({
        url: '/board',
        method: 'get',
    })
}
}