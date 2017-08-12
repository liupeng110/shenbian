package com.sbh.vo.service;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/7/28.
 */
public class ServiceQueryVO {

    private Long id;
    private String serviceTitle;//服务标题
    private String serviceDescription;//服务描述
    private BigDecimal servicePrice;//服务价格
    private int serviceFlag;//服务标识(0:文章,1:服务)
    private String materialUrl;
    private String materialHash;
    private String materialSize;
    private String materialBytes;
    private String materialFormat;
    private int materialShowTime;

}
