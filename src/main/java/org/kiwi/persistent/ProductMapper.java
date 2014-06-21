package org.kiwi.persistent;

import org.kiwi.domain.Product;

import java.util.List;

public interface ProductMapper {
    Product selectProductById(int id);

    List<Product> all();
}
