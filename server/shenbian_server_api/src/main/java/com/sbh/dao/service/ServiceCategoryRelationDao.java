package com.sbh.dao.service;


import com.sbh.annotation.RegisterDto;
import com.sbh.basedao.common.BaseDao;
import com.sbh.model.service.ServiceCategoryRelation;


@RegisterDto(value = ServiceCategoryRelation.class)
public interface ServiceCategoryRelationDao extends BaseDao<ServiceCategoryRelation> {


}
