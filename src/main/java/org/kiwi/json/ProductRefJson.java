package org.kiwi.json;

import org.kiwi.domain.Product;

public class ProductRefJson {
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductRefJson that = (ProductRefJson) o;

        if (id != that.id) return false;
        if (current_price != null ? !current_price.equals(that.current_price) : that.current_price != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (pricings_uri != null ? !pricings_uri.equals(that.pricings_uri) : that.pricings_uri != null) return false;
        if (uri != null ? !uri.equals(that.uri) : that.uri != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        result = 31 * result + (pricings_uri != null ? pricings_uri.hashCode() : 0);
        result = 31 * result + (current_price != null ? current_price.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

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
