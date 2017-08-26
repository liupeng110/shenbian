<<<<<<< HEAD
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
=======
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
>>>>>>> e7eb74d832a92ae08214a7cb6b7869295e64c0c2
