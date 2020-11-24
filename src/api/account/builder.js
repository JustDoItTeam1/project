import request from '@/utils/request'

// 查询施工单位用户列表
export function listBuilder(query) {
  return request({
    url: '/program/builder/list',
    method: 'get',
    params: query
  })
}

// 查询施工单位用户详细
export function getBuilder(builderId) {
  return request({
    url: '/account/builder/' + builderId,
    method: 'get'
  })
}

// 新增施工单位用户
export function addBuilder(data) {
  return request({
    url: '/account/builder',
    method: 'post',
    data: data
  })
}

// 修改施工单位用户
export function updateBuilder(data) {
  return request({
    url: '/account/builder',
    method: 'put',
    data: data
  })
}

// 删除施工单位用户
export function delBuilder(builderId) {
  return request({
    url: '/account/builder/' + builderId,
    method: 'delete'
  })
}

// 导出施工单位用户
export function exportBuilder(query) {
  return request({
    url: '/account/builder/export',
    method: 'get',
    params: query
  })
}
