package org.maxlv.shop.service.impl;

import org.maxlv.shop.dao.AreaDao;
import org.maxlv.shop.entity.Area;
import org.maxlv.shop.service.AreaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    private  AreaDao areaDao;

    //这个时候的areaDao并不是一个接口,注入的是一个代理实例
    public AreaServiceImpl(AreaDao areaDao) {
        this.areaDao = areaDao;
    }

    @Override
    public List<Area> getAreaList() {
          return areaDao.queryArea();
    }
}
