package com.sbh.service.service;

import com.sbh.common.IBaseService;
import com.sbh.model.service.ServiceInfo;
import com.sbh.vo.service.ServiceQueryVO;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/23.
 */
public interface IServiceOfService extends IBaseService<ServiceInfo> {

    List<ServiceQueryVO> queryTopFivePosition(Map<String,Object> map);
}
