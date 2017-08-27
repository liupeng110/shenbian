package com.sbh.service.service.impl;

import com.sbh.basedao.common.BaseDao;
import com.sbh.basedao.common.BaseService;
import com.sbh.dao.service.ServiceAddressDao;
import com.sbh.model.service.ServiceAddressInfo;
import com.sbh.service.service.IServiceAddressService;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/23.
 */
@Service
public class ServiceAddressServiceImpl extends BaseService<ServiceAddressInfo> implements IServiceAddressService {

    protected Log logger	= LogFactory.getLog(getClass());

    @Autowired
    private  ServiceAddressDao serviceAddressDao;

    @Override
    public BaseDao<ServiceAddressInfo> getDao() {
        return serviceAddressDao;
    }

}
