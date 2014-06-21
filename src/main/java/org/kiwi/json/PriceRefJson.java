package org.kiwi.json;

import org.kiwi.domain.Price;
import org.kiwi.domain.Product;

public class PriceRefJson {
    private int price;
    private String timestamp;
    private String uri;
    private String modifiedBy;

    //for jackson
    public PriceRefJson() {

    }

    public PriceRefJson(Product product, Price price) {
        this.uri = "/products/" + product.getId() + "/prices/" + price.getId();
        this.timestamp = price.getTimestamp();
        this.price = price.getPrice();
        this.modifiedBy = price.getModifiedBy();
    }

    public int getPrice() {
        return price;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getUri() {
        return uri;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }
}
