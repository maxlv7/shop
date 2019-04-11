package org.maxlv.shop.service;

import org.junit.Test;
import org.maxlv.shop.BaseTest;
import org.maxlv.shop.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaServiceTest extends BaseTest {

    //    这里注入的是areaService的实现类
    @Autowired
    private AreaService areaService;

    @Test
    public void testGetList() {
        List<Area> areas = areaService.getAreaList();
        assert areas.size() == 4;
    }
}
