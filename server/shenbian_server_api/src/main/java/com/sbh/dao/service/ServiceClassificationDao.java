package com.sbh.dao.service;

import com.sbh.annotation.RegisterDto;
import com.sbh.basedao.common.BaseDao;
import com.sbh.model.service.ServiceClassificationInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26.
 */
@RegisterDto(value = ServiceClassificationInfo.class)
public  interface ServiceClassificationDao extends BaseDao<ServiceClassificationInfo> {

    List<ServiceClassificationInfo> queryServiceClassifications(String groupId);

}
