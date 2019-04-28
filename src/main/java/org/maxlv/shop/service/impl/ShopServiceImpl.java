package org.maxlv.shop.service.impl;

import org.maxlv.shop.dao.ShopDao;
import org.maxlv.shop.dto.ShopExecution;
import org.maxlv.shop.entity.Shop;
import org.maxlv.shop.enums.ShopStateEnum;
import org.maxlv.shop.service.ShopService;
import org.maxlv.shop.util.ImageUtil;
import org.maxlv.shop.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {

    //注入shopDao spring自动注入其实现类,其实这个类就是一个mapper代理类
    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImg, String filename) {
        if (shop == null) {
            //必需是这个异常,这样才能在错误发生时终止事务的执行,保证操作的原子性
            throw new RuntimeException();
        }
        try {
            //一些初始化操作(店铺创建之前)
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());

            int effectedNum = shopDao.insertShop(shop);
            //如果失败
            if (effectedNum <= 0) {
                throw new RuntimeException();
            } else {
                //把文件写入本地文件夹中
                addShopImg(shop, shopImg, filename);
                //更新数据库里面的图片地址,这时候数据库已经有了店铺的记录
                try {
                    int effectednum = shopDao.updateShop(shop);
                    if (effectednum <= 0) {
                        throw new RuntimeException("创建数据库图片地址失败!");
                    }
                } catch (Exception e) {
                    throw new RuntimeException("addShopImg error:" + e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("addShopImg error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    private void addShopImg(Shop shop, InputStream inputStream, String filename) throws IOException {
        String dest = PathUtil.getShopImgPath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(inputStream, dest, filename);
        shop.setShopImg(shopImgAddr);
    }
}
