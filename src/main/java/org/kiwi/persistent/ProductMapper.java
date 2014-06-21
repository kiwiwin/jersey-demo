package org.kiwi.persistent;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.kiwi.domain.Product;

import java.util.List;

public interface ProductMapper {
    Product findProductById(int id);

    List<Product> all();

//    @Insert("insert into Product (name, description) values (#{name}, #{description})")
//    @Options(keyProperty = "id", useGeneratedKeys = true)
    Integer createProduct(Product product);
}
