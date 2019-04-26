package org.maxlv.shop.dao;

import org.junit.Test;
import org.maxlv.shop.BaseTest;
import org.maxlv.shop.entity.ShopCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryDaoTest extends BaseTest {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testShopCategoryDao(){
        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(11L);
        List<ShopCategory> l  = shopCategoryDao.queryShopCategory(sc);
        assert l.size()==6;
    }

}
