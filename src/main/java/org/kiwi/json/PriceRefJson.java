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
        this.timestamp = price.getDate();
        this.price = price.getPrice();
        this.modifiedBy = price.getModifiedBy();
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceRefJson that = (PriceRefJson) o;

        if (price != that.price) return false;
        if (modifiedBy != null ? !modifiedBy.equals(that.modifiedBy) : that.modifiedBy != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        if (uri != null ? !uri.equals(that.uri) : that.uri != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = price;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        result = 31 * result + (modifiedBy != null ? modifiedBy.hashCode() : 0);
        return result;
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
