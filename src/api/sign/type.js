import request from '@/utils/request'

// 查询标牌类型列表
export function listType(query) {
  return request({
    url: '/program/signType/list',
    method: 'get',
    params: query
  })
}

// 查询标牌类型详细（单个）
export function getType(id) {
  return request({
    url: '/sign/type/' + id,
    method: 'get'
  })
}

// 新增标牌类型
export function addType(data) {
  return request({
    url: '/program/signType',
    method: 'post',
    data: data
  })
}

// 修改标牌类型
export function updateType(data) {
  return request({
    url: '/program/signType',
    method: 'put',
    data: data
  })
}

// 删除标牌类型
export function delType(id) {
  return request({
    url: '/program/signType/' + id,
    method: 'delete'
  })
}

// 导出标牌类型
export function exportType(query) {
  return request({
    url: '/sign/type/export',
    method: 'get',
    params: query
  })
}
