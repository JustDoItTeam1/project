import request from '@/utils/request'

// 查询地图标牌
export function getMapSign(query) {
  return request({
    url: '/program/sign/signInfo/list',
    method: 'get',
    params: query
  })
}
// 地图查询标牌列表（画图）
export function MaplistSign(query) {
  return request({
    url: '/program/sign',
    method: 'get',
    params: query
  })
}
// 新增地图标牌
export function addMapSign(data) {
  return request({
    url: '/program/sign/inMap',
    method: 'post',
    data: data
  })
}


// 删除地图标牌信息
export function delMapSign(ssId) {
  return request({
    url: '/program/sign/signInfo/' + ssId,
    method: 'delete'
  })
}

// 查询标牌
export function listSign(query) {
  return request({
    url: '/program/sign/list',
    method: 'get',
    params: query
  })
}
// 查询单个标牌
export function getSign(signId) {
  return request({
    url: '/program/sign/' + signId,
    method: 'get'
  })
}

// 新增标牌
export function addSign(data) {
  return request({
    url: '/program/sign',
    method: 'post',
    data: data
  })
}

// 修改标牌
export function updateSign(data) {
  return request({
    url: '/program/sign',
    method: 'put',
    data: data
  })
}

// 删除标牌
export function delSign(signId) {
  return request({
    url: '/program/sign/' + signId,
    method: 'delete'
  })
}

// 导出标牌（无）
export function exportSign(query) {
  return request({
    url: '/sign/sign/export',
    method: 'get',
    params: query
  })
}
