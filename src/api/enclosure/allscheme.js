import request from '@/utils/request'

// 查询围蔽信息列表
export function listAllscheme(query) {
  return request({
    url: '/program/scheme/list?flag=pass',
    // url: '/program/allscheme/list',
    method: 'get',
    params: query
  })
}

// 查询围蔽信息详细
export function getAllscheme(ssId) {
  return request({
    url: '/program/allscheme/' + ssId,
    method: 'get'
  })
}

// 新增围蔽信息
export function addAllscheme(data) {
  return request({
    url: '/program/allscheme',
    method: 'post',
    data: data
  })
}

// 修改围蔽信息
export function updateAllscheme(data) {
  return request({
    url: '/program/allscheme',
    method: 'put',
    data: data
  })
}

// 删除围蔽信息
export function delAllscheme(ssId) {
  return request({
    url: '/program/allscheme/' + ssId,
    method: 'delete'
  })
}

// 导出围蔽信息
export function exportAllscheme(query) {
  return request({
    url: '/program/allscheme/export',
    method: 'get',
    params: query
  })
}
