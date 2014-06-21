package org.kiwi.json;

import org.kiwi.domain.Product;

public class ProductsItemRefJson {


    private String uri;
    private int id;
    private String name;
    private CurrentPriceRefJson price;

    //for jackson
    public ProductsItemRefJson() {

    }

    public ProductsItemRefJson(Product product) {
        this.uri = "/products/" + product.getId();
        this.id = product.getId();
        this.name = product.getName();
        this.price = new CurrentPriceRefJson(product.getId(), product.getCurrentPrice());
    }

    public String getUri() {
        return uri;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CurrentPriceRefJson getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsItemRefJson that = (ProductsItemRefJson) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (uri != null ? !uri.equals(that.uri) : that.uri != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uri != null ? uri.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
