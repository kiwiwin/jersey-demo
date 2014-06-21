package org.kiwi.persistent;

import org.kiwi.domain.Product;

import java.util.List;

public interface ProductMapper {
    Product findProductById(int id);

    List<Product> all();
}
