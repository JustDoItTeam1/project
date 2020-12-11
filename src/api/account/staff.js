import request from '@/utils/request'

// 查询交管人员用户列表
export function listStaff(query) {
  return request({
    url: '/program/staff/list',
    method: 'get',
    params: query
  })
}

// 查询交管人员用户详细
export function getStaff(trafficId) {
  return request({
    url: '/program/staff/' + trafficId,
    method: 'get'
  })
}

// 新增交管人员用户
export function addStaff(data) {
  return request({
    url: '/program/staff',
    method: 'post',
    data: data
  })
}

// 修改交管人员用户
export function updateStaff(data) {
  return request({
    url: '/program/staff',
    method: 'put',
    data: data
  })
}

// 删除交管人员用户
export function delStaff(trafficId) {
  return request({
    url: '/program/staff/' + trafficId,
    method: 'delete'
  })
}

// 导出交管人员用户
export function exportStaff(query) {
  return request({
    url: '/program/staff/export',
    method: 'get',
    params: query
  })
}
