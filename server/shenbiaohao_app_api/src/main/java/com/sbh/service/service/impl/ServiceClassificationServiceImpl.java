package com.sbh.service.service.impl;

import com.sbh.basedao.common.BaseDao;
import com.sbh.basedao.common.BaseService;
import com.sbh.dao.service.ServiceClassificationDao;
import com.sbh.model.service.ServiceClassificationInfo;
import com.sbh.service.service.IServiceClassificationService;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/23.
 */
@Service
public class ServiceClassificationServiceImpl extends BaseService<ServiceClassificationInfo> implements IServiceClassificationService {

    protected Log logger	= LogFactory.getLog(getClass());

    @Autowired
    private ServiceClassificationDao serviceClassificationDao;



    @Override
    public BaseDao<ServiceClassificationInfo> getDao() {
        return serviceClassificationDao;
    }


    @Override
    public List<ServiceClassificationInfo> queryClassifications(String groupId) {

        List<ServiceClassificationInfo>  infos = serviceClassificationDao.queryServiceClassifications(groupId);
        return infos;
    }
}
