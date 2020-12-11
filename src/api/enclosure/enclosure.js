import request from '@/utils/request'

// 查询围蔽信息列表
export function listEnclosure(query) {
  return request({
    url: '/program/scheme/list',
    method: 'get',
    params: query
  })
}
// 根据项目id获取围蔽方案详细信息
export function getIdEnclosure(ssId) {
  return request({
    url: '/program/scheme/byProjectId/' + ssId,
    method: 'get'
  })
}
// 围蔽方案审核
export function reviewEnclosure(ssId,query) {
  return request({
    url: '/program/scheme/'+ ssId,
    method: 'put',
    params: query
  })
}

// 下载围蔽方案附件
export function downloadEnclosure(query) {
  return request({
    url: '/common/download',
    method: 'get',
    params: query
  })
}
// 查询围蔽信息详细
export function getEnclosure(ssId) {
  return request({
    url: '/program/scheme/' + ssId,
    method: 'get'
  })
}

// 新增围蔽信息
export function addEnclosure(data) {
  return request({
    url: '/enclosure/scheme',
    method: 'post',
    data: data
  })
}

// 修改围蔽信息
export function updateEnclosure(data) {
  return request({
    url: '/enclosure/scheme',
    method: 'put',
    data: data
  })
}

// 删除围蔽信息
export function delEnclosure(ssId) {
  return request({
    url: '/enclosure/scheme/' + ssId,
    method: 'delete'
  })
}

// 导出围蔽信息
export function exportEnclosure(query) {
  return request({
    url: '/enclosure/scheme/export',
    method: 'get',
    params: query
  })
}
