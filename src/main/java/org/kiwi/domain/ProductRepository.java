package org.kiwi.domain;

import java.util.List;

public interface ProductRepository {
    Product findProductById(int id);

    List<Product> all();

    int createProduct(Product product);

    int addNewPrice(Product product, Price price);
}
