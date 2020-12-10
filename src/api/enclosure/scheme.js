import request from '@/utils/request'

// 查询围蔽信息列表
export function listScheme(query) {
  return request({
    url: '/program/scheme/list',
    method: 'get',
    //params: query
  })
}

// 查询围蔽信息详细
export function getScheme(ssId) {
  return request({
    url: '/program/scheme/' + ssId,
    method: 'get'
  })
}

// 新增围蔽信息
export function addScheme(data) {
  return request({
    url: '/program/scheme',
    method: 'post',
    data: data
  })
}

// 修改围蔽信息
export function updateScheme(data) {
  return request({
    url: '/program/scheme',
    method: 'put',
    data: data
  })
}

// 删除围蔽信息
export function delScheme(ssId) {
  return request({
    url: '/program/scheme/' + ssId,
    method: 'delete'
  })
}

// 导出围蔽信息
export function exportScheme(query) {
  return request({
    url: '/program/scheme/export',
    method: 'get',
    params: query
  })
}
