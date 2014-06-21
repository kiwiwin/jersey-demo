package org.kiwi.domain;

import java.util.List;

public interface ProductRepository {
    Product findByProductId(int id);

    List<Product> all();

}
