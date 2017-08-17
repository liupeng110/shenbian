package com.sbh.dao.service;


import com.sbh.annotation.RegisterDto;
import com.sbh.basedao.common.BaseDao;
import com.sbh.model.service.ServiceInfo;

import java.util.List;


@RegisterDto(ServiceInfo.class)
public interface ServiceDao extends BaseDao<ServiceInfo> {
	
      public List<ServiceInfo> queryServiceInfo();

	
}
