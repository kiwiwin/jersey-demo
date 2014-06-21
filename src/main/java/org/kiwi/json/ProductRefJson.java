package org.kiwi.json;

import org.kiwi.domain.Product;

public class ProductRefJson {
    private int id;
    private String name;
    private String uri;
    private String pricings_uri;
    private CurrentPriceRefJson current_price;
    private String description;
    //for jackson

    public ProductRefJson() {

    }

    public int getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public String getPricings_uri() {
        return pricings_uri;
    }

    public CurrentPriceRefJson getCurrent_price() {
        return current_price;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public ProductRefJson(Product product) {
        uri = "/products/" + product.getId();
        id = product.getId();
        name = product.getName();
        pricings_uri = "/products/" + product.getId() + "/pricings";
        current_price = new CurrentPriceRefJson(product.getId(), product.getCurrentPrice());
        description = product.getDescription();
    }
}
