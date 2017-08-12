package com.sbh.service.service;

import com.sbh.common.IBaseService;
import com.sbh.model.service.ServiceClassificationInfo;
import com.sbh.model.service.ServiceInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/7/23.
 */
public interface IServiceClassificationService extends IBaseService<ServiceClassificationInfo> {

    public List<ServiceClassificationInfo> queryClassifications(String groupId);
}
