package org.maxlv.shop.dao;

import org.junit.Test;
import org.maxlv.shop.BaseTest;
import org.maxlv.shop.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class AreaDaoTest extends BaseTest {


    //这个area Dao其实是mapper代理对象
    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea(){
        List<Area> al = areaDao.queryArea();
        assertEquals(4,al.size());
    }
}
