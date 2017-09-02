package com.sbh.dao.service;

import com.sbh.annotation.RegisterDto;
import com.sbh.basedao.common.BaseDao;
import com.sbh.model.service.ServiceAddressInfo;

/**
 * Created by Administrator on 2017/7/26.
 */
@RegisterDto(value=ServiceAddressInfo.class)
public interface ServiceAddressDao extends BaseDao<ServiceAddressInfo> {

    
}
