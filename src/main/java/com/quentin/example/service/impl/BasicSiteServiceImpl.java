package com.quentin.example.service.impl;

import com.quentin.example.domain.BasicSiteVO;
import com.quentin.example.domain.mapper.BasicSiteVOMapper;
import com.quentin.example.service.IBasicSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auth Created by guoqun.yang
 * @Date Created in 9:50 2018/1/26
 * @Version 1.0
 */
@Service
public class BasicSiteServiceImpl implements IBasicSiteService {
    @Autowired
    private BasicSiteVOMapper basicSiteVOMapper;
    @Override
    public BasicSiteVO selectBasicSiteBySiteCode(String siteCode) {
        return basicSiteVOMapper.selectByCode(siteCode);
    }
}
