package org.maxlv.shop.dao;

import org.maxlv.shop.entity.Shop;

public interface ShopDao {
    //新加店铺
    int insertShop(Shop shop);
    //更新店铺信息
    int updateShop(Shop shop);

}
