import request from '@/utils/request'

// 查询管理员用户列表
export function listAdmin(query) {
  return request({
    url: '/program/admin/list',
    method: 'get',
    params: query
  })
}

// 查询管理员用户详细
export function getAdmin(adminId) {
  return request({
    url: '/program/admin/' + adminId,
    method: 'get'
  })
}

// 新增管理员用户
export function addAdmin(data) {
  return request({
    url: '/program/admin',
    method: 'post',
    data: data
  })
}

// 修改管理员用户
export function updateAdmin(data) {
  return request({
    url: '/program/admin',
    method: 'put',
    data: data
  })
}

// 删除管理员用户
export function delAdmin(adminId) {
  return request({
    url: '/program/admin/' + adminId,
    method: 'delete'
  })
}

// 导出管理员用户
export function exportAdmin(query) {
  return request({
    url: '/program/admin/export',
    method: 'get',
    params: query
  })
}
