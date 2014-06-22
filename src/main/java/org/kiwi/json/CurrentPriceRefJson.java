package org.kiwi.json;

import org.kiwi.domain.Price;

public class CurrentPriceRefJson {
    private String uri;
    private Price price;

    //for jackson
    public CurrentPriceRefJson() {

    }

    public CurrentPriceRefJson(int productId, Price price) {
        this.price = price;
        this.uri = "/products/" + productId + "/prices/current";
    }

    public int getPrice() {
        return price.getPrice();
    }

    public String getUri() {
        return uri;
    }
}
