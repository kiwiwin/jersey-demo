package org.kiwi.json;

import org.kiwi.domain.Price;

public class CreatePriceRefJson {
    private int price;
    private String timestamp;
    private String modifiedBy;

    public Price getPrice() {
        return new Price(price, timestamp, modifiedBy);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
