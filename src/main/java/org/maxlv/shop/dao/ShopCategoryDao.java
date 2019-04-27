package org.maxlv.shop.dao;

import org.apache.ibatis.annotations.Param;
import org.maxlv.shop.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryDao {
    List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategory);
}
