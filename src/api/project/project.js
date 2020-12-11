import request from '@/utils/request'

// 查询施工项目列表
export function listProject(query) {
  return request({
    url: '/program/project/list',
    method: 'get',
    params: query
  })
}

// 查询施工单位列表！！！！！！！！！！！！！！！！！
// export function listBuilder(query) {
//   return request({
//     url: '/program/project/list',
//     method: 'get',
//   })
// }

// 查询施工项目详细
export function getProject(projectId) {
  return request({
    url: '/program/project/' + projectId,
    method: 'get'
  })
}

// 新增施工项目
export function addProject(data) {
  return request({
    url: '/program/project',
    method: 'post',
    data: data
  })
}

// 修改施工项目
export function updateProject(data) {
  return request({
    url: '/program/project',
    method: 'put',
    data: data
  })
}

// 删除施工项目
export function delProject(projectId) {
  return request({
    url: '/program/project/' + projectId,
    method: 'delete'
  })
}

// 导出施工项目
export function exportProject(query) {
  return request({
    url: '/program/project/export',
    method: 'get',
    params: query
  })
}
