import request from '@/utils/request'

// 登录方法
export function login(username, password, code, uuid) {
  const data = {
    username,
    password,

  }


    return request({
      url: '/login',
      method: 'post',
      data: data
    })


}

// 获取用户详细信息
export function getInfo() {
  // if(radioRole=="1"){
  //   return request({
  //     url: '/login/getAdminInfo',
  //     method: 'get'
  //   })
  // }
  // if(radioRole=="2"){
  //   return request({
  //     url: '/login/getTrafficInfo',
  //     method: 'get'
  //   })
  // }
  // if(radioRole=="3"){
  //   return request({
  //     url: '/login/getBuilderInfo',
  //     method: 'get'
  //   })
  // }
  return request({
    url: '/getInfo',
    method: 'get'
  })
}

// 退出方法
export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

// // 获取验证码
// export function getCodeImg() {
//   return request({
//     url: '/captchaImage',
//     method: 'get'
//   })
// }
