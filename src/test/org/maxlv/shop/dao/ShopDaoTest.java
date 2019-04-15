package org.maxlv.shop.dao;

import org.junit.Test;
import org.maxlv.shop.BaseTest;
import org.maxlv.shop.entity.Area;
import org.maxlv.shop.entity.PersonInfo;
import org.maxlv.shop.entity.Shop;
import org.maxlv.shop.entity.ShopCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ShopDaoTest extends BaseTest {


    //这个Shop Dao其实是mapper代理对象
    @Autowired
    private ShopDao shopDao;

    @Test
    public void testInsertShop() {
        Shop shop = new Shop();
        PersonInfo person = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        //外键设置值
        person.setUserId(11L);
        area.setAreaId(5);
        shopCategory.setShopCategoryId(12L);

        shop.setOwner(person);
        shop.setArea(area);

        //设置店铺信息
        shop.setShopName("大家");
        shop.setShopDesc("our");
        shop.setShopAddr("beijing");
        shop.setPhone("123213");
        shop.setShopImg("url/xxx");
        shop.setEnableStatus(1);
        shop.setPriority(50);
        shop.setCreateTime(new Date());
        shop.setAdvice("must price");
        int res = shopDao.insertShop(shop);
        System.out.println(shop.getShopId());
        assert res == 1;
    }

    @Test
    public void updateTest() {
        Shop shop = new Shop();
        shop.setShopId(32L);
        shop.setShopName("我是第二个人");
        int res = shopDao.updateShop(shop);
        assert res == 1;
    }
}
