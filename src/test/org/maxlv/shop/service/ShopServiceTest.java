package org.maxlv.shop.service;

import org.junit.Test;
import org.maxlv.shop.BaseTest;
import org.maxlv.shop.dto.ShopExecution;
import org.maxlv.shop.entity.Area;
import org.maxlv.shop.entity.PersonInfo;
import org.maxlv.shop.entity.Shop;
import org.maxlv.shop.entity.ShopCategory;
import org.maxlv.shop.enums.ShopStateEnum;
import org.maxlv.shop.util.ImageUtil;
import org.maxlv.shop.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;


public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop(){
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
        shop.setShopName("大家1112");
        shop.setShopDesc("our");
        shop.setShopAddr("beijing");
        shop.setPhone("123213");

        shop.setPriority(50);
        shop.setAdvice("审核中");
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        //这个path就是resources下的图片
        File shopImg = new File(ImageUtil.basePath+"/1.jpg");
        ShopExecution shopExecution = shopService.addShop(shop,shopImg);
        shopExecution.getState();

    }
}
