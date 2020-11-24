import request from '@/utils/request'

// 查询围蔽方案信息列表
export function listEnclosure(query) {
  return request({
    url: '/map/enclosure/list',
    method: 'get',
    params: query
  })
}

// 查询围蔽方案信息详细
export function getEnclosure(id) {
  return request({
    url: '/map/enclosure/' + id,
    method: 'get'
  })
}

// 新增围蔽方案信息
export function addEnclosure(data) {
  return request({
    url: '/map/enclosure',
    method: 'post',
    data: data
  })
}

// 修改围蔽方案信息
export function updateEnclosure(data) {
  return request({
    url: '/map/enclosure',
    method: 'put',
    data: data
  })
}

// 删除围蔽方案信息
export function delEnclosure(id) {
  return request({
    url: '/map/enclosure/' + id,
    method: 'delete'
  })
}

// 导出围蔽方案信息
export function exportEnclosure(query) {
  return request({
    url: '/map/enclosure/export',
    method: 'get',
    params: query
  })
}