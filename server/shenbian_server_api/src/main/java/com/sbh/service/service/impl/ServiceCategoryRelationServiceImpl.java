package com.sbh.service.service.impl;

import com.sbh.basedao.common.BaseDao;
import com.sbh.basedao.common.BaseService;
import com.sbh.dao.service.ServiceCategoryRelationDao;
import com.sbh.model.service.ServiceCategoryRelation;
import com.sbh.service.service.IServiceCategoryRelationService;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/23.
 */
@Service
public class ServiceCategoryRelationServiceImpl extends BaseService<ServiceCategoryRelation> implements IServiceCategoryRelationService {

    protected Log logger	= LogFactory.getLog(getClass());

    @Autowired
    private ServiceCategoryRelationDao serviceCategoryRelationDao;

    @Override
    public BaseDao<ServiceCategoryRelation> getDao() {
        return serviceCategoryRelationDao;
    }
}
