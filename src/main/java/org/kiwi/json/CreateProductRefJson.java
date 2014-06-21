package org.kiwi.json;

import org.kiwi.domain.Product;

public class CreateProductRefJson {
    private String name;
    private String description;
    private CreatePriceRefJson price;

    public Product getProduct() {
        return new Product(name, description, price.getPrice());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(CreatePriceRefJson price) {
        this.price = price;
    }
}
