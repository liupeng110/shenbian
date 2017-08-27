package com.sbh.service.service.impl;

import com.sbh.basedao.common.BaseDao;
import com.sbh.basedao.common.BaseService;
import com.sbh.dao.service.ServiceDao;
import com.sbh.model.service.ServiceInfo;
import com.sbh.service.service.IServiceOfService;
import com.sbh.vo.service.ServiceQueryVO;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/23.
 */
@Service
public class ServiceOfServiceImpl extends BaseService<ServiceInfo> implements IServiceOfService {

    protected Log logger	= LogFactory.getLog(getClass());
    @Autowired
    private ServiceDao serviceDao;


    @Override
    public BaseDao<ServiceInfo> getDao() {
        return serviceDao;
    }

    /**
     * 查询top5的位置信息
     * @param map
     * @return
     */
    @Override
    public List<ServiceQueryVO> queryTopFivePosition(Map<String, Object> map) {
        List<ServiceQueryVO> listResult = serviceDao.queryTopFivePosition(map);
        return listResult;
    }
}
