package com.sbh.dao.service;


import com.sbh.annotation.RegisterDto;
import com.sbh.basedao.common.BaseDao;
import com.sbh.model.service.ServiceInfo;
import com.sbh.vo.service.ServiceQueryVO;

import java.util.List;
import java.util.Map;


@RegisterDto(ServiceInfo.class)
public interface ServiceDao extends BaseDao<ServiceInfo> {
	
      public List<ServiceInfo> queryServiceInfo();

    /**
     * 首页 查询最新的五条数据
     * @param map
     * @return
     */
    List<ServiceQueryVO> queryTopFivePosition(Map<String,Object> map);

	
}
