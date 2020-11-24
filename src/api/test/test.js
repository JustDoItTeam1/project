import request from '@/utils/request'

// 查询测试信息列表
export function listTest(query) {
  return request({
    url: '/sun/test/list',
    method: 'get',
    params: query
  })
}

// 查询测试信息详细
export function getTest(id) {
  return request({
    url: '/sun/test/' + id,
    method: 'get'
  })
}

// 新增测试信息
export function addTest(data) {
  return request({
    url: '/sun/test',
    method: 'post',
    data: data
  })
}

// 修改测试信息
export function updateTest(data) {
  return request({
    url: '/sun/test',
    method: 'put',
    data: data
  })
}

// 删除测试信息
export function delTest(id) {
  return request({
    url: '/sun/test/' + id,
    method: 'delete'
  })
}

// 导出测试信息
export function exportTest(query) {
  return request({
    url: '/sun/test/export',
    method: 'get',
    params: query
  })
}