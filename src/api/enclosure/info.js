import request from '@/utils/request'

// 查询整改信息列表
export function listInfo(query) {
  return request({
    url: '/enclosure/info/list',
    method: 'get',
    params: query
  })
}

// 查询整改信息详细
export function getInfo(riId) {
  return request({
    url: '/enclosure/info/' + riId,
    method: 'get'
  })
}

// 新增整改信息
export function addInfo(data) {
  return request({
    url: '/enclosure/info',
    method: 'post',
    data: data
  })
}

// 修改整改信息
export function updateInfo(data) {
  return request({
    url: '/enclosure/info',
    method: 'put',
    data: data
  })
}

// 删除整改信息
export function delInfo(riId) {
  return request({
    url: '/enclosure/info/' + riId,
    method: 'delete'
  })
}

// 导出整改信息
export function exportInfo(query) {
  return request({
    url: '/enclosure/info/export',
    method: 'get',
    params: query
  })
}