import request from '@/utils/request'

// 查询整改信息列表
export function listInfo(query) {
  return request({
    url: '/program/info/list?flag=nopass',
    // url: '/program/info/list',
    method: 'get',
    params: query
  })
}

// 查询整改信息详细
export function getInfo(riId) {
  return request({
    url: '/program/info/' + riId,
    method: 'get'
  })
}

// 新增整改信息
export function addInfo(data) {
  return request({
    url: '/program/info',
    method: 'post',
    data: data
  })
}

// 修改整改信息
export function updateInfo(data) {
  return request({
    url: '/program/info',
    method: 'put',
    data: data
  })
}

// 删除整改信息
export function delInfo(riId) {
  return request({
    url: '/program/info/' + riId,
    method: 'delete'
  })
}

// 导出整改信息
export function exportInfo(query) {
  return request({
    url: '/program/info/export',
    method: 'get',
    params: query
  })
}
