package org.kiwi.persistent;

import org.apache.ibatis.annotations.Param;
import org.kiwi.domain.Price;
import org.kiwi.domain.Product;

public interface PriceMapper {
    int createPrice(@Param("product") Product product, @Param("price") Price price);
}
