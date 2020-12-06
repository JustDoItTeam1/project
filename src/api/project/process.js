import request from '@/utils/request'

// 查询施工进度列表
export function listProcess(query) {
  return request({
    url: '/program/process/list',
    method: 'get',
    params: query
  })
}

// 查询施工进度详细
export function getProcess(ppId) {
  return request({
    url: '/program/process/' + ppId,
    method: 'get'
  })
}

// 新增施工进度
export function addProcess(data) {
  return request({
    url: '/program/process',
    method: 'post',
    data: data
  })
}

// 修改施工进度
export function updateProcess(data) {
  return request({
    url: '/program/process',
    method: 'put',
    data: data
  })
}

// 删除施工进度
export function delProcess(ppId) {
  return request({
    url: '/program/process/' + ppId,
    method: 'delete'
  })
}

// 导出施工进度
export function exportProcess(query) {
  return request({
    url: '/program/process/export',
    method: 'get',
    params: query
  })
}
